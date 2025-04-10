//문제: 10836 여왕벌
//메모리: 43164 KB
//시간: 916 ms

/*
 * 문제 조건을 보면
 * 왼쪽 제일 아래 칸에서 시작하여 위쪽으로 가면서 읽고, 제일 위쪽 칸에 도착하면 오른쪽으로 가면서 행의 끝까지 읽는다.
 * 이렇게 읽었을 때, 읽은 값들이 감소하지 않는다고 한다.
 * 그래고 나머지 애벌레들은 왼쪽, 왼쪽 위, 위쪽의 애벌레들 중 가장 많이 자란 애벌레가 자란 만큼 자란다.
 * 위 조건에 따르면 커지는 크기는 항상 위쪽 칸과 같다.
 * 읽은 값이 감소하지 않으므로 바로 위쪽 칸이 가장 많이 자라기 때문이다.
 * 즉, (a,b)가 커지는 크기는 (a,0)과 같다.(단, 0 < a,b < m)
 * 결국, n일이 지난후 1 ~ n-1 열은 열이 같다면 같은 값을 가진다.
 * 그래서 (n-1,0)~(0,0),(1,0)~(n-1,0)의 2*m - 1개의 칸만 계산해주면 된다.
 */

import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		int m = nextInt();
		int n = nextInt();

		int M = (m << 1) - 1;
		int[] larvas = new int[M]; //(n-1,0)~(0,0),(1,0)~(n-1,0)의 2*m - 1개의 칸

		while (n-- > 0) {
			int idx = 0;
			for (int i = 0; i < 3; i++) {
				int count = nextInt();
				while (count-- > 0)
					larvas[idx++] += i;
			}
		}

		StringBuilder temp = new StringBuilder(), sb = new StringBuilder();
		//초기 값이 1이므로 계산된 값에서 1을 더하여 출력
		for (int j = m; j < M; j++) //(1,0)~(n-1,0)은 행이 바뀌어도 고정
			temp.append(larvas[j] + 1).append(' ');
		temp.append('\n');
		for (int i = m - 1; i >= 0; i--)
			sb.append(larvas[i] + 1).append(' ').append(temp);
		System.out.println(sb);
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
