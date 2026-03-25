package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BalloonPang {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			// N: 행, M: 열
			int[][] grid = new int[N][M];
			int[] dx = { -1, 1, 0, 0 }; // 상 하
			int[] dy = { 0, 0, -1, 1 }; // 좌 우
			int max_val = Integer.MIN_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					grid[i][j] = Integer.parseInt(st.nextToken());
				} // 열
			} // 행
				// 그리드 입력받기 로직.

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					int buffer = 0; // 합산 버퍼
					buffer += grid[i][j];
					for (int k = 0; k < 4; k++) {
						for (int l = 1; l <= grid[i][j]; l++) { // 처음 풍선만큼 연쇄폭발
						int nx = i + (dx[k] * l) ;
						int ny = j + (dy[k] * l);
						if (!((0 <= nx) && (nx < N) && (0 <= ny) && (ny < M))) continue;
						// 그리드 벗어나면 컨티뉴
						buffer += grid[nx][ny];
						// 아니면 더하기
						}
					} // 델타 수행
					if (max_val < buffer)
						max_val = buffer;
				} // 열
			} // 행
				// 델타탐색
			sb.append("#").append(tc).append(" ").append(max_val).append("\n");
		} // tc문 끝
		System.out.print(sb);

	} // 메인문 끝
}
