//문제: 옥수수밭
//메모리: 46868 KB
//시간: 652 ms

/*
 * 수확할 수 있는 위치의 옥수수들 중 가장 가치가 높은 것을 수확한다는 조건을 보고 우선순위 큐를 떠올렸다
 * 우선순위 큐를 이용하기 위해서는 옥수수의 위치,가치를 가지는 클래스를 만들고 가치를 기준으로 정렬되도록 해야한다고 생각했다
 * 옥수수를 수확하면 수확한 옥수수 주변의 옥수수도 고려해야 하므로 4방탐색을 하는 BFS를 생각했다
 */

import java.io.*;
import java.util.*;

class Corn {
	int i, j, value;

	public Corn(int i, int j, int value) {
		this.i = i;
		this.j = j;
		this.value = value;
	}
}

public class Main {
	static StreamTokenizer st;
	static int[][] d = { { 1, 0, -1, 0 }, { 0, 1, 0, -1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);
		StringBuilder sb = new StringBuilder();
		int n = nextInt(), m = nextInt();
		int[][] map = new int[n][m];
		boolean[][] visited = new boolean[n][m];
		Queue<Corn> pq = new PriorityQueue<>((a, b) -> b.value - a.value);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				map[i][j] = nextInt();
				if (i == 0 || j == 0 || i == n - 1 || j == m - 1) { //입력받을 때 가장자리면 우선순위 큐에 미리 넣기
					pq.add(new Corn(i, j, map[i][j]));
					visited[i][j] = true;
				}
			}
		}
		int k = nextInt();
		for (int i = 0; i < k; i++) { //k번만 반복하면 된다
			Corn now = pq.poll();
			int x = now.i;
			int y = now.j;
			for (int dir = 0; dir < 4; dir++) { //4방 탐색
				int ni = x + d[0][dir];
				int nj = y + d[1][dir];
				if (ni >= 0 && nj >= 0 && ni < n && nj < m && !visited[ni][nj]) { //범위 내에 있는 방문하지 않는 옥수수에 대해
					pq.add(new Corn(ni,nj,map[ni][nj])); //우선순위 큐에 넣기
					visited[ni][nj] = true;
				}
			}
			sb.append(x+1).append(' ').append(y+1).append('\n'); //문제에서 좌표가 1,1부터 시작하므로 두 좌표값에 1을 더한다
		}
		System.out.println(sb);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}