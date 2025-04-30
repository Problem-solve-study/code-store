//문제: BOJ 25045 비즈마켓
//메모리: 34564 KB
//시간: 356 ms

/*
 * A를 내림차순, B를 오름차순으로 정렬하고
 * n과 m 중 작은 값만큼 A[i] - B[i]를 누적해준다
 * 만약  A[i] - B[i]가 음수가 되면 거래가 성사되지 않는다는 말이니 이때는 0을 더해줘야 한다
 */

import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		int n = (int) nextLong(), m = (int) nextLong(), k = n > m ? m : n;
		long result = 0;
		long[] A = new long[n], B = new long[m];
		while (n-- > 0)
			A[n] = -nextLong();
		while (m-- > 0)
			B[m] = nextLong();
		Arrays.sort(A);
		Arrays.sort(B);
		while (k-- > 0)
			result -= Math.min(A[k] + B[k], 0);
		System.out.print(result);
	}

	static long nextLong() throws Exception {
		long n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
