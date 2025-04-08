//문제: BOJ 19542번 전단지 돌리기
//메모리: 34316 KB
//시간: 216 ms

/*
 * 노드의 높이가 d보다 크면 방문해야 한다
 */

import java.util.*;
import java.io.*;

public class Main {
	static int d;
	static List<List<Integer>> graph = new ArrayList<>();
	static boolean[] visited;
	static int result = -2;

	public static void main(String[] args) throws IOException {
		int n = nextInt(), s = nextInt();
		d = nextInt();
		visited = new boolean[n + 1];
		for (int i = 0; i <= n; i++)
			graph.add(new ArrayList<>());
		for (int i = 1; i < n; i++) {
			int a = nextInt(), b = nextInt();
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		visited[s] = true;
		result = DFS(s) > d ? result : 0; //시작 노드에서 반환 값이 d보다 작은 경우 result가 -2인 상태이므로 이 경우 0으로
		System.out.print(result);
	}

	static int DFS(int now) {
		int ret = 0;
		for (int next : graph.get(now)) {
			if (!visited[next]) {
				visited[next] = true;
				ret = Math.max(ret, DFS(next)); //자식의 높이 중 최댓값
			}
		}
		if (++ret > d) //now의 높이가 d보다 크면 왕복 횟수 카운트
			result += 2;
		return ret;
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
