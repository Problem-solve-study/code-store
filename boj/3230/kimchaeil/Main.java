//문제: BOJ 3230번 금메달, 은메달, 동메달은 누가?
//시간: 64 ms
//메모리: 11456 KB

/*
 */

public class Main {

	public static void main(String[] args) throws Exception {
		int n = nextInt(), m = nextInt();
		int[] first = new int[m];
		int[] result = new int[3];

		for (int i = 1; i <= n; i++) {
			int rank = nextInt();
			if (rank > m)
				continue;
			for (int j = Math.min(i, m) - 1; j >= rank; j--) {
				first[j] = first[j - 1];
			}
			first[rank - 1] = i;
		}

		for (int i = 0; i < m; i++) {
			int rank = nextInt();
			if (rank > 3)
				continue;
			for (int j = Math.min(i, 2); j >= rank; j--) {
				result[j] = result[j - 1];
			}
			result[rank - 1] = first[m - i - 1];
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(result[0]).append('\n').append(result[1]).append('\n').append(result[2]);
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
