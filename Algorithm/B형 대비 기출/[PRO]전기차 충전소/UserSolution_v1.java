import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class UserSolution {

    static final int maxV = 305;
    static final int maxE = 4000;

    static class CustomList {
        int[] head;
        int[] to;
        int[] weight;
        int[] next;
        int[] Id;
        int edgeCnt;

        CustomList(){
            this.head = new int[maxV];
            this.to = new int[maxE];
            this.weight = new int[maxE];
            this.next = new int[maxE];
            this.Id = new int[maxE];
        }

        public void init() {
            for(int i = 0; i < 305; i++) {
                head[i] = -1;
            }
            edgeCnt = 0;
        }

        public void addEdge(int w, int u, int v, int mId) {
            to[edgeCnt] = v;
            weight[edgeCnt] = w;
            Id[edgeCnt] = mId;
            next[edgeCnt] = head[u];
            head[u] = edgeCnt++;
        }
    }

    static class State {
        int w;
        int v;
        int cost;

        State(int w, int v, int cost){
            this.w = w;
            this.v = v;
            this.cost = cost;
        }
    }

    static class Comp implements Comparator<State> {
        @Override
        public int compare(State a, State b) {
            return Integer.compare(a.w, b.w);
        }
    }

    static CustomList cl = new CustomList();
    static PriorityQueue<State> hq;
    static Comp cp = new Comp();
    static int[][] dist = new int[305][2005];
    static int[] cost = new int[305];
    static Map<Integer, Boolean> isDeleted;
    static final int INF = 1 << 30;

    public void init(int N, int mCost[], int K, int mId[], int sCity[], int eCity[], int mDistance[]) {
        cl.init();
        isDeleted = new HashMap<>();

        for(int i = 0; i < N; i++) {
            cost[i] = mCost[i];
        }

        for(int i = 0; i < K; i++) {
            cl.addEdge(mDistance[i], sCity[i], eCity[i], mId[i]);
        }


        return;
    }

    public void add(int mId, int sCity, int eCity, int mDistance) {
        // 1,400번 호출
        // 커스텀 그래프 입력 O(N)
        cl.addEdge(mDistance, sCity, eCity, mId);
        return;
    }

    public void remove(int mId) {
        // 500번 호출
        // Lazy방식으로 인접리스트 순회할때 삭제된거면 스킵할거임.
        // HashMap<Integer, Boolean> O(1)
        isDeleted.put(mId, true);
        return;
    }

    public int cost(int sCity, int eCity) {
        // 100번 호출
        // 다익스트라 E log V
        // E = 3400
        // V = 300
        hq = new PriorityQueue<>(cp);
        for(int i = 0; i < maxV; i++) {
            Arrays.fill(dist[i], INF);
        }
        State s = new State(0, sCity, cost[sCity]);
        hq.add(s);
        dist[sCity][s.cost] = 0;

        while(!hq.isEmpty()) {
            s = hq.poll();

            if(dist[s.v][s.cost] < s.w) continue;

            if(s.v == eCity) return dist[s.v][s.cost];


            for(int i = cl.head[s.v]; i != -1; i = cl.next[i]) {
                if(isDeleted.containsKey(cl.Id[i])) continue;
                int nextN = cl.to[i];
                int minCost = s.cost;
                int nextW = dist[s.v][minCost] + minCost * cl.weight[i];
                if(minCost > cost[nextN]) minCost = cost[nextN];

                if(dist[nextN][minCost] > nextW) {
                    dist[nextN][minCost] = nextW;
                    hq.add(new State(nextW, nextN, minCost));
                }
            }
        }
        return -1;
    }
}