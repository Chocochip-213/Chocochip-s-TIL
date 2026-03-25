package aa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PasswordGenerator {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = 10;
		int Max = 9;
		int[] cirQueue = new int [Max];

		for(int tc = 1; tc <= T; tc++) {
			int head = 0;
			int tail = 7;
			int minusVal = 1;
			int inputTc = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i <Max-1; i++) cirQueue[i] = Integer.parseInt(st.nextToken());
			// 처음 큐에 숫자 넣기.
			while(true) {
				if(minusVal > 5) minusVal = 1;
				cirQueue[head] -= minusVal;
				minusVal++;
				head++;
				tail++;
				if(head >= Max - 1) head %= Max - 1;
				if(tail >= Max - 1) tail %= Max - 1;
				if(cirQueue[tail] <= 0) {
					cirQueue[tail] = 0;
					break;
				}
			}
			sb.append("#").append(tc).append(" ");
			while(head != tail) {
				sb.append(cirQueue[head]).append(" ");
				head++;
				if(head >= Max - 1) head %= 8;
			}
			sb.append(cirQueue[tail]).append(" ");
			sb.append("\n");
		} // tc문 끝
		System.out.print(sb);

	} // 메인 문 끝
}
