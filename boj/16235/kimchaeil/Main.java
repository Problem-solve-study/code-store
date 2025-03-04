//문제: BOJ 16235번 나무 재테크
//메모리: 304476 KB
//시간: 1412 ms

/*
 * 봄,여름,가을,겨울이 1년이므로 사계절을 구현하고 이를 k만큼 반복
 * 처음에는 봄에 한 칸에 여러 개의 나무가 있을 때, 나이가 어린 나무부터 양분을 먹으므로 우선순위큐를 사용했다
 * 하지만 위 방법으로는 시간 초과가 나서 ArrayList를 사용하고 필요할 때만 정렬하는 방법으로 바꾸었더니 오래걸리긴 하지만 통과했다
 */

import java.io.*;
import java.util.*;

class Tree {
	int i, j, age;
	boolean isDead = false;

	public Tree(int i, int j, int age) {
		this.i = i;
		this.j = j;
		this.age = age;
	}
}

public class Main {
	static StreamTokenizer st;
	static int n;
	static int[][] map;
	static int[][] map2;
	static int[][] d = { { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 }, { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 } };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);

		n = nextInt();
		int m = nextInt(), k = nextInt();
		map = new int[n + 1][n + 1];
		map2 = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = 5;
				map2[i][j] = nextInt();
			}
		}

		List<Tree> list = new ArrayList<>();

		for (int i = 0; i < m; i++) {
			list.add(new Tree(nextInt(), nextInt(), nextInt()));
		}

		for (int i = 0; i < k; i++) {
			list = simul(list);
		}
		System.out.println(list.size());

	}

	static List<Tree> simul(List<Tree> list) {
		Collections.sort(list,(a,b)->a.age-b.age);
		List<Tree> temp = new ArrayList<>();

		for(Tree tree : list) {
			if (map[tree.i][tree.j] >= tree.age) {
				map[tree.i][tree.j] -= tree.age++;
			} else {
				tree.isDead = true;
			}
		}
		
		for (Tree tree : list) {
			if (tree.isDead) {
				map[tree.i][tree.j] += (tree.age >> 1);
			}
			else {
				if (tree.age % 5 == 0) {
					for (int dir = 0; dir < 8; dir++) {
						int ni = tree.i + d[dir][0];
						int nj = tree.j + d[dir][1];
						if (ni > 0 && ni <= n && nj > 0 && nj <= n) {
							temp.add(new Tree(ni, nj, 1));
						}
					}
				}
				temp.add(tree);
			}
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] += map2[i][j];
			}
		}

		return temp;
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
