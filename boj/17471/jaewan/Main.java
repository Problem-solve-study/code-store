// 13292 KB, 76 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int N, min = Integer.MAX_VALUE;
	static int[] num, sungeogu;
	static int[][] map;
	static boolean[] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		num = new int[N + 1];
		map = new int[N + 1][N + 1];
		String[] input = br.readLine().split(" ");
		for (int i = 1; i <= N; i++)
			num[i] = Integer.parseInt(input[i - 1]);

		for (int i = 1; i <= N; i++) {
			input = br.readLine().split(" ");
			int t = Integer.parseInt(input[0]);
			for (int j = 1; j <= t; j++) {
				map[i][Integer.parseInt(input[j])] = 1;
				map[Integer.parseInt(input[j])][i] = 1;
			}
		}

		// 두 선거구로 나누기
		sungeogu = new int[N + 1];
		sungeogu[1] = 1;
		selectArea(2, 1);
		System.out.println(min == Integer.MAX_VALUE ? -1 : min);
	}

	static int bfs(int s) { // 연결돼 있는 구역 돌면서 합을 구함.
		visit[s] = true;

		int sum = num[s];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(s);
		while (!queue.isEmpty()) {
			int t = queue.poll();
			for (int i = 1; i <= N; i++) {
				if (map[t][i] == 1 && sungeogu[t] == sungeogu[i] && !visit[i]) { // 연결돼 있을 때, 같은 선거구, 방문 안했을 떄
					visit[i] = true;
					queue.offer(i);
					sum += num[i];
				}
			}
		}
		return sum;
	}

	static void selectArea(int d, int cnt) { // cnt는 1 구역의 수
		if (d == N + 1) {
			if (cnt == N)
				return; // 1만 N개

			// 1은 a 선거구
			int bStart = 0;
			for (int i = 1; i <= N; i++)
				if (sungeogu[i] == 2)
					bStart = i;

			visit = new boolean[N + 1];
			int aSum = bfs(1);
			int bSum = bfs(bStart);

			for (int i = 1; i <= N; i++)
				if (!visit[i]) // 연결 안된 구역이 있으면 return
					return;
			min = Math.min(min, Math.abs(aSum - bSum));
			return;
		}

		sungeogu[d] = 1;
		selectArea(d + 1, cnt + 1);

		sungeogu[d] = 2;
		selectArea(d + 1, cnt);
	}
}