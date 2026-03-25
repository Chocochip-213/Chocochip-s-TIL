package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ShortestPath {
	static final int MAX_V = 20005;
	static final int MAX_E = 600005;
	static final int MAX_DIST = MAX_E * 10;
	static int[] head = new int[MAX_V];
	static int[] to = new int[MAX_E];
	static int[] next = new int[MAX_E];
	static int[] dist_weight = new int[MAX_E];
	static int[] dist = new int[MAX_V];
	static int edgeCnt;
	
	static void init(int V, int E) {
		for(int i = 0; i <= V; i++) {
			head[i] = -1;
			dist[i] = Integer.MAX_VALUE;
		}
		edgeCnt = 0;
	}
	
	static void addEdge(int u, int v, int w) {
		to[edgeCnt] = v;
		dist_weight[edgeCnt] = w;
		next[edgeCnt] = head[u];
		head[u] = edgeCnt++;
	}
	
	static final int MAX_Q = 600005;
	static int[] heapV = new int[MAX_Q];
	static int[] heapD = new int[MAX_Q];
	static int heapSize;
	static int poppedV, poppedD;
	
	static void hq_push(int d, int v) {
		int cur = ++heapSize;
		
		heapD[heapSize] = d;
		heapV[heapSize] = v;

		while(cur > 1) {
			int parent = cur / 2;
			
			if(heapD[parent] <= heapD[cur]) break;
			
			int tmpD = heapD[cur];
			heapD[cur] = heapD[parent];
			heapD[parent] = tmpD;
			
			int tmpV = heapV[cur];
			heapV[cur] = heapV[parent];
			heapV[parent] = tmpV;
			
			cur = parent;
		}
	}
	
	static boolean hq_pop() {
		if (heapSize == 0) return false;
		poppedD = heapD[1];
		poppedV = heapV[1];
		
		heapD[1] = heapD[heapSize];
		heapV[1] = heapV[heapSize--];
		
		int cur = 1;
		
		while(cur * 2 <= heapSize) {
			int child = cur * 2;
			
			if(child + 1 <= heapSize && heapD[child+1] < heapD[child]) {
				child++;
			}
			
			if(heapD[cur] <= heapD[child]) break;
			
			int tmpD = heapD[cur];
			heapD[cur] = heapD[child];
			heapD[child] = tmpD;
			
			int tmpV = heapV[cur];
			heapV[cur] = heapV[child];
			heapV[child] = tmpV;
			
			cur = child;
		}
		return true;
	}
	
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		// V: 정점의 개수, E: 간선의 개수
		int start_V = Integer.parseInt(br.readLine());
		// 시작 정점 번호
		// 1 ~ V까지 번호가 매겨짐.
		init(V, E);
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			addEdge(u, v, w);
			// 방향 그래프임 (단방향)
		}
		
		
			
		hq_push(0, start_V);
		dist[start_V] = 0;
		while(heapSize != 0) {
			hq_pop();
			int currD = poppedD;
			int currV = poppedV;
			
			if (currD > dist[currV]) continue;
			
			for(int j = head[currV]; j != -1; j = next[j]) {
				int next_V = to[j];
				int next_D = dist_weight[j] + currD;
				if(dist[next_V] > next_D) {
					dist[next_V] = next_D;
					hq_push(dist[next_V], next_V);
				}
			} // 리스트 순회
		} // 힙큐 와일
		
		for(int i = 1; i <= V; i++) {
			if(dist[i] > MAX_DIST) sb.append("INF").append("\n");
			else sb.append(dist[i]).append("\n");
		}
		System.out.print(sb);
	} // 메인문
}
