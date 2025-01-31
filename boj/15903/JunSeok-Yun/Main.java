//222220KB, 292ms
import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		long res = 0;
		PriorityQueue<Long> pq = new PriorityQueue<>();
		
		for(int i = 0; i < N; i++)
			pq.add(sc.nextLong());

		for (int i = 0; i < M; i++){
			long x = pq.poll();
			long y = pq.poll();
			long sum = x + y;
			for (int k = 0; k < 2; k++)
				pq.add(sum);
		}

		while (!pq.isEmpty())
			res += pq.poll();
		System.out.println(res);
		sc.close();
	}
}