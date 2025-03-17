//문제: BOJ 13140번 Hello World!
//메모리: 31452 KB
//시간: 100 ms

/*
 * 브루트 포스로 풀었다.
 * 시간을 줄이기 위해 sum 배열을 함수 내에서 선언하였더니 (100ms/31452KB)가 나왔고
 * 메모리를 줄이기 위해 sum 배열을 static 글로벌 변수로 만드니 (108ms/13592KB)가 나왔다.
 */

import java.io.*;

public class Main {
	static int[] result = new int[7]; // h,e,l,o,w,r,d
	static boolean find = false;
	static int n;
	static boolean[] visited = new boolean[10];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());
		findAnswer(0);
		if (find) {
			sb.append("  ").append(result[0]).append(result[1]).append(result[2]).append(result[2]).append(result[3])
					.append('\n');
			sb.append('+').append(' ').append(result[4]).append(result[3]).append(result[5]).append(result[2])
					.append(result[6]).append('\n');
			sb.append("-------\n");
			sb.append(String.format("%7d", n));
		} else {
			sb.append("No Answer");
		}
		System.out.println(sb);
	}

	static void findAnswer(int depth) {
		if (depth == 7) {
			int ans = 0;
			int[] sum = new int[5];
			sum[0] = result[0] + result[4];
			sum[1] = result[1] + result[3];
			sum[2] = result[2] + result[5];
			sum[3] = result[2] << 1;
			sum[4] = result[3] + result[6];
			for (int a : sum) {
				ans *= 10;
				ans += a;
			}
			if (ans == n) {
				find = true;
			}
			return;
		}
		int start = (depth == 0 || depth == 4) ? 1 : 0;
		for (int i = start; i < 10; i++) {
			if (visited[i])
				continue;
			result[depth] = i;
			visited[i] = true;
			findAnswer(depth + 1);
			visited[i]=false;
			if (find)
				return;
		}
	}
}
