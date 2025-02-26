/*
 * N*N 보드에 갈 수 있는 거리가 적혀져 있다.
 * 가장 왼쪽 위 칸에서, 가장 오른쪽 아래 칸으로 가는 경로의 개수를 출력
 * 경로의 개수는 2^63-1보다 작거나 같다.
 * 점프 할 수 있는 방향은 오른쪽, 아래 두가지
 * 
 * long 변수 사용. 게임 판의 크기 4 <= N <= 100
 * long dp[][] 사용해서, i j 돌면서 갈 수 있는 경로의 수 더해가며 계산.
 * 
 */

import java.io.IOException;

public class BOJ1890_점프 {

	public static void main(String[] args) throws IOException {
		int N = readInt(), t;
		long[][] board = new long[N][N];
		board[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				t = readInt();
				if (t == 0)
					continue;
				if (i + t < N) // 아래
					board[i + t][j] += board[i][j];
				if (j + t < N) // 오른쪽
					board[i][j + t] += board[i][j];
			}
		}
		System.out.println(board[N - 1][N - 1]);
	}

	private static int readInt() throws IOException {
		int c;
		do {
			c = System.in.read();
		} while (c <= 32);
		int n = c & 15;
		c = System.in.read();
		while (c > 47) {
			n = (n << 3) + (n << 1) + (c & 15);
			c = System.in.read();
		}
		return n;
	}
}
