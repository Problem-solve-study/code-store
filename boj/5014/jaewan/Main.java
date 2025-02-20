/* 
 * 총 F층, 목표 G층, 현재 S층, 위로 U층, 아래로 D층
 * 1 <= S, G <= F <= 1,000,000, 0 <= U, D <= 1,000,000
 * BFS 로 시작에서 갈 수 있는 층 탐색.
 * 
 * 1-3-5-7-9-10
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int F, G, S, U, D;
	static int[] floors;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
		F = nextInt(st);
		S = nextInt(st);
		G = nextInt(st);
		U = nextInt(st);
		D = nextInt(st);
		floors = new int[F + 1];

		Queue<Integer> q = new LinkedList<>();

		floors[S] = 1;
		q.offer(S);

		while (!q.isEmpty()) {
			int cur = q.poll(); // 현재 층

			int next = cur + U; // U 버튼
			if (next <= F && floors[next] == 0) {
				floors[next] = floors[cur] + 1;
				q.offer(next);
			}

			next = cur - D; // D 버튼
			if (next > 0 && floors[next] == 0) {
				floors[next] = floors[cur] + 1;
				q.offer(next);
			}

		}

		System.out.println(floors[G] > 0 ? floors[G] - 1 : "use the stairs");

	}

	private static int nextInt(StreamTokenizer st) throws IOException {
		st.nextToken();
		return (int) st.nval;
	}

}