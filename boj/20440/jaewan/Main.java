
/*
 * 모기들의 방 입장, 퇴장 시각이 주어짐.
 * 모기들이 가장 많이 있는 시간대와 해당 시간대에 모기가 몇 마리 있는지 출력.
 * 모기의 마릿수 1 <= N <= 1,000,000
 * 입장 시각, 퇴장 시각 0 <= T <= 2,100,000,000
 * 
 * 구간 시작하면 마릿수가 +1, 구간이 끝나면 마릿수가 -1.
 * 구간을 정렬해 O(N log N)으로 해결  
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		TreeMap<Integer, Integer> events = new TreeMap<>(); // TreeMap은 Key 기준으로 자동 정렬된다.
		for (int i = 0; i < N; i++) {
			String[] inputSE = br.readLine().split(" ");
			int S = Integer.parseInt(inputSE[0]);
			int E = Integer.parseInt(inputSE[1]);
			events.put(S, events.getOrDefault(S, 0) + 1); // 구간 시작은 모기 수가 +1 된다.
			events.put(E, events.getOrDefault(E, 0) - 1); // 구간 끝에서는 모기 수가 -1 된다.
		}

		int cur = 0, max = 0;
		int maxStart = 0, maxEnd = 0;
		boolean isMaxInterval = false;
		for (Entry<Integer, Integer> e : events.entrySet()) {
			int change = e.getValue();

			cur += change;
			if (change > 0) {
				if (max < cur) { // 최댓값 갱신되면, 구간 시작 기록
					max = cur;
					maxStart = e.getKey();
					isMaxInterval = true;
				}
			} else if (change < 0) {
				if (max + e.getValue() == cur && isMaxInterval) { // 최댓값 구간일때, max 였다가 감소했으면 구간 끝 기록
					maxEnd = e.getKey();
					isMaxInterval = false;
				}
			}
		}
		System.out.printf("%d\n%d %d\n", max, maxStart, maxEnd);
	}
}