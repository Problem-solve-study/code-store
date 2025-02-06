//메모리 11548KB
//시간 64ms

import java.io.*;

public class Main {
	static char[] tokens;
	static int[] partialSum;
	static int result = Integer.MIN_VALUE;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		tokens = br.readLine().toCharArray();
		partialSum = new int[n / 2 + 1];
		for (int i = 0; i < partialSum.length - 1; i++) {
			partialSum[i] = calculate(tokens[2 * i] - '0', tokens[2 * i + 2] - '0', tokens[2 * i + 1]);
		}
		partialSum[partialSum.length - 1] = tokens[n - 1] - '0';
		dfs(2, tokens[0] - '0');
		dfs(4, partialSum[0]);

		System.out.println(result);
	}

	public static void dfs(int idx, int earlyResult) {
		if (idx >= n) {
			result = Integer.compare(earlyResult, result) > 0 ? earlyResult : result;
			return;
		}
		dfs(idx + 2, calculate(earlyResult, tokens[idx] - '0', tokens[idx - 1]));
		dfs(idx + 4, calculate(earlyResult, partialSum[idx / 2], tokens[idx - 1]));
	}

	public static int calculate(int a, int b, char operator) {
		return operator == '+' ? a + b : (operator == '-' ? a - b : a * b);
	}
}
