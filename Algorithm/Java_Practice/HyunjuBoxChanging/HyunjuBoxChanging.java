package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HyunjuBoxChanging {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		int L, R;
		// T: 테케 수 
		for(int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int [] box = new int[N]; 
			// N개의 상자 생성.
			int Q = Integer.parseInt(st.nextToken());
			// Q회 숫자바꾸기 수행.
			for(int i = 1; i <= Q; i++) { // 1부터 시작(i)
				st = new StringTokenizer(br.readLine());
				// 상자 바꾸기 번호 입력
				L = Integer.parseInt(st.nextToken());
				// L: 시작 상자 번호.
				R = Integer.parseInt(st.nextToken());
				// R: 종료 상자 번호.
				for(int j = L-1; j < R; j++) {
					box[j] = i;
				}
			}
			sb.append("#").append(tc);
			for(int val : box) {
				sb.append(" ").append(val);
			}
			sb.append("\n");
		}// tc 반복문
		System.out.print(sb);
	} // main 문
}
