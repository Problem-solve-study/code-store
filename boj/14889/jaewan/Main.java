// 13004 KB, 84 ms
/*
 * N 짝수 명이 축구하려고 모였다.
 * N/2명으로 이루어진 스타트 팀과 링크 팀으로 사람을 나누기.
 * 
 * 능력치 S[i][j]는 i번과 j번이 같은 팀에 속할 때, 팀에 더해지는 능력치.
 * S[i][j]와 S[j][i]가 다를 수도 있다. i와 j가 같은 팀에 속했을 때, 능력치는 둘 다 더함.
 * 
 * 재미를 위해 두 팀 능력치의 차이 최솟값을 출력
 * 
 * 4 <= N <= 20, 1 <= S[i][j] <= 100
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
	static int N, minDiff = Integer.MAX_VALUE;
	static int[] sumRow, sumCol;
	static int[][] S;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);

		N = nextInt(st);
		int totalSum = 0;
		sumRow = new int[N];
		sumCol = new int[N];
		S = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				S[i][j] = nextInt(st);
				sumRow[i] += S[i][j];
				sumCol[j] += S[i][j];
				totalSum += S[i][j];
			}

		makeComb(0, -1, totalSum);
		System.out.println(minDiff);
	}

	static void makeComb(int cnt, int idx, int remainSum) {
		if (cnt == N / 2) {
			minDiff = Math.min(minDiff, Math.abs(remainSum));
			return;
		}

		for (int i = idx + 1; i < N; i++)
			makeComb(cnt + 1, i, remainSum - sumRow[i] - sumCol[i]); // i 번째를 선택
	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

}
