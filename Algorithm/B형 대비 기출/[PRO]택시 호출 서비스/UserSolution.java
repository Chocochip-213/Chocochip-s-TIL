import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

class UserSolution {
    // 나눠 볼까 3x3으로 나누려면
    // 만약에 N이 10000이라고 가정.
    // (0,0)(3333,3333)까지
    // (3334, 0)(6666,3333)까지
    // (6667, 0)(9999,3333)까지 왼쪽 3개 끝

    // (0, 3333)(3333, 6666)
    // (3334, 3333)(6666, 6666)
    // (6667, 3333)(9999, 6666)까지 중간 3개 끝

    // (0, 6666)(3333, 9999)
    // (3334, 6666)(6666, 9999)
    // (6667, 6666)(9999, 9999) 까지 우측 3개 끝

    // 결국에는
    // 왼쪽은
    // (0, 0)(N/3, N/3)
    // (N/3 + 1, 0)(N/3*2, N/3)
    // (N/3*2+1, 0)(N/3*3, N/3)

    // (0, N/3)(N/3, N/3*2)
    // (N/3 + 1, N/3)(N/3*2, N/3*2)
    // (N/3*2+1, N/3)(N/3*3, N/3*2)

    // (0, N/3*2)(N/3, N/3*3)
    // (N/3+1, N/3*2)(N/3*2, N/3*3)
    // (N/3*2+1, N/3*2)(N/3*3, N/3*3)

    // 이렇게 배열을 9개를 만들고
    // 사용자의 위치에서 상하좌우로 L을 더하고 해봤을때
    // 포함되는 배열만 순회를 해보면 될거같거든?
    // 그 조건을 배열을 0 ~ 10 로 나눠서
    // ArrayList에 그 배열안에 들어가면은 0~10중에 포함되는 배열 번호를 넣고
    // 그걸 int i : 배열번호 해서
    // i를 배열 인덱스로 넣고 순회하면서 찾으면 될거같거든.
    // 업데이트는 어떻게 할까?
    // 배열에는 택시 iD만 넣고 거리 및 정보는 Raw 해시맵에 넣는거임
    // 결과 조회용 힙큐를 계속 유지하면서 Versioning을 하자.
    // 택시가 이동하면 버전이 올라가는거야.
    // 최대버전이 많이 생기긴하는데.. 흠.
    // 일단 해보자 걍 ㅈ같다.

    static public class Taxi {
        int tID;
        int tDist;
        int tUserDist;
        int tX;
        int tY;
        int tVersion;

        Taxi(int tID, int tDist, int tUserDist, int tX, int tY, int tVersion) {
            this.tID = tID;
            this.tDist = tDist;
            this.tUserDist = tUserDist;
            this.tX = tX;
            this.tY = tY;
            this.tVersion = tVersion;
        }

    }

    public int findBucket(int x, int y) {
        // (0, 0)(N/3, N/3) 1번
        // (N/3 + 1, 0)(N/3*2, N/3) 2번
        // (N/3*2+1, 0)(N/3*3, N/3) 3번

        // (0, N/3)(N/3, N/3*2) 4번
        // (N/3 + 1, N/3)(N/3*2, N/3*2) 5번
        // (N/3*2+1, N/3)(N/3*3, N/3*2) 6번

        // (0, N/3*2)(N/3, N/3*3) 7번
        // (N/3+1, N/3*2)(N/3*2, N/3*3) 8번
        // (N/3*2+1, N/3*2)(N/3*3, N/3*3) 9번

        if (x < 0 || y < 0)
            return -1;
        if (x < citySize / 3 && y < citySize / 3)
            return 1;
        if (x < citySize / 3 * 2 && y < citySize / 3)
            return 2;
        if (x <= citySize / 3 * 3 && y < citySize / 3)
            return 3;
        if (x < citySize / 3 && y < citySize / 3 * 2)
            return 4;
        if (x < citySize / 3 * 2 && y < citySize / 3 * 2)
            return 5;
        if (x <= citySize / 3 * 3 && y < citySize / 3 * 2)
            return 6;
        if (x < citySize / 3 && y <= citySize / 3 * 3)
            return 7;
        if (x < citySize / 3 * 2 && y <= citySize / 3 * 3)
            return 8;
        if (x <= citySize / 3 * 3 && y <= citySize / 3 * 3)
            return 9;
        return -1;
    }

    public int calcCallDist(int callX, int callY, int TaxiX, int TaxiY) {
        int xD = Math.abs(callX - TaxiX);
        int yD = Math.abs(callY - TaxiY);

        return xD + yD;
    }

    public int calcGoalDist(int callX, int callY, int goalX, int goalY) {
        int xD = Math.abs(callX - goalX);
        int yD = Math.abs(callY - goalY);

        return xD + yD;
    }

    static public class tComp implements Comparator<Taxi> {
        @Override
        public int compare(Taxi a, Taxi b) {
            if (a.tUserDist != b.tUserDist)
                return Integer.compare(b.tUserDist, a.tUserDist);
            return Integer.compare(a.tID, b.tID);
        }
    }

    static int citySize;
    static int taxiCnt;
    static int limitCall;
    static final tComp tp = new tComp();
    PriorityQueue<Taxi> Result;
//  ArrayList<Taxi>[] TaxiBucket = new ArrayList[10];
    Set<Taxi>[] TaxiBucket = new Set[10];
    Taxi[] TaxiRaw = new Taxi[2001];
//  ArrayList<Integer> bucket = new ArrayList<>();
    Set<Integer> bucket = new HashSet<>();

    public void init(int N, int M, int L, int[] mXs, int[] mYs) {
        Result = new PriorityQueue<>(tp);
        for (int i = 0; i < 10; i++) {
//          TaxiBucket[i] = new ArrayList<>();
            TaxiBucket[i] = new HashSet<>();
        }
        citySize = N + 1;
        taxiCnt = M;
        limitCall = L;
        // mYs가 행임(세로)
        // mXs가 열임(가로)
        Taxi t;
        for (int i = 0; i < M; i++) {
//          System.out.println(findBucket(mYs[i], mXs[i]));
//          TaxiBucket[findBucket(mYs[i], mXs[i])].add(new Taxi(i, 0, mXs[i], mYs[i], 1));
            t = new Taxi(i + 1, 0, 0, mXs[i], mYs[i], 1);
            TaxiBucket[findBucket(mXs[i], mYs[i])].add(t);
            TaxiRaw[i + 1] = t;
            Result.add(t);
        }
        return;
    }

    static final int[] dx = { 0, -1, 1, 0, 0, -1, -1, 1, 1 };
    static final int[] dy = { 0, 0, 0, -1, 1, -1, 1, -1, 1 };
    static final int INF = 1 << 30;

    public int pickup(int mSX, int mSY, int mEX, int mEY) {
        bucket.clear();

        for (int i = 0; i < 9; i++) {
            // 8방향 버킷 찾기
            int idx = findBucket(mSX + dx[i] * limitCall, mSY + dy[i] * limitCall);
            if (idx == -1)
                continue;
            bucket.add(idx);
        }

        int GoalBucket = findBucket(mEX, mEY);
        int MinDist = INF;
        int SelectBucketIdx = -1;
        Taxi SelectTaxi = null;

        for (int i : bucket) {
            for (Taxi taxi : TaxiBucket[i]) {
                // 택시와 손님위치 거리계산부터.
                int buff = calcCallDist(mSX, mSY, taxi.tX, taxi.tY);
                if (buff > limitCall)
                    continue;
                if (buff <= MinDist) {
                    // 가장 짧은 손님과의 거리를 가진 택시가
                    // 선택된다.
                    if (buff == MinDist) {
                        // 만약 거리가 같다면
                        // 아이디가 작은거 선택.
                        if (taxi.tID < SelectTaxi.tID) {
                            SelectTaxi = taxi;
                            SelectBucketIdx = i;
                            continue;
                        }
                        continue;
                    }
                    MinDist = buff;
                    SelectTaxi = taxi;
                    SelectBucketIdx = i;
                }
            }
        }
        if (SelectBucketIdx == -1)
            return -1;

        // 원래 버킷에서 삭제하고
        TaxiBucket[SelectBucketIdx].remove(SelectTaxi);
        int UserD = calcGoalDist(mSX, mSY, mEX, mEY);
        if (SelectTaxi.tVersion != TaxiRaw[SelectTaxi.tID].tVersion) {
            // 여기서 꺼냈을때 버전이 다르다면
            // 리셋된거임.
            // 누적합이 아닌 새로 그냥 값을 넣어주기.
            SelectTaxi = new Taxi(SelectTaxi.tID, MinDist + UserD, UserD, mEX, mEY, SelectTaxi.tVersion + 1);
        } else {
            // 버전이 같으면 그대로 누적합
            SelectTaxi = new Taxi(SelectTaxi.tID, SelectTaxi.tDist + MinDist + UserD, SelectTaxi.tUserDist + UserD, mEX,
                    mEY, SelectTaxi.tVersion + 1);
        }
        TaxiRaw[SelectTaxi.tID] = SelectTaxi;
        TaxiBucket[GoalBucket].add(SelectTaxi);
        Result.add(SelectTaxi);
        return SelectTaxi.tID;
    }

    public Solution.Result reset(int mNo) {
        Solution.Result res = new Solution.Result();
        res.mMoveDistance = TaxiRaw[mNo].tDist;
        res.mRideDistance = TaxiRaw[mNo].tUserDist;
        res.mX = TaxiRaw[mNo].tX;
        res.mY = TaxiRaw[mNo].tY;
        Taxi t = new Taxi(mNo, 0, 0, TaxiRaw[mNo].tX, TaxiRaw[mNo].tY, TaxiRaw[mNo].tVersion + 1);
        TaxiRaw[mNo] = t;
        Result.add(t);
        return res;
    }

    public void getBest(int[] mNos) {
        int cnt = 0;
        ArrayList<Taxi> Picked = new ArrayList<>();
        Taxi t;
        while (cnt < 5 && !Result.isEmpty()) {
            t = Result.poll();
            if (t.tVersion != TaxiRaw[t.tID].tVersion)
                continue;
            mNos[cnt] = t.tID;
            cnt++;
            Picked.add(t);
        }

        for (Taxi i : Picked) {
            Result.add(i);
        }
        return;
    }
}