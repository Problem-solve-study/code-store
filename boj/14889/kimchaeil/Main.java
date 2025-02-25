//문제: 14889번 스타트링크
//메모리: 14568 KB
//시간: 228 ms

import java.io.*;

public class Main {
	static StreamTokenizer st;
	static int n;
	static int[][] synergies;
	static int teamA;
	static boolean[] visited;
	static int limit;
	static int result;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);
		result = Integer.MAX_VALUE;
		n = nextInt();
		synergies = new int[n][n];
		visited = new boolean[1 << n];
		limit = (1 << n) - 1;
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				synergies[i][j] = nextInt();
		combination(0, 0);
		System.out.println(result);
	}

	static int combination(int depth, int start) {
		if (start - depth > n / 2) {
			return 0;
		}
		if (depth == n / 2) {
			if (!visited[teamA]) {
				visited[teamA] = true;
				visited[~teamA & limit] = true;
				result = Math.min(calScoreDiff(), result);
			}
			return 0;
		}
		int mask = 1 << start;
		for (int i = start; i < n; i++) {
			teamA |= mask;
			combination(depth + 1, i + 1);
			teamA ^= mask;
			mask <<= 1;
		}
		return 0;
	}

	static int calScoreDiff() {
		int scoreDiff = 0;
		for (int i = 0; i < n - 1; i++) {
			boolean check = (teamA & (1 << i)) != 0;
			for (int j = i + 1; j < n; j++) {
				if (!(check ^ (teamA & (1 << j)) != 0)) {
					scoreDiff += check ? synergies[i][j] + synergies[j][i] : -(synergies[i][j] + synergies[j][i]);
				}
			}
		}
		return Math.abs(scoreDiff);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}