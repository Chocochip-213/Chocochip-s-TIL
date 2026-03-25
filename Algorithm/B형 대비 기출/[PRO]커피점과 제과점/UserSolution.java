import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
 
class UserSolution {
     
    static class CustomList {
        int[] head;
        int[] next;
        int[] to;
        int[] weight;
        int edgeCnt;
         
        CustomList(){
            this.head = new int[maxV+1];
            this.next = new int[maxE+1];
            this.to = new int[maxE+1];
            this.weight = new int[maxE+1];
        }
         
        public void init() {
            edgeCnt = 0;
        }
         
        public void addEdge(int w, int u, int v) {
            to[edgeCnt] = v;
            weight[edgeCnt] = w;
            next[edgeCnt] = head[u];
            head[u] = edgeCnt++;
        }
    }
     
//  static class graph {
//      int weight;
//      int end;
//      
//      graph(int weight, int end){
//          this.weight = weight;
//          this.end = end;
//      }
//  }
     
    static class search {
        int weight;
        int end;
        int flag;
         
        search (int weight, int end, int flag){
            this.weight = weight;
            this.end = end;
            this.flag = flag;
        }
    }
     
    static class cComp implements Comparator<search>{
        @Override
        public int compare(search a, search b) {
            return Integer.compare(a.weight, b.weight);
        }
    }
     
    static PriorityQueue<search>hq;
    static int[][] dist = new int[2][10001];
    static cComp cp = new cComp();
    static Set<Integer> isNotNomal;
    static final int INF = 1 << 30;
    static int cityCnt;
    static final int maxV = 10001;
    static final int maxE = 70001;
    static CustomList cl;
     
    public void init(int N, int K, int sBuilding[], int eBuilding[], int mDistance[]) {
        cityCnt = N;
        cl = new CustomList();
        for(int i = 0; i <= N; i++) {
            cl.head[i] = -1;
        }
        Arrays.fill(dist[0], 0, N, INF);
        Arrays.fill(dist[1], 0, N, INF);
        for(int i = 0; i < K; i++) {
            cl.addEdge(mDistance[i], sBuilding[i], eBuilding[i]);
            cl.addEdge(mDistance[i], eBuilding[i], sBuilding[i]);
        }
         
        return;
    }
 
    public void add(int sBuilding, int eBuilding, int mDistance) {
        cl.addEdge(mDistance, sBuilding, eBuilding);
        cl.addEdge(mDistance, eBuilding, sBuilding);
        return;
    }
 
    public int calculate(int M, int mCoffee[], int P, int mBakery[], int R) {
        isNotNomal = new HashSet<>();
        hq = new PriorityQueue<>(cp);
        search g;
        for(int i = 0; i < M; i++) {
            // 커피
            isNotNomal.add(mCoffee[i]);
            g = new search(0, mCoffee[i], 0);
            dist[0][mCoffee[i]] = 0;
            hq.add(g);
        }
        for(int i = 0; i < P; i++) {
            // 제과
            isNotNomal.add(mBakery[i]);
            g = new search(0, mBakery[i], 1);
            dist[1][mBakery[i]] = 0;
            hq.add(g);
        }
         
        while(!hq.isEmpty()) {
            g = hq.poll();
             
            if(dist[g.flag][g.end] < g.weight) continue;
            if(g.weight > R) continue;
             
            for(int i = cl.head[g.end]; i != -1; i = cl.next[i]) {
                int nextN = cl.to[i];
                int nextW = dist[g.flag][g.end] + cl.weight[i];
                if(nextW > R) continue;
                if(dist[g.flag][nextN] > nextW) {
                    dist[g.flag][nextN] = nextW;
                    hq.add(new search(nextW, nextN, g.flag));
                }
            }
        }
 
         
        int MinSum = INF;
         
        for(int i = 0; i < cityCnt; i++) {
            if(isNotNomal.contains(i)) {
                dist[0][i] = INF;
                dist[1][i] = INF;
                continue;
            }
            if(dist[0][i] == INF || dist[1][i] == INF) {
                dist[0][i] = INF;
                dist[1][i] = INF;
                continue;
            }
            int buff = dist[0][i] + dist[1][i];
            dist[0][i] = INF;
            dist[1][i] = INF;
            if(buff < MinSum)
                MinSum = buff;
        }
         
        if(MinSum == INF) return -1;
        return MinSum;
    }
}