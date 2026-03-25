package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MST_prim_prac {

	static final int MAX_V = 10005;
	static final int MAX_E = 200005;

	static int[] head = new int[MAX_V];
	static int[] next = new int[MAX_E];
	static int[] to = new int[MAX_E];
	static int[] weight = new int[MAX_E];
	static int edgeCnt;

	static final int MAX_hq = 200005;

	static int[] heapV = new int[MAX_hq];
	static int[] heapW = new int[MAX_hq];
	static int heapSize;
	static int poppedV, poppedW;

	static void init(int V) {
		for (int i = 0; i <= V; i++) {
			head[i] = -1;
		}
		edgeCnt = 0;
		heapSize = 0;
	}

	static void addEdge(int v, int u, int w) {
		to[edgeCnt] = v;
		weight[edgeCnt] = w;
		next[edgeCnt] = head[u];
		head[u] = edgeCnt++;
	}

	static void hq_push(int w, int v) {
		int cur = ++heapSize;
		heapW[cur] = w;
		heapV[cur] = v;

		while (cur > 1) {
			int parent = cur / 2;
			if (heapW[parent] < heapW[cur])
				break;
			// 여기 조건이 push했을때 조건 우선순위임.
			int tmpW = heapW[cur];
			heapW[cur] = heapW[parent];
			heapW[parent] = tmpW;

			int tmpV = heapV[cur];
			heapV[cur] = heapV[parent];
			heapV[parent] = tmpV;

			cur = parent;
		}
	}

	static boolean hq_pop() {
		if (heapSize == 0)
			return false;

		poppedW = heapW[1];
		poppedV = heapV[1]; // 최소값들 가져오기

		heapW[1] = heapW[heapSize];
		heapV[1] = heapV[heapSize--];

		int cur = 1;

		while (cur * 2 <= heapSize) {
			int child = cur * 2;

			if (child + 1 <= heapSize && heapW[child + 1] < heapW[child]) {
				child++;
			}

			if (heapW[cur] < heapW[child]) {
				break;
			}

			int tmpW = heapW[cur];
			heapW[cur] = heapW[child];
			heapW[child] = tmpW;

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

		int V_num = Integer.parseInt(st.nextToken());
		int E_num = Integer.parseInt(st.nextToken());
		// V_num: 정점개수, E_num: 간선 개수
		init(V_num);

		for (int i = 0; i < E_num; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			addEdge(u, v, w);
			addEdge(v, u, w);
		}
		

		boolean[] visited = new boolean[MAX_V];
		long total_w = 0;
		int cnt = 0;
		hq_push(0, 1);

		while (heapSize != 0) {
			hq_pop();
			int curr_W = poppedW;
			int curr_V = poppedV;

			if (visited[curr_V])
				continue;
			visited[curr_V] = true;
			total_w += curr_W;
			cnt++;

			if (cnt == V_num)
				break;

			for (int i = head[curr_V]; i != -1; i = next[i]) {
				int next_node = to[i];
				int next_weight = weight[i];

				if (!visited[next_node])
					hq_push(next_weight, next_node);

			} // 힙큐 넣기

		} // 힙 와일문
		System.out.println(total_w);
	} // 메인문
}
