import java.io.IOException;

public class Main {
	static int[] res = new int[2];
	static int[][] map;

	public static void main(String[] args) throws IOException {
		int N = readInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = readInt();
			}
		}
		cutPaper(0, 0, N);
		System.out.printf("%d\n%d", res[0], res[1]);
	}

	private static void cutPaper(int y, int x, int size) {
		boolean flag = true;

		loop: for (int i = y; i < y + size; i++) {
			for (int j = x; j < x + size; j++) {
				if (map[y][x] != map[i][j]) {
					flag = false;
					break loop;
				}
			}
		}

		if (flag) { // 다 같은 색이면
			res[map[y][x]]++;
			return;
		} else {
			int t = size / 2;
			cutPaper(y, x, t);
			cutPaper(y + t, x, t);
			cutPaper(y, x + t, t);
			cutPaper(y + t, x + t, t);
		}
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
