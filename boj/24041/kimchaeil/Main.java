//문제: BOJ 24041 성싶당 밀키트
//메모리: 36680 KB
//시간: 1176 ms

/*
 * 중요하지 않은 재료는 최대한 많이 빼는 것이 좋다
 * 임의의 날이 지났을 때, 먹을 수 있는지 없는지 구하는 것은 쉽다.
 * 이를 이용해 매개변수 탐색을 한다.
 * 최대값을 구하는 것이니 upperbound
 */

import java.io.*;
import java.util.*;

public class Main {
	static class Ingredient {
		int S, L;
		boolean O;

		public Ingredient(int S, int L, int O) {
			this.S = S;
			this.L = L;
			this.O = O == 1;
		}

		public long getVirus(int x) {
			return (long) S * Math.max(1, x - L);
		}
	}

	public static void main(String[] args) throws IOException {
		int N = nextInt(), G = nextInt(), K = nextInt();
		List<Ingredient> list = new ArrayList<>();

		for (int i = 0; i < N; i++)
			list.add(new Ingredient(nextInt(), nextInt(), nextInt()));

		int left = 0, right = Integer.MAX_VALUE;
		while (left < right) {
			int mid = left + ((right - left) >> 1);
			list.sort((a, b) -> { // mid일 후 바이러스 수 내림차순 정렬
				long result = b.getVirus(mid) - a.getVirus(mid);
				if (result < 0)
					return -1;
				else if (result > 0)
					return 1;
				else
					return 0;
			});
			long virus = -G;
			int cnt = 0;
			for (Ingredient ingredient : list) {
				if (ingredient.O && cnt < K) { // 중요하지 않으면서 아직 뺄 수 있으면 스킵
					cnt++;
					continue;
				}
				virus += ingredient.getVirus(mid); // 바이러스 누적
			}
			if (virus <= 0) // 바이러스가 임계치 이하면 left 이동
				left = mid + 1;
			else // 바이러스가 임계치 초과면 right 이동
				right = mid;
		}
		System.out.println(left - 1);
	}

	static int nextInt() throws IOException {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
