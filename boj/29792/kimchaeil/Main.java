//문제: BOJ 29792 규칙적인 보스돌이
//메모리: 12404 KB
//시간: 88 ms

/*
 * 한 캐릭터가 어떤 보스를 잡든 다른 캐릭터들에게 영향을 주지 않는다.
 * 캐릭터마다 냅색을 하여 얻을 수 있는 최대 메소를 구하고
 * 상위 m개의 합이 답이라고 생각했다.
 * 
 * 최대 용량을 주어진 시간 15분->900초
 * 비용은 ceil(보스 체력 / 캐릭터 데미지)
 * 가치는 보스를 잡았을 때 얻는 메소
 */

import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int n = (int) nextLong(), m = (int) nextLong(), k = (int) nextLong();
		long[] damage = new long[n];
		for (int i = 0; i < n; i++)
			damage[i] = nextLong();

		long[] hp = new long[k];
		long[] value = new long[k];
		for (int i = 0; i < k; i++) {
			hp[i] = nextLong();
			value[i] = nextLong();
		}

		Long[] result = new Long[n];
		long[] dp = new long[901];
		for (int character = 0; character < n; character++) {
			for (int i = 0; i < 901; i++)
				dp[i] = 0;
			for (int boss = 0; boss < k; boss++) {
				long cost = (hp[boss] + damage[character] - 1) / damage[character];
				for (int j = 900; j >= cost; j--)
					dp[j] = Math.max(dp[j], dp[(int) (j - cost)] + value[boss]);
			}
			result[character] = dp[900];
		}
		
		Arrays.sort(result, Comparator.reverseOrder());
		long max = 0;
		while(m-->0)
			max += result[m];
		System.out.println(max);
	}

	static long nextLong() throws IOException {
		long c, n;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
