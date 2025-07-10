//문제: BOJ 2248번 이진수 찾기
//시간: 64 ms
//메모리: 11396 KB

/*
 * 주석은 추후에 작성하겠습니다.
 * 설명을 못하겠어요...
 */

public class Main {

	public static void main(String[] args) throws Exception {
		int n = nextInt(), l = nextInt(), i = nextInt()-1;
		int[][] dp = new int[n][l + 1];
		dp[0][0] = 1;
		for (int j = 1; j < n; j++) {
			dp[j][0] = 1;
			int limit = j < l ? j : l;
			for (int k = 1; k <= limit; k++) {
				dp[j][k] = dp[j - 1][k] + dp[j - 1][k - 1];
			}
		}

		StringBuilder sb = new StringBuilder();
		while (n-- > 0) {
			int temp = i;
			for (int k = 0; k <= l && dp[n][k] > 0 && temp >= 0; k++)
				temp -= dp[n][k];
			if (temp >= 0) {
				i = temp;
				l--;
				sb.append('1');
			} else
				sb.append('0');
		}
		System.out.println(sb);
	}

	static int nextInt() throws Exception {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
