import java.io.*;
import java.math.BigInteger;

/*
 * 11776KB, 80ms
 * 
 * 1, 2, 3 -> 4개를 뽑을 수가 없으므로 반드시 0
 * 4 -> 숫자가 13개가 있으므로 13개
 * 5 -> 13C1 * 48C1 (포카드 13개 중 1개를 고르고, 나머지 카드 중 1장을 고르기)
 * 6 -> 13C1 * 48C2
 * 7 -> 13C1 * 48C3
 * 8 -> 13C1 * 48C4 - 13C2 * 44C0 (포카드 13개 중 1개를 고르고 나머지 카드 중 4장을 고른 후 중복 카운팅된 경우 빼주기)
 * .
 * .
 * 12 -> 13C1 * 48C8 - 13C2 * 44C4 + 13C3 * 40C0 (포함 배제의 원리로 두 번 빠진 경우를 더해줌)
 * 
 * 이런 식으로 식을 구성하고 포함 베재의 원리에 따라 값을 빼고 더해주고를 반복
 * 값이 커질 것 같아 BigInteger 사용
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static final int MOD = 10_007;
	
	public static void main(String[] args) throws Exception {
		int n = nextInt();
		if (n == 1 || n == 2 || n == 3) {
			System.out.print(0);
		} else {
			BigInteger base = BigInteger.valueOf(13).multiply(combination(48, n - 4));
			for (int i = 1; i < n / 4; i++) {
				if (i % 2 == 1) {
					BigInteger sub = combination(13, (i + 1)).multiply(combination(48 - (4 * i), n - (4 * (i + 1))));
					base = base.subtract(sub);
				} else {
					BigInteger plus = combination(13, (i + 1)).multiply(combination(48 - (4 * i), n - (4 * (i + 1))));
					base = base.add(plus);
				}
			}
			System.out.print(base.mod(BigInteger.valueOf(MOD)));
		}
	}
	
	static BigInteger combination(int n, int r) {
		BigInteger a = BigInteger.valueOf(1);
		BigInteger b = BigInteger.valueOf(1);
		for (int i = 0; i < r; i++) {
			a = a.multiply(BigInteger.valueOf(n - i));
		}
		for (int i = r; i >= 1; i--) {
			b = b.multiply(BigInteger.valueOf(i));
		}
		
		return a.divide(b);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
