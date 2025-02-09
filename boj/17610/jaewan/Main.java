// 127348 KB, 236 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int k = Integer.parseInt(br.readLine()); // 추의 개수
		int S = 0;
		boolean[] res = new boolean[k * 200000 + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		Queue<Entry> queue = new LinkedList<>();
		queue.offer(new Entry(0, 0));
		for (int i = 1; i <= k; i++) {
			int g = Integer.parseInt(st.nextToken()); // 추의 무게
			S += g; // 모든 추 무게의 합 S
			while (!queue.isEmpty()) {
				if (queue.peek().idx == i) // 이전 버전 추 사용한 결과까지만
					break;
				Entry e = queue.poll();
				queue.offer(new Entry(e.weight, i)); // i 번째 추 미사용
				queue.offer(new Entry(e.weight + g, i)); // 우측에 달기
				queue.offer(new Entry(e.weight - g, i)); // 좌측에 달기
			}
		}
		for (Entry e : queue) {
			if (e.weight < 0)
				continue;
			res[e.weight] = true;
		}
		int cnt = 0;
		for (int i = 1; i <= S; i++) {
			if (!res[i])
				cnt++;
		}
		System.out.println(cnt);
	}

	static class Entry {
		int weight;
		int idx;

		Entry(int weight, int idx) {
			this.weight = weight;
			this.idx = idx;
		}
	}
}
