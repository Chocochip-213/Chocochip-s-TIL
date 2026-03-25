import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
 
class UserSolution {
    static public class CustomList {
        int[] head;
        int[] next;
        int[] to;
        int[] weight;
        int[] mId;
        int edgeCnt;
         
        CustomList(){
            this.head = new int[MAXV];
            this.next = new int[MAXE];
            this.to = new int[MAXE];
            this.weight = new int[MAXE];
            this.mId = new int[MAXE];
        }
         
        public void init(int v) {
            for(int i = 0; i < v; i++) {
                head[i] = -1;
            }
            edgeCnt = 0;
        }
         
        public void addEdge(int w, int u, int v, int mId) {
            weight[edgeCnt] = w;
            to[edgeCnt] = v;
            this.mId[edgeCnt] = mId;
            next[edgeCnt] = head[u];
            head[u] = edgeCnt++;
        }
         
         
    }
    static final int MAXV = 305;
    static final int MAXE = 4000;
     
    static public class state {
        int w;
        int v;
        int cost;
         
        state (int w, int v, int cost){
            this.w = w;
            this.v = v;
            this.cost = cost;
        }
    }
     
    static public class cComp implements Comparator<state>{
        @Override
        public int compare(state a, state b) {
            return Integer.compare(a.w, b.w);
        }
    }
     
    static int dist[][] = new int[305][2005];
    static final cComp cp = new cComp();
    static PriorityQueue<state> hq;
    static CustomList cl;
    static int cost[] = new int[305];
    static Map<Integer, Boolean> isDeleted;
    static int CityCnt;
     
    public void init(int N, int mCost[], int K, int mId[], int sCity[], int eCity[], int mDistance[]) {
        cl = new CustomList();
        cl.init(N + 1);
        CityCnt = N;
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
        cl.addEdge(mDistance, sCity, eCity, mId);
        return;
    }
 
    public void remove(int mId) {
        isDeleted.put(mId, true);
        return;
    }
 
    static final int INF = 1 << 30;
     
    public int cost(int sCity, int eCity) {
        hq = new PriorityQueue<>(cp);
        for(int i = 0; i < CityCnt; i++) {
            Arrays.fill(dist[i], INF);
        }
         
        hq.add(new state(0, sCity, cost[sCity]));
        dist[sCity][cost[sCity]] = 0;
         
        while(!hq.isEmpty()) {
            state s = hq.poll();
             
            if(dist[s.v][s.cost] < s.w) continue;
            if(s.v == eCity) return dist[s.v][s.cost];
             
            for(int i = cl.head[s.v]; i != -1; i = cl.next[i]) {
                if(isDeleted.containsKey(cl.mId[i])) continue;
                int nextN = cl.to[i];
                int minCost = Math.min(s.cost, cost[s.v]);
                int nextW = dist[s.v][s.cost] + cl.weight[i] * minCost;
                 
                if(dist[nextN][minCost] > nextW) {
                    dist[nextN][minCost] = nextW;
                    hq.add(new state(nextW, nextN, minCost));
                }
            }
        }
         
         
        return -1;
    }
}