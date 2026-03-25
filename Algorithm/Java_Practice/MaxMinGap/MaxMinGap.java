package aa;
import java.io.FileInputStream;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MaxMinGap {
	public static void main(String[] args) throws IOException{
//		System.setIn(new FileInputStream("input/MaxMinGapInput.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		// T: 테케
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			// N: 양수의 개수
			StringTokenizer st = new StringTokenizer(br.readLine());
			int [] arr = new int[N];
			int min_value = Integer.MAX_VALUE;
			int min_idx = 0;
			int max_value = Integer.MIN_VALUE;
			int max_idx = 0;
			for(int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				if(arr[i] < min_value) {
					min_value = arr[i];
					min_idx = i;
				}
				if(arr[i] >= max_value) {
					max_value = arr[i];
					max_idx = i;
				}
			} // 배열 순회 끝
			sb.append("#").append(tc).append(" ").append(Math.abs(min_idx - max_idx)).append("\n");
		} // tc문 끝
		System.out.print(sb);
	} // main문 끝
}
