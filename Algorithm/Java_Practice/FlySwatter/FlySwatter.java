package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlySwatter {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;

		for (int tc = 1; tc < T + 1; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			// N: NxN 그리드
			int M = Integer.parseInt(st.nextToken());
			// M: MxM 파리채
			int max_kill = Integer.MIN_VALUE;
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++) { // 처음 그리드 입력받기.
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int base_r = 0; base_r < N - M + 1; base_r++) {
				for (int base_c = 0; base_c < N - M + 1; base_c++) {
					int buffer = 0;
					for (int row = base_r; row < base_r + M; row++) {
						for (int col = base_c; col < base_c + M; col++) {
							buffer += arr[row][col];
						} // 파리채 열
					} // 파리채 행
					if (max_kill < buffer)
						max_kill = buffer;

				} // 베이스 열
			} // 베이스 행

			sb.append("#").append(tc).append(" ").append(max_kill).append("\n");

		} // tc문 끝
		System.out.print(sb);
	} // main문 끝
}
