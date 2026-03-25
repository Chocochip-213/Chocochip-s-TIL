package aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ViewPointCalculation {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		// 테케 10개
		int skip_building = 2;
		int start_idx = 2;
		// 조망권이 확보된 빌딩이 있다면 우측 두개는 확보안되므로 스킵.
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			// N: 건물의 갯수
			int viewPoint = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			// st: N개의 건물 높이
			int [] building = new int[N];
			for(int i = 0; i < N; i++) building[i] = Integer.parseInt(st.nextToken());
			// 빌딩 배열에 값 넣어주기.
			for(int j = start_idx; j < N-2; j++) {
				int left_bd = building[j-2];
				int left_closer_bd = building[j-1];
				int curr_bd = building[j];
				int right_bd = building[j+2];
				int right_closer_bd = building[j+1];
				if(left_closer_bd < curr_bd && right_closer_bd < curr_bd ) {
					// 양옆 빌딩보다 자기 자신이 높다면.
					if(left_bd < curr_bd && right_bd < curr_bd) {
						// 2칸 양옆 빌딩보다 자기 자신이 높다면.
						int max_building = Math.max(left_bd, Math.max(right_bd, Math.max(left_closer_bd, right_closer_bd)));
						// 조망권 확보세대 구하기 위한 max값 찾기
						viewPoint += curr_bd - max_building;
						j += skip_building; 
						// 빌딩 두칸 넘어가기
					} // 빌딩체크
				}// 빌딩 체크 끝
			}// 빌딩 순회 for 문
			sb.append("#").append(tc).append(" ").append(viewPoint).append("\n");
		} // tc 끝
		System.out.print(sb);
	}// main문 끝
}
