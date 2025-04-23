/**
 * 69152KB	900ms
 * 
 * 좋은 풀이는 아닙니다.
 * 단순 시뮬레이션 코드에 스택으로 경로 단축시키고, 각 경로의 변화에서 일어나는 사이드 이펙트를 전파하는 방식입니다.
 * 예를 들어 A경로 스택이 {1, 2, 5, 10}, B경로 스택이 {3, 4, 5}라고 할 때
 * B경로로 돌을 떨어트리면 5에 돌이 쌓이게 됩니다. 이 때 5를 이동 경로로 하고 있는 A경로에 5지점을 다시 확인해야한다고 전파합니다.
 * 이후에 A경로로 돌을 떨어트리는 경우, 5인 지점까지 스택을 pop해서 재검사합니다.
 * 
 * 코드가 매우매우 더럽기 때문에, 위 개념만 응용하는것이 좋을 것 같습니다. 
 */

import java.io.*;
import java.util.*;

public class Main {
	static int R, C;
	static Node[][] node;
	static char[][] map;
	static List<Deque<Node>> stack;
	static boolean[][] present;
	static List<List<Integer>> update;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		stack = new ArrayList<>();
		update = new ArrayList<>();
		for (int i = 0; i < C; ++i) {
			stack.add(new ArrayDeque<>());
			update.add(new ArrayList<>());
		}

		node = new Node[R][C];
		map = new char[R][C];
		for (int r = 0; r < R; ++r) {
			String line = br.readLine();
			for (int c = 0; c < C; ++c)
				map[r][c] = line.charAt(c);
		}

		for (int c = 0; c < C; ++c) {
			if (map[R - 1][c] == '.')
				node[R - 1][c] = new Node(R - 1, c);
			else
				node[R - 2][c] = new Node(R - 2, c);
		}

		boolean[] flag = new boolean[C];
		for (int r = R - 1; r >= 0; --r) {
			for (int c = 0; c < C; ++c) {
				if (map[r][c] == '.') {
					if (!flag[c]) {
						flag[c] = true;
						node[r][c] = new Node(r, c);
					} else {
						node[r][c] = node[r + 1][c];
					}
				} else if (map[r][c] == 'X') {
					flag[c] = false;
				}
			}
		}

		present = new boolean[C][idCnt + 1];

		int q = Integer.parseInt(br.readLine());
		for (int i = 0; i < q; ++i) {
			int x = Integer.parseInt(br.readLine()) - 1;
			put(x);
		}

		StringBuilder res = new StringBuilder();
		for (int r = 0; r < R; ++r) {
			for (int c = 0; c < C; ++c)
				res.append(map[r][c]);
			res.append('\n');
		}
		System.out.print(res);
	}

	static void put(int c) {
		Deque<Node> cs = stack.get(c);
		List<Integer> ud = update.get(c);

		if (cs.isEmpty()) {
			cs.addLast(node[0][c]);
			present[c][node[0][c].id] = true;
		}
		
		for (int u : ud) {
			if (present[c][u]) {
				while (!cs.isEmpty() && cs.peekLast().id != u) {
					present[c][cs.peekLast().id] = false;
					cs.pollLast();
				}
			}
		}
		ud.clear();

		while (true) {
			Node cur = cs.pollLast();
			int cr = cur.r - cur.size;
			int cc = cur.c;
			if (cur.size == 0) {
				map[cr][cc] = 'O';
				++cur.size;
				cs.addLast(cur);
				for (int i = 0; i < C; ++i) {
					if (i != c && present[i][cur.id]) update.get(i).add(cur.id);
				}
				break;
			} else if (cs.isEmpty()) {
				if (isValid(cr, cc - 1) && map[cr][cc - 1] == '.' && map[cr + 1][cc - 1] == '.') {
					cs.addLast(cur);
					cs.addLast(node[cr][cc - 1]);
					present[c][node[cr][cc - 1].id] = true;
				} else if (isValid(cr, cc + 1) && map[cr][cc + 1] == '.' && map[cr + 1][cc + 1] == '.') {
					cs.addLast(cur);
					cs.addLast(node[cr][cc + 1]);
					present[c][node[cr][cc + 1].id] = true;
				} else {
					map[cr][cc] = 'O';
					++cur.size;
					cs.addLast(cur);

					for (int i = 0; i < C; ++i) {
						if (i != c && present[i][cur.id]) update.get(i).add(cur.id);
					}
					break;
				}
			} else {
				Node prev = cs.peekLast();
				int pr = prev.r - prev.size;
				int pc = prev.c;

				if (cc < pc && map[pr][pc - 1] == '.' && map[pr + 1][pc - 1] == '.') {
					if (isValid(cr, cc - 1) && map[cr][cc - 1] == '.' && map[cr + 1][cc - 1] == '.') {
						cs.addLast(cur);
						cs.addLast(node[cr][cc - 1]);
						present[c][node[cr][cc - 1].id] = true;
					} else if (isValid(cr, cc + 1) && map[cr][cc + 1] == '.' && map[cr + 1][cc + 1] == '.') {
						cs.addLast(cur);
						cs.addLast(node[cr][cc + 1]);
						present[c][node[cr][cc + 1].id] = true;
					} else {
						map[cr][cc] = 'O';
						++cur.size;
						cs.addLast(cur);
						for (int i = 0; i < C; ++i) {
							if (i != c && present[i][cur.id]) update.get(i).add(cur.id);
						}
						break;
					}
				} else if (cc > pc && map[pr][pc + 1] == '.' && map[pr + 1][pc + 1] == '.') {
					if (isValid(cr, cc - 1) && map[cr][cc - 1] == '.' && map[cr + 1][cc - 1] == '.') {
						cs.addLast(cur);
						cs.addLast(node[cr][cc - 1]);
						present[c][node[cr][cc - 1].id] = true;
					} else if (isValid(cr, cc + 1) && map[cr][cc + 1] == '.' && map[cr + 1][cc + 1] == '.') {
						cs.addLast(cur);
						cs.addLast(node[cr][cc + 1]);
						present[c][node[cr][cc + 1].id] = true;
					} else {
						map[cr][cc] = 'O';
						++cur.size;
						cs.addLast(cur);

						for (int i = 0; i < C; ++i) {
							if (i != c && present[i][cur.id]) update.get(i).add(cur.id);
						}
						break;
					}
				} else {
					present[c][cur.id] = false;
				}
			}
		}
	}

	static boolean isValid(int r, int c) {
		return 0 <= r && r < R && 0 <= c && c < C;
	}

	static int idCnt = 0;

	static class Node {
		int id = ++idCnt;
		int r, c;
		int size = 0;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
