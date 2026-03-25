package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MaxConsecutiveOnes {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			// N: 수열의 길이
			String sequence = br.readLine();
			// 수열입력받기
			int one_cnt = 0;
			int max_cnt = 0;
			
			for (int i = 0; i < sequence.length(); i++) {
				if (sequence.charAt(i) == '1') {
					one_cnt++;
					if (max_cnt < one_cnt) max_cnt = one_cnt;
				}
				else one_cnt = 0;
			}// 수열 순회문
			
			sb.append("#").append(tc).append(" ").append(max_cnt).append("\n");
			
		} // tc 끝
		System.out.print(sb);
	} // main 문
	
	
}
