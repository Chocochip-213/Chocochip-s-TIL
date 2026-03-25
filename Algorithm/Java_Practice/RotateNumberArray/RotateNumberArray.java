package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class RotateNumberArray {

	public static int[][] rotate(int[][] grid, int N) {
		int[][] rotate_arr = new int[N][N];
		for (int col = 0; col < N; col++) {
			for (int row = N - 1; row >= 0; row--) {
				rotate_arr[col][N - 1 - row] = grid[row][col];
			} // 행 역순회
		} // 열 순회
		return rotate_arr;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {

			int N = Integer.parseInt(br.readLine());
			int[][] arr = new int[N][N];
			int[][][] result = new int[3][N][N];
			StringTokenizer st;

			for (int i = 0; i < N; i++) { // 숫자 배열 입력받기.
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				} // 열 순회
			} // 행 순회


			result[0] = rotate(arr, N); // 90도
			result[1] = rotate(result[0], N); // 180도
			result[2] = rotate(result[1], N); // 270도

			sb.append("#").append(tc).append(" ").append("\n");
			for (int row = 0; row < N; row++) {
				for (int arr_idx = 0; arr_idx < 3; arr_idx++) {
					for (int col = 0; col < N; col++) {
						sb.append(result[arr_idx][row][col]);
					}
					sb.append(" ");
				}
				sb.append("\n");
			}
		} // tc문 끝
		System.out.print(sb);
	} // main문 끝
}
