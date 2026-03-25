package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SupplyRoute {
	static final int[] dx = { -1, 1, 0, 0 };
	static final int[] dy = { 0, 0, -1, 1 };

	static final int MAX_Q = 20005;
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

			if (heapW[parent] <= heapW[cur])
				break;

			int tmpW = heapW[cur];
			heapW[cur] = heapW[parent];
			heapW[parent] = tmpW;

			int tmpV = heapV[cur];
			heapV[cur] = heapV[parent];
			heapV[parent] = tmpV;

			cur = parent;
		}
	} // 힙큐 push

	static boolean hq_pop() {
		if (heapSize == 0)
			return false;
		poppedW = heapW[1];
		poppedV = heapV[1];

		heapW[1] = heapW[heapSize];
		heapV[1] = heapV[heapSize--];

		int cur = 1;

		while (cur * 2 <= heapSize) {
			int child = cur * 2;

			if (child + 1 <= heapSize && heapW[child + 1] < heapW[child]) {
				child++;
			}
			if (heapW[cur] <= heapW[child])
				break;

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
		int T = Integer.parseInt(br.readLine());
		// 테케 수

		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			int[][] map = new int[N][N];
			int[][] dist = new int[N][N];
			int start_idx = 0;
			int end_idx = N * 100 + N;
			heapSize = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					dist[i][j] = Integer.MAX_VALUE;
				}
			} // 다익스트라 거리값 INF 초기화

			for (int i = 0; i < N; i++) {
				String buffer = br.readLine();
				for (int j = 0; j < N; j++) {
					int val = buffer.charAt(j) - '0';
					map[i][j] = val;
				} // 열
			} // 행

			hq_push(0, start_idx);
			dist[0][0] = 0;

			while (heapSize != 0) {
				hq_pop();
				int currVx = poppedV / 100;
				int currVy = poppedV % 100;
				int currW = poppedW;

				if (currW > dist[currVx][currVy])
					continue;

				for (int i = 0; i < 4; i++) {
					int nx = currVx + dx[i];
					int ny = currVy + dy[i];
					if ((nx < 0) || (nx >= N) || (ny < 0) || (ny >= N))
						continue;
					int nextV = nx * 100 + ny;
					int nextD = currW + map[nx][ny];
					if(dist[nx][ny] > nextD) {
						dist[nx][ny] = nextD;
						hq_push(nextD, nextV);
					}
				}
			} // 다익스트라 시작.

			int endX = end_idx / 100 - 1;
		    int endY = end_idx % 100 - 1;
			sb.append("#").append(tc).append(" ").append(dist[N-1][N-1]).append("\n");
		} // tc 문 끝
		System.out.print(sb);
	} // main 문
}
