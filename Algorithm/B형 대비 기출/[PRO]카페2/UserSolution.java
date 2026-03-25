import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
 
class UserSolution {
 
    static public class Order {
        int mID;
        int JuiceID;
        int rawJuice;
        int remainJuice;
        int addDate;
 
        Order(int mID, int JuiceID, int rawJuice, int remainJuice, int addDate) {
            this.mID = mID;
            this.JuiceID = JuiceID;
            this.rawJuice = rawJuice;
            this.remainJuice = remainJuice;
            this.addDate = addDate;
        }
    }
 
    static public class jComp implements Comparator<Order> {
        @Override
        public int compare(Order a, Order b) {
            if(a.addDate != b.addDate) return Integer.compare(a.addDate, b.addDate);
            return Integer.compare(a.remainJuice, b.remainJuice);
        }
    }
 
    static HashMap<Integer, int[]> remainMID;
    static HashMap<Integer, Integer> rawMID;
    static HashMap<Integer, Integer> JuiceCnt;
    static HashMap<Integer, Integer> Version;
    static int MaxJuice;
    static int addDate;
    static PriorityQueue<Order>[] hq = new PriorityQueue[11];
    static PriorityQueue<Hurry> hurryQ;
    // 1 ~ 10번 종류
    static final jComp jp = new jComp();
    static final hComp hp = new hComp();
    static int totalCnt;
 
    public void init(int N) {
        MaxJuice = N;
        addDate = 0;
        for (int i = 0; i < 11; i++) {
            hq[i] = new PriorityQueue<>(jp);
        }
        hurryQ = new PriorityQueue<>(hp);
        // 얘는 int배열로 각 주스 주문이 충족됐는지 판단하는용
        remainMID = new HashMap<>();
        // 얘는 각 MID마다 최초 음료수 전체 개수만
        rawMID = new HashMap<>();
        // 얘는 모든 주문이 충족됐는지 확인하기위해 해당 주문에 주스 종류만 넣어둠.
        JuiceCnt = new HashMap<>();
        // 얘는 버전관리임.
        Version = new HashMap<>();
        totalCnt = 0;
        return;
    }
 
    public int order(int mID, int M, int mBeverages[]) {
        // 20,000 이하.
        // 힙큐에 집어넣기
        int[] Juice = new int[11];
        int[] defArray = new int[11];
        int cnt = -1;
        int JuiceC = 0;
        int JuiceSum = 0;
        for (int i = 0; i < M; i++) {
            // 각 주스 종류별로 몇개가 들어왔는지 배열에 저장.
            Juice[mBeverages[i]] += 1;
        }
        for (int i : Juice) {
            // 해당 종류 주스 주문이 없으면 컨티뉴
            cnt++; // 사실상 인덱스 번호임.
            if (i == 0)
                continue;
            // 주스 주문 발견 시 개수(i)를 힙큐에 넣는다.
            // 이때 cnt는 해당 주스 종류이고, 원본 주스 개수, 남은 주스개수(최초는 동일), addDate)
            // JuiceC는 전체 주스 종류의 개수임.
            JuiceC++;
            JuiceSum += i;
            hq[cnt].add(new Order(mID, cnt, i, i, ++addDate));
        }
        remainMID.put(mID, defArray);
        JuiceCnt.put(mID, JuiceC);
        rawMID.put(mID, JuiceSum);
        Version.put(mID, 1);
        hurryQ.add(new Hurry(mID, JuiceSum, 1, addDate));
        totalCnt++;
        return totalCnt;
    }
 
    public int supply(int mBeverage) {
        // 50,000 이하.
 
        // 이게 가장 무거움.
        // 최대한 Lazy하게 반영해야하는데 여기서는
        // 힙큐에서 꺼낸 필요 음료 종류 카운팅배열과
        // 현재 누적된 전체 제공 음료 카운팅 배열을 보고
        // 만약 오더가 충족 된다면 pop하고
        // 아니라면 다시 add해줌.
        // 근데 전체 order가 20000까지 될수있으니까
        // 맨날 add pop하면 너무 비용이 크다!.
        // 음료가 배치된 주문의 ID는 어떻게 가져올까?
        // 음료별로 힙큐를 만든다?
        // 기존에 주문을 hq에 넣을때 각 주문 mID
        // 음료 종류의 개수를 어떤 뭐 해시맵같은데 저장하고
        // pop이된(제조완료) mID마다 cnt를 해준다.
        // 음료 종류의 cnt와 poped mID cnt가 같다면 끝인거임
        // 그러면 음료가 배치된 주문의 ID는?
        // 해당 음료수 힙큐 사이즈가 0이면 -1
        // pop했는데 나오면 ID
        // 다시 push해야함.
        // hq 객체자체에 현재 남은 주문수와 가장 처음 주문수를 같이 넣자.
        // 그러면 취소전 주문에 남은 음료의 개수도 반환가능하다.
        // 그러면 근데 현재남은주문수가 만약에 0이면 pop하고 다시 add를 안하고
        // cnt를 올리면되는데
        // 만약 그상태에서 cancel이 된다면 음료 재배치를 어떻게하지?
        // 남은 음료가 몇개인지를 모르잖아.
        // 아니지 total 음료 cnt는 유지를 해야함.
        // 한개 음료수가 완료되서 == 남은 주문수가 0이라 pop이 되는경우에
        // cnt가 다 되서 손님에게 전달된 경우에는 다시 카운팅배열을 그 주문을 받기전으로 해야하는데.
        // mid별로 해시맵으로 관리를 할까? 배열넣어서?
        // mid에 계속 업데이트 해주는거임 그 주문에 들어온 모든 음료의 수를.
        // 취소가 된다면 그 음료들을 supply를 다시 호출해서 또 업데이트 해주는거임.
        // 그럼 최대 supply 호출횟수는 1,000 늘는거야.
        // mId별로 완료 flag도 필요하다.
        // 그냥 remove해도될듯.
        // 여기서도 remove된경우 pop하는거 해야함 continue
        // 주문이 전부 완료된경우에는 전체 주문 개수 cnt도 -1 해주자.
 
        // 이건 업데이트 아닌거 픽할건데 아마 쓸일없을듯?
//      ArrayList<Order> picked = new ArrayList<>();
 
        // 해당 음료가 필요한 주문이 없는경우임.
        if (hq[mBeverage].isEmpty())
            return -1;
        Order o = hq[mBeverage].poll();
        while (!JuiceCnt.containsKey(o.mID) || !remainMID.containsKey(o.mID)) {
            // 만약에 삭제되었거나 완료된게 pop되었다면
            // 남아있는지 확인 후 pop하기.
            if (hq[mBeverage].isEmpty())
                return -1;
            o = hq[mBeverage].poll();
        }
        // 지금 pop된 주문에 쌓인 주스 카운팅배열에 +1하기.
        int[] buff = remainMID.get(o.mID);
        buff[mBeverage]++;
        remainMID.put(o.mID, buff);
        // 여기까지
 
        // 주스 필요한 양 -1로 업데이트 ( 1개를 받았으니까 )
        o = new Order(o.mID, o.JuiceID, o.rawJuice, o.remainJuice - 1, o.addDate);
 
        if (o.remainJuice == 0) {
            // 만약에 해당 주문에서 필요한 현재 주스 종류가 0이 된다면(다 받았다면)
            // 해당 주스 주스 필요 종류 -1 하기
            // 그리고 힙큐에서 다시 넣지않기(빼기).
            JuiceCnt.put(o.mID, JuiceCnt.get(o.mID) - 1);
        } else {
            // 아직 현재 주스가 해당 주문에서 더 필요하다면.
            // 힙큐에 다시 넣어주기
            hq[mBeverage].add(o);
        }
        // 주문의 모든 주스가 완료됐다면. 전체 주문갯수에서 까기.
        // 버전도 -1로 만들어준다.
        if (JuiceCnt.get(o.mID) == 0) {
            JuiceCnt.remove(o.mID);
            Version.put(o.mID, -1);
            totalCnt--;
        }
         
        int how = getStatus(o.mID);
        if(how != 0 && how != -1) {
            // 만약 주문이 취소되지않은경우
            // 남은 주스의 개수를 가져온다.
            int currV = Version.get(o.mID);
            Version.put(o.mID, currV + 1);
            Hurry h = new Hurry(o.mID, how, currV + 1, o.addDate);
            hurryQ.add(h);
        }
        return o.mID;
    }
 
    public int cancel(int mID) {
 
        // 1,000 이하.
        // 얘는 주문이 완료되었을때만 miD키가 삭제됨.
        if (!JuiceCnt.containsKey(mID))
            return 0;
        // 얘는 주문이 삭제되었을때만 키를 삭제할 예정임.
        if (!remainMID.containsKey(mID))
            return -1;
 
        int[] buff = remainMID.get(mID);
        int[] array = buff.clone();
        remainMID.remove(mID);
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            // 주문취소일때 해당 그 주문에 할당된 음료수를 순회함.
            // 만약에 음료수 개수가 0이라면 그 종류는 없어서 continue
            if (array[i] == 0)
                continue;
            // 해당 i종류가 존재한다면
            // 그 종류의 개수만큼 음료수 공급함수를 선언해주자.
            // 인자는 종류이다.
            for (int j = 0; j < array[i]; j++) {
                sum++;
                supply(i);
            }
        }
        // 삭제할때만 지우기.
        Version.put(mID, -1);
        totalCnt--;
 
        return rawMID.get(mID) - sum;
    }
 
    public int getStatus(int mID) {
        // 얘는 주문이 완료되었을때만 miD키가 삭제됨.
        if (!JuiceCnt.containsKey(mID))
            return 0;
        // 얘는 주문이 삭제되었을때만 키를 삭제할 예정임.
        if (!remainMID.containsKey(mID))
            return -1;
 
        // 얘는 지금까지 주문에 반영된 음료개수 배열
        int[] buff = remainMID.get(mID);
        // 얘는 젤처음에 들어온 음료수 개수
        int result = rawMID.get(mID);
        // 5,000 이하.
        for (int i : buff) {
            result -= i;
        }
        return result;
    }
 
    public static class Hurry {
        int mID;
        int HowJuice;
        int Version;
        int addDate;
 
        Hurry(int mID, int HowJuice, int Version, int addDate) {
            this.mID = mID;
            this.HowJuice = HowJuice;
            this.Version = Version;
            this.addDate = addDate;
        }
    }
 
    public static class hComp implements Comparator<Hurry> {
        @Override
        public int compare(Hurry a, Hurry b) {
            if (a.HowJuice != b.HowJuice)
                return Integer.compare(b.HowJuice, a.HowJuice);
            return Integer.compare(a.addDate, b.addDate);
        }
    }
 
    Solution.RESULT hurry() {
        // 10,000 이하.
        // 얘도 객체를 따로 만들어야겠네.
        // 전체 음료 개수가 우선순위이고
        // 개수가 같다면 addDate순으로 가면되겠군.
        // 일단 처음에 Order할때 전체 주문 개수로 넣고.
        // 버저닝을 해야겠다.
        // supply가 진행될때마다 버전이 1개 올라가고
        // 그때 남은 음료의 개수로 다시 push해주는거임.
        // cancel은 또 -1로 버전해주면 고스트큐로 되겠지?
        // 그러면 음료개수랑 addDate, mID로 객체 만들면되겠네
 
        Solution.RESULT res = new Solution.RESULT();
        res.cnt = 0;
        ArrayList<Hurry> picked = new ArrayList<>();
        Hurry h;
        while(!hurryQ.isEmpty() && res.cnt < 5) {
            h = hurryQ.poll();
            // 버전이 틀리면 구버전이므로 컨티뉴.
            if(h.Version != Version.get(h.mID)) continue;
            // 버전이 맞다면 다시 넣어주기위해 일단 add하고.
            picked.add(h);
            res.IDs[res.cnt] = h.mID; 
            res.cnt++;
        }
        for(Hurry k : picked) {
            hurryQ.add(k);
        }
         
         
        return res;
    }
 
}