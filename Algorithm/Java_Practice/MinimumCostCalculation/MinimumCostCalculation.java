package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MinimumCostCalculation {

	static final int MAX_V = 1005;
	static final int MAX_E = 100005;
	static int[] head = new int[MAX_V];
	static int[] to = new int[MAX_E];
	static int[] next = new int[MAX_E];
	static int[] weight = new int[MAX_E];
	static int edgeSize;

	static int[] dist = new int[MAX_V];

	static void init(int v) {
		for (int i = 0; i <= v; i++) {
			head[i] = -1;
			dist[i] = Integer.MAX_VALUE;
		}
		edgeSize = 0;
		heapSize = 0;
	} // 초기화

	static void addEdge(int u, int v, int w) {
		to[edgeSize] = v;
		weight[edgeSize] = w;
		next[edgeSize] = head[u];
		head[u] = edgeSize++;
	}

	static final int MAX_Q = 100005;
	static int[] heapW = new int[MAX_Q];
	static int[] heapV = new int[MAX_Q];
	static int heapSize;
	static int poppedW, poppedV;

	static void hq_push(int w, int v) {
		int cur = ++heapSize;

		heapW[heapSize] = w;
		heapV[heapSize] = v;

		while (cur > 1) {
			int parent = cur / 2;

			if (heapW[cur] >= heapW[parent])
				break;

			int tmpW = heapW[cur];
			heapW[cur] = heapW[parent];
			heapW[parent] = tmpW;

			int tmpV = heapV[cur];
			heapV[cur] = heapV[parent];
			heapV[parent] = tmpV;

			cur = parent;
		}
	} // 힙 push
	
	static boolean hq_pop() {
		if(heapSize == 0) return false;
		poppedW = heapW[1];
		poppedV = heapV[1];
		
		heapW[1] = heapW[heapSize];
		heapV[1] = heapV[heapSize--];
		
		int cur = 1;
		
		while(cur * 2 <= heapSize) {
			int child = cur * 2;
			if(child + 1 <= heapSize && heapW[child + 1] < heapW[child]) {
				child++;
			}
			if(heapW[cur] <= heapW[child]) break;
			
			int tmpW = heapW[cur];
			heapW[cur] = heapW[child];
			heapW[child] = tmpW;
			
			int tmpV = heapV[cur];
			heapV[cur] = heapV[child];
			heapV[child] = tmpV;
			
			cur = child;
		}
		return true;
	} // 힙 pop

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		// N: 도시의 개수, M: 버스의 개수
		StringTokenizer st;
		init(N);
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			addEdge(u, v, w);
		} // 그래프 입력받기

		st = new StringTokenizer(br.readLine());
		int startV = Integer.parseInt(st.nextToken());
		int endV = Integer.parseInt(st.nextToken());
		
		hq_push(0, startV);
		dist[startV] = 0;
		
		while(heapSize != 0) {
			hq_pop();
			int curr_W = poppedW;
			int curr_V = poppedV;
			
			if(dist[curr_V] < curr_W) continue;
			if(curr_V == endV) System.out.println(dist[curr_V]);
			
			for(int i = head[curr_V]; i != -1; i = next[i]) {
				int nextV = to[i];
				int nextW = curr_W + weight[i];
				
				if(dist[nextV] > nextW) {
					dist[nextV] = nextW;
					hq_push(nextW, nextV);
				}
			}
		} // 다익스트라.
	} // 메인문 끝.
}
