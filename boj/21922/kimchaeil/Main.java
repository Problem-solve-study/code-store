//문제: BOJ 21922번 학부 연구생 민상
//메모리: 47176 KB
//시간: 348 ms

/*
 * 재귀 DFS를 이용해 구현
 * +) 재귀 DFS에서 반복문으로 변경 -> 바람이 여러 방향으로 퍼지지 않기 때문
 */

import java.io.*;

public class Main {
	static int n, m;
	static int[] map;
	static boolean[] visited;
	static boolean[][] dirVisited;

	// 상하좌우 순서
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static int result;

	public static void main(String[] args) throws IOException {
		n = nextInt();
		m = nextInt();
		int size = n * m;
		map = new int[size];
		visited = new boolean[size];
		dirVisited = new boolean[4][size];
		int[] acStack = new int[50];
		int sIdx = 0;
		for (int i = 0; i < size; i++) {
			map[i] = nextInt();
			if (map[i] == 9) // 에어컨 위치 스택에 추가
				acStack[sIdx++] = i;
		}
		for (int i = 0; i < sIdx; i++) // 스택에 있는 에어컨들에 대해 4방으로 DFS
			for (int dir = 0; dir < 4; dir++)
				simul(acStack[i], dir);

		System.out.println(result);
	}

	static void simul(int pos, int dir) {
		int y = pos / m;
		int x = pos % m;
		while (true) {
			if (dirVisited[dir][pos]) // 같은 방향으로 pos를 방문했었다면 탐색 중단
				return;
			if (!visited[pos]) // pos에 처음 방문했다면 카운트
				result++;
			visited[pos] = dirVisited[dir][pos] = true; // visited 처리
			switch (map[pos]) { // pos 칸의 정보
			case 1: // 1번 물건
				if (dir > 1) // 좌우 방향이라면 탐색 중단
					return;
				break;
			case 2: // 2번 물건
				if (dir < 2) // 상하 방향이라면 탐색 중단
					return;
				break;
			case 3: // 3번 물건
				dir = dir ^ 3; // 방향을 상(0) <-> 우(3), 하(1) <-> 좌(2) 로 변경
				break;
			case 4: // 4번 물건
				dir = dir ^ 2;// 방향을 상(0) <-> 좌(2), 하(1) <-> 우(3) 로 변경
				break;
			}
			y += dy[dir];
			x += dx[dir];
			if (y < 0 || x < 0 || y >= n || x >= m) // 범위 넘어가면 탐색 중단
				return;
			pos = y * m + x;
		}
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
