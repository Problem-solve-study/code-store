import java.io.*;
import java.util.*;

/*
 * 46720KB, 308ms
 * 
 * x를 a로, y를 b - x로 fix 시켜두자.
 * x <= y 및 y % x == 0이라면 
 * gcd는 반드시 a가 되고 (x는 a, b는 a * n의 꼴이므로)
 * x + y는 반드시 b가 된다. (x + b - x = b이므로)
 * 
 * 따라서 저 두 조건을 검사해주면 된다.
 * y % x == 0을 안걸었다가 1틀
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int Q = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		while (Q --> 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long a = Long.parseLong(st.nextToken());
			long b = Long.parseLong(st.nextToken());
			
			long x = a;
			long y = b - x;
			sb.append(y >= x && y % x == 0 ? 1 : 0).append('\n');
		}
		System.out.print(sb);
	}
}