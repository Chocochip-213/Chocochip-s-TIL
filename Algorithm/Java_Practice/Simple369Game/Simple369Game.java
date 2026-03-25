package aa;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Simple369Game {
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringBuilder print_sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		int buffer = 0;
		// 1~N까지
		for(int n = 1; n <= N; n++) {
			boolean chk_flag = false;
			buffer = n;
			while(buffer != 0) {
				int check_val = buffer % 10;
				if(check_val == 3 || check_val == 6 || check_val == 9) {
					chk_flag = true;
					print_sb.append('-');
				}
				buffer /= 10;
			}
			if(!chk_flag) print_sb.append(n).append(" ");
			// 3,6,9가 포함되지않았다면
			else print_sb.append(" ");
			// 포함되었다면
		} // 수열 반복문
		
		System.out.print(print_sb);
	} // 메인문
}
