import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

class UserSolution {
    // 영화 필드
    // ID, genre, mScore, mAddDate
    //
    // 삭제 필드 Version관리
    // Version -> hashmap으로

    static class Movie {
        int mId;
        int mGenre;
        int mTotal;
        int mAddDate;
        int mVersion;

        Movie(int mId, int mGenre, int mTotal, int mAddDate, int mVersion) {
            this.mId = mId;
            this.mGenre = mGenre;
            this.mTotal = mTotal;
            this.mAddDate = mAddDate;
            this.mVersion = mVersion;
        }
    }

    static class mComp implements Comparator<Movie> {
        @Override
        public int compare(Movie a, Movie b) {
            if (a.mTotal != b.mTotal) {
                return Integer.compare(b.mTotal, a.mTotal);
            }
            return Integer.compare(b.mAddDate, a.mAddDate);
        }
    }

    static class User {
        int uID;
        int mID; // mID 해시맵으로 접근해서 다 받을 예정임.
        int uScore;
        int uAddDate;

        User(int uID, int mID, int uScore, int uAddDate) {
            this.uID = uID;
            this.mID = mID;
            this.uScore = uScore;
            this.uAddDate = uAddDate;
        }
    }

    static PriorityQueue<Movie>[] mPQ = new PriorityQueue[6];
    static ArrayList<User>[] uAL = new ArrayList[1010];
    static int[] uAddCnt = new int[1010];
    static HashSet<Integer>[] isWatched = new HashSet[1010];
    static HashMap<Integer, Movie> mRecent; // 영화 최신정보 key: mID
    static HashMap<Integer, Integer> mVersion; // 고스트 큐를 위한 버전관리.
    static mComp mc = new mComp();
    static int mAddD;

    boolean mAddCheck(int mID) {
        // 영화 등록된적 없음.
        if (!mVersion.containsKey(mID))
            return true;

        // 영화 등록된적 있음.
        else
            return false;
    }

    boolean mDelCheck(int mID) {
        // 영화 등록된적 없다면 false
        if (!mVersion.containsKey(mID))
            return false;
        // 영화가 이미 삭제됐다면 false
        if (mVersion.get(mID) == -1)
            return false;

        return true;
    }

    boolean uWatchCheck(int uID, int mID) {
        // 영화가 등록된적없거나, 삭제되었다면 false
        if (!mDelCheck(mID))
            return false;
        // 이미 사용자가 본적 있는 영화라면 false
        if (isWatched[uID].contains(mID))
            return false;

        return true;
    }

    void init(int N) {

        for (int i = 0; i <= N + 5; i++) {
            // 유저 객체 생성.
            uAL[i] = new ArrayList<>();
            uAddCnt[i] = 0;
            isWatched[i] = new HashSet<>(); // 안에 있으면 시청했던거임.
        }

        for (int j = 0; j < 6; j++) {
            // 영화 장르별 5개 + 전체 영화 1개 힙큐 생성.
            mPQ[j] = new PriorityQueue<>(mc);
        }
        mAddD = 0; // 영화 등록 날짜는 모두 공유
        mRecent = new HashMap<>();
        mVersion = new HashMap<>();
        return;
    }

    int add(int mID, int mGenre, int mTotal) {
        if (mAddCheck(mID)) {
            // 영화가 등록된적없다면.
            // 최초 등록 버전은 1.
            Movie m = new Movie(mID, mGenre, mTotal, ++mAddD, 1);
            mVersion.put(mID, 1); // 최초 버전 등록
            mRecent.put(mID, m); // 최초 상태 등록
            mPQ[mGenre].add(m); // 장르별 힙큐 등록완료.
            mPQ[0].add(m); // 전체 영화 힙큐 등록 완료.
            return 1;
        }
        return 0;
    }

    int erase(int mID) {
        if (mDelCheck(mID)) {
            mVersion.put(mID, -1);
            return 1;
        }
        return 0;
    }

    int watch(int uID, int mID, int mRating) {
        if (uWatchCheck(uID, mID)) {
            User u = new User(uID, mID, mRating, ++uAddCnt[uID]);
            // 시청기록에 넣기 완료.
            uAL[uID].add(u);
            // 성공하면 영화 점수 업데이트 해야하고.
            // 버전 업데이트 해야하고
            // 힙큐에 업데이트 된거 넣어줘야한다.
            Movie oldM = mRecent.get(mID);
            Movie m = new Movie(oldM.mId, oldM.mGenre, oldM.mTotal + mRating, oldM.mAddDate, oldM.mVersion + 1);
            // 버전 최신값 업데이트 완료.
            mVersion.put(mID, oldM.mVersion + 1);
            // 최신 상태 업데이트 완료.
            mRecent.put(mID, m);
            // 힙큐에 최신정보 넣기 완료.
            mPQ[oldM.mGenre].add(m);
            mPQ[0].add(m);
            // 일단 이거 안넣었었음.
            isWatched[uID].add(mID);
            return 1;
        }
        return 0;
    }

    boolean isOkay(Movie m, int uID) {
        int cMID = m.mId;
        int cVer = m.mVersion;
        // 이미 삭제된 영화면 false
        if (mVersion.get(cMID) == -1)
            return false;
        // 최신버전이랑 pop된 버전이 다르면 false
        if (mVersion.get(cMID) != cVer)
            return false;
        // 이미 유저가 시청한 영화라면 false
        if (isWatched[uID].contains(cMID))
            return false;

        return true;
    }

    Solution.RESULT suggest(int uID) {
        Solution.RESULT res = new Solution.RESULT();
        ArrayList<Movie> pickedM = new ArrayList<>();

        res.cnt = -1;
        // 영화 다 뽑았는지 검증하기 위한 cnt
        int cnt = 0;
        // uAddCnt[uID] == 0이며 시청기록 없는거임.
        if (uAddCnt[uID] == 0) {
            // 시청기록 없으면 전체 영화에서 pop5개 해야지.
            while (cnt < 5 && mPQ[0].size() != 0) {
                Movie m = mPQ[0].poll();
                if (isOkay(m, uID)) {
                    res.IDs[cnt] = m.mId;
                    cnt++;
                }
                pickedM.add(m);
            }

            for(Movie m : pickedM) {
                mPQ[0].add(m);
            }
        }
        else {
            // User u = new User(uID, mID, uScore, uAddDate)

            ArrayList<User> pickedU = new ArrayList<>();
            User u;
            // 시청기록 있다면 장르를 가져와야함.
            // uAddCnt-1부터 시작해야함. 역순조회.
            for (int i = uAddCnt[uID] - 1; i >= 0; i--) {
                // 역순 조회
                u = uAL[uID].get(i);
                int mID = u.mID;
                // 시청기록의 영화가 이미 삭제된 영화라면 continue
                if(mVersion.get(mID) == -1) continue;
                pickedU.add(u);
                // 5개 골랐으면 break
                if(pickedU.size() == 5) break;
            }

            int BS = 0; // 버퍼 점수
            int BAD = 0; // 버퍼 날짜
            int BG = 0;

            for(User curr : pickedU) {
                if(BS == 0) {
                    BS = curr.uScore;
                    BAD = curr.uAddDate;
                    BG = mRecent.get(curr.mID).mGenre;
                    continue;
                }

                // 점수 우선순위
                if (BS < curr.uScore) {
                    BS = curr.uScore;
                    BAD = curr.uAddDate;
                    BG = mRecent.get(curr.mID).mGenre;
                    continue;
                }


                // 그다음 날짜 우선순위(큰순)
                if (BS == curr.uScore) {
                    // 두개 점수가 같다면
                    if(BAD < curr.uAddDate) {
                        // 날짜 비교 후
                        BAD = curr.uAddDate;
                        BG = mRecent.get(curr.mID).mGenre;
                        continue;
                    }
                }
            } // for문 끝


            // 시청기록 없으면 전체 영화에서 pop5개 해야지.
            while (cnt < 5 && mPQ[BG].size() != 0) {
                Movie m = mPQ[BG].poll();
                if (isOkay(m, uID)) {
                    res.IDs[cnt] = m.mId;
                    cnt++;
                }
                pickedM.add(m);
            }
            for(Movie m : pickedM) {
                mPQ[BG].add(m);
            }
        }
        res.cnt = cnt;
//      System.out.println(cnt);
        return res;
    }
}