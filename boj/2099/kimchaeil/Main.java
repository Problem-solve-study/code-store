//문제: BOJ 2099번 The game of death
//메모리: 62872 KB
//시간: 468 ms

/*
 * 행렬 거듭 제곱
 * n이 200이므로 인접 행렬로 표현 가능하다.
 * 인접 행렬을 지목 횟수로 했을 때 k제곱하면 k번째 지목 후 임의의 사람에서 시작하여 임의의 사람이 지목 가능한 경우의 수가 나온다.
 * 하지만 이 문제는 경우의 수를 구하는 것이 아닌 지목 당할 수 있는가를 묻고 있기 때문에
 * true or false로 판단하면 된다.
 */

import java.io.*;

class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String args[]) throws Exception {
		int n = nextInt(), k = nextInt(), m = nextInt();
		boolean[][] graph = new boolean[n + 1][n + 1];
		//graph[i][j]는 i를 시작으로 j가 지목 될 수 있는가
		for (int i = 1; i <= n; i++) { //인접 행렬로 저장
			graph[i][nextInt()] = true;
			graph[i][nextInt()] = true;
		}
		graph = Pow(graph, k); //k 제곱
		StringBuilder sb = new StringBuilder();
		while (m-- > 0)
			sb.append(graph[nextInt()][nextInt()] ? "death\n" : "life\n");
		System.out.print(sb);
	}

	public static boolean[][] Pow(boolean[][] arr, int k) { //행렬 제곱
		if (k == 1)
			return arr;
		boolean[][] result = Pow(arr, k >> 1);
		result = mult(result, result);
		if ((k & 1) == 1)
			result = mult(result, arr);
		return result;
	}

	public static boolean[][] mult(boolean[][] arr1, boolean[][] arr2) { //행렬 곱
		int length = arr1.length;
		boolean[][] result = new boolean[length][length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				for (int k = 0; k < length; k++) {
					if (arr1[i][k] && arr2[k][j]) {
						//and연산들의 or연산이 결과이기 때문에 중간에 한번이라도 and연산의 결과가 true가 나오면 해당 칸은 true다
						result[i][j] = true;
						break;
					}
				}
			}
		}
		return result;
	}

	static int nextInt() throws IOException {
		int c, n;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
