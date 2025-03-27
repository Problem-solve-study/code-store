//문제: BOJ 23048번 자연수 색칠하기
//메모리: 204 KB
//시간: 27096 ms

/*
 *  cnt는 (n 이하의 소수(prime number)의 개수)+1이다.
 *  소수의 배수들은 소수와 같은 색으로 칠한다.
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
		int[] color = new int[n + 1];
		int cnt = 1;
		color[1] = 1; //1은 무조건 1로 칠한다.
		int limit = (int)Math.sqrt(n);
		for (int i = 2; i <= n; i++) {
			if (color[i] == 0) { //색이 칠해지지 않았다면 -> 소수
				color[i] = ++cnt; //cnt 증가 후 색칠
				if (i > limit) //만약 i가 n의 제곱근보다 크다면 i 제곱 이상의 배수는 n보다 크기 때문에 넘어간다.
					continue;
				for (int j = i * i; j <= n; j += i) //i제곱 이상의 i의 배수를 i와 같은 색으로 칠하기
					color[j] = cnt;
			}
		}
		sb.append(cnt).append('\n');
		for (int i = 1; i <= n; i++)
			sb.append(color[i]).append(' ');
		System.out.print(sb);
	}
}
