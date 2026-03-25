package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MST_prim {

	static final int MAX_V = 10005;
	static final int MAX_E = 100005 * 2; // 양방향 간선이므로 *2

	// 정적인접리스트를 위한 변수.
	static int[] head = new int[MAX_V];
	static int[] to = new int[MAX_E];
	static int[] next = new int[MAX_E];
	static int[] weight = new int[MAX_E];
	static int edgeCnt;

	// 커스텀 힙을 위한 변수.
	static final int MAX_HEAP = 100005 * 2; // 간선의 최대 개수
	static int[] heapW = new int[MAX_HEAP]; // 가중치 저장
	static int[] heapV = new int[MAX_HEAP]; // 정점 번호 저장
	static int heapSize = 0;
	static int poppedV, poppedW;

	static void init(int V) {
		for (int i = 0; i <= V; i++)
			head[i] = -1;
		edgeCnt = 0;
		heapSize = 0;
	}

	static void hq_push(int w, int v) {
		int cur = ++heapSize;
		heapW[cur] = w;
		heapV[cur] = v;

		// 부모가 나보다 크면 위로 교체(최소 힙)
		while (cur > 1) {
			int parent = cur / 2;
			if (heapW[parent] <= heapW[cur])
				break;

			// Swap (W와 V 둘다)
			int tmpW = heapW[parent];
			heapW[parent] = heapW[cur];
			heapW[cur] = tmpW;
			int tmpV = heapV[parent];
			heapV[parent] = heapV[cur];
			heapV[cur] = tmpV;

			cur = parent; // 끝까지 다 정렬? 하기위해서.
		}
	}

	static boolean hq_pop() {
		if (heapSize == 0)
			return false;

		poppedW = heapW[1];
		poppedV = heapV[1];

		heapW[1] = heapW[heapSize];
		heapV[1] = heapV[heapSize--];

		int cur = 1;
		while (cur * 2 <= heapSize) {
			int child = cur * 2; // 왼쪽 자식 선택

			if (child + 1 <= heapSize && heapW[child + 1] < heapW[child]) {
				// 오른쪽 자식(child + 1)이 존재하고 왼쪽보다 작다면 오른쪽 선택
				child++;
			}

			if (heapW[cur] <= heapW[child])
				break;
			// 내가 자식보다 작거나 같으면 정렬 끝

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

	static void addEdge(int u, int v, int w) {
		to[edgeCnt] = v; // to[엣지카운트] = 도착 정점
		weight[edgeCnt] = w; // weight[엣지카운트] = 가중치
		next[edgeCnt] = head[u]; // next[엣카] = head[출발정점];
		// 1 2 3, 1 3 3
		// to[0] = 2, weight[0] = 3, next[0] = head[1], head[1] = 0 // edgeCnt++ (후행연산)
		// to[1] = 3, weight[1] = 3, next[1] = head[1], head[1] = 1 // edgeCnt++
		head[u] = edgeCnt++;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int V_num = Integer.parseInt(st.nextToken());
		int E_num = Integer.parseInt(st.nextToken());

		init(V_num);

		for (int i = 0; i < E_num; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());

			addEdge(u, v, w);
			addEdge(v, u, w);
		} // 그래프 입력받기.

		boolean[] visited = new boolean[10005];
		long totalWeight = 0;
		int cnt = 0;

		hq_push(0, 1);

		while (heapSize > 0) {
			hq_pop();
			// poppedW, poppedV

			int currV = poppedV;
			int currW = poppedW;

			if (visited[currV])
				continue;

			visited[currV] = true;
			totalWeight += currW;
			cnt++;

			if (cnt == V_num)
				break;

			for (int i = head[currV]; i != -1; i = next[i]) {
				int nextNode = to[i];
				int nextW = weight[i];

				if (!visited[nextNode]) {
					hq_push(nextW, nextNode);
				}
			}

		}

		System.out.println(totalWeight);
	}// main문 끝
}
