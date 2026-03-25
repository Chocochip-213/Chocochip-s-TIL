package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 처음 익은 토마토들을 큐에 먼저 다 집어넣은뒤에 마지막 그리드에서 최대 dist 값 출력하면 될거같음.

public class TomatoRipeningDays {

	static int[][] grid = new int[1001][1001];
	static int[][] dist = new int[1001][1001];

	static final int MAX_Q = 1001 * 1001 + 10;
	static int[] q = new int[MAX_Q];
	static int head, tail;
	static int maxValue = Integer.MIN_VALUE;
	static int Unripe; // 안익은 토마토 개수
	static final int[] dx = { -1, 1, 0, 0 }; // 상하
	static final int[] dy = { 0, 0, -1, 1 }; // 좌우

	static void q_push(int v) {
		q[tail] = v;
		tail = (tail + 1) % MAX_Q;
	}

	static int q_pop() {
		int v;
		v = q[head];
		head = (head + 1) % MAX_Q;
		return v;
	};

	static boolean q_isEmpty() {
		return tail == head;
	};

	static int bfs(int N, int M) {

		while (!q_isEmpty()) {
			int raw_xy = q_pop(); // 디코딩 전 좌표.
			int curr_x = raw_xy >> 10;
			int curr_y = raw_xy & 1023;
			for (int i = 0; i < 4; i++) {
				int nx = curr_x + dx[i];
				int ny = curr_y + dy[i];
				if ((nx < 0) || (nx >= N) || (ny < 0) || (ny >= M))
					continue;
				// 그리드 벗어나면 continue
				if ((grid[nx][ny] == -1) || (dist[nx][ny] >= 1))
					continue;
				// 이미 익었거나 없다면 continue
				dist[nx][ny] = dist[curr_x][curr_y] + 1;
				if (maxValue < dist[nx][ny])
					maxValue = dist[nx][ny];
				Unripe--; // 익었으니 안익은 토마토 갯수에서 - 1 수행.
				q_push((nx << 10) | ny);
			} // 델타 for 문
		} // 큐 while 문

		if (Unripe > 0) return -1;
		else return maxValue - 1;

	} // bfs 함수 리턴

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		head = 0;
		tail = 0;
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		// N: 행, M: 열

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
				if (grid[i][j] >= 1) { // 처음받았을 때 토마토가 익은 좌표라면.
					int encode_xy = (i << 10) | j;
					dist[i][j] = 1;
					maxValue = 1;
					q_push(encode_xy); // 큐에 시작지점 넣기.(다중 시작점)
				} else if (grid[i][j] == 0)
					Unripe++; // 안익은 토마토 개수 추가.
			} // 토마토 입력 열
		} // 토마토 입력 행
		int result = bfs(N, M);
		System.out.println(result);
	} // 메인문 끝
} // 클래스 끝
