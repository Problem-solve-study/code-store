// 16052 KB, 100 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	/*
	 * 수빈이가 동생을 찾는 가장 빠른 시간 몇 초 후인지 구하라 수빈이는 걷거나 순간이동을 한다. 걷기는 1초 후, X-1 또는 X+1로 이동
	 * 순간이동은 1초 후, 2*X로 이동.
	 * 
	 * 수빈이와 동생의 위치가 입력으로 주어짐. 0 <= N <= 100,000 , 0 <= K <= 100,000
	 * 
	 * [문제해결] 수빈이의 위치에서 이동하는 경우는 3가지다. -1, +1, *2 동생의 위치 K를 BFS로 탐색한다. 이미 도달했던 위치에
	 * 나중에 도달하는 경우는 최소 시간이 아니므로 기록하지 않는다.
	 */

	static int[] arr = new int[100001];
	static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);

		arr[N] = 1;
		queue.offer(N);

		while (!queue.isEmpty()) {
			int t = queue.poll();
			if (t == K) { // 동생을 찾았다!
				System.out.println(arr[K] - 1);
				break;
			}

			func(t, t - 1);
			func(t, t + 1);
			func(t, t * 2);
		}
	}

	static void func(int cur, int next) {
		if (0 <= next && next <= 100000 && arr[next] == 0) {
			queue.offer(next);
			arr[next] = arr[cur] + 1;
		}
	}
}