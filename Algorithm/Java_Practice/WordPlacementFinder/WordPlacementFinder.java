package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class WordPlacementFinder {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			 st = new StringTokenizer(br.readLine());
			 int N = Integer.parseInt(st.nextToken());
			 // N: 가로 세로 길이.
			 int K = Integer.parseInt(st.nextToken());
			 // K: 단어의 길이.
			 int [][] arr = new int[N][N];
			 for(int i = 0; i < N; i++) {
				 st = new StringTokenizer(br.readLine());
				 for(int j = 0; j < N; j++) {
					 arr[i][j] = Integer.parseInt(st.nextToken());
				 } // 열 for문
			 } // 행 for문
			 int wordPlace = 0; // 단어가 들어갈 수 있는 자리의 수.
			 for(int i = 0; i < N; i++) {
				 int rCnt = 0, cCnt = 0;
				 for(int j = 0; j < N; j++) {
					 if(arr[i][j] == 1) {
						 rCnt++;
					 } // 가로 흰색통로를 만나면 rCnt ++
					 else if(arr[i][j] != 1) {
						 if(rCnt == K) wordPlace++;
						 rCnt = 0;
					 } // 벽을 만났을때 현재까지 rCnt값이 단어와 같으면 단어수 ++
					 if(arr[j][i] == 1) {
						 cCnt++;
					 }
					 else if(arr[j][i] != 1) {
						 if(cCnt == K) wordPlace++;
						 cCnt = 0;
					 }
				 } // 열 for문
				 if(rCnt == K) wordPlace++;
				 if(cCnt == K) wordPlace++;
				 // 위는 마지막에 벽없이 끝난경우 반영.
			 } // 행 for문
			 sb.append("#").append(tc).append(" ").append(wordPlace).append("\n");
		} // tc문 끝
		System.out.print(sb);
		
	} // 메인문 끝
	
}
