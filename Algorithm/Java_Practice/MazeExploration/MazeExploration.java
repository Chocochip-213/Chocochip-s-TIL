package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MazeExploration {
	static final int MAX_Q = 100*100+10;
	static int[] q = new int[MAX_Q];
	static int[][] maze = new int[101][101];
	static int[][] dist = new int[101][101];
	static int head, tail;
	static final int start_idx = 0;
	static int end_idx;
	static final int[] dx = {-1, 1, 0, 0}; // 상하
	static final int[] dy = {0, 0, -1, 1}; // 좌우

	static void q_push(int v) { q[tail] = v; tail = (tail + 1) % MAX_Q; }
	static int q_pop() { int v = q[head]; head = (head + 1) % MAX_Q; return v; }
	static boolean q_isEmpty() { return head == tail; }

	static int bfs(int start, int end, int N, int M) {
		dist[0][0] = 1;
		int er = end / 10000;
		int ec = end % 10000;
		q_push(start);
		while(!q_isEmpty()) {
			int row_idx = q_pop();
			int curr_r = row_idx / 10000;
			int curr_c = row_idx % 10000;
			for(int i = 0; i < 4; i++) {
				int nr = curr_r + dx[i];
				int nc = curr_c + dy[i];
				if((nr < 0) || (nr >= N) || (nc < 0) || (nc >= M)) continue;
				if(maze[nr][nc] == 0) continue; // 길이 아니면 컨티뉴
				if(dist[nr][nc] != 0) continue; // 방문 했었으면 컨티뉴
				dist[nr][nc] = dist[curr_r][curr_c] + 1;
				if(dist[er][ec] != 0) return dist[er][ec];
				q_push((nr * 10000) + nc);
			} // 델타 반복문
		} // q 반복문
		return 0;
	} // bfs 함수

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int result;
		end_idx = (N-1) * 10000 + (M-1);
		head = 0;
		tail = 0;
		// N: 행, M: 열
		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				maze[i][j] = line.charAt(j) - '0';
			} // 열
		} // 행
		result = bfs(start_idx, end_idx, N, M);
		System.out.println(result);
	} // 메인문 끝
}
