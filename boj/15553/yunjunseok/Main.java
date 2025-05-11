// 25488KB, 296ms
import java.io.*;
import java.util.*;

public class Main {
	static int N, K;
	static int sum, result;
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		int startTime = Integer.parseInt(br.readLine());
		int before = startTime;
		for (int i = 1; i < N; i++){
			int curTime = Integer.parseInt(br.readLine());
			pq.offer(curTime - (before + 1));
			before = curTime;
		}

		sum = 0;
		for (int i = 1; i < K; i++){
			sum += pq.poll();
		}

		result = (before + 1 - startTime) - sum;
		System.out.println(result);
	}
}