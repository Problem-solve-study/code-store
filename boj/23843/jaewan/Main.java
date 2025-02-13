import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long res = 0;
		PriorityQueue<Integer> electronics = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> concent = new PriorityQueue<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			electronics.add(Integer.parseInt(st.nextToken()));
		}

		while (concent.size() < M && !electronics.isEmpty()) { // 콘센트 비어있으면 충전하기
			concent.add(electronics.poll());
		}

		while (!concent.isEmpty()) {
			int time = concent.poll();
			res += time;

			PriorityQueue<Integer> temp = new PriorityQueue<>();
			while (!concent.isEmpty()) {
				int remainTime = concent.poll() - time;
				if (remainTime > 0)
					temp.add(remainTime);
			}
			concent = temp;

			while (concent.size() < M && !electronics.isEmpty())
				concent.add(electronics.poll());
		}
		System.out.println(res);
	}
}
