import java.util.*;
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n=sc.nextInt(),m=sc.nextInt();
		PriorityQueue<Long> pq = new PriorityQueue<>();
		for(int i=0;i<n;i++) {pq.add(sc.nextLong());}
		for(int i=0;i<m;i++) {
			long sum=0;
			sum+=pq.poll();
			sum+=pq.poll();
			pq.add(sum);
			pq.add(sum);
		}
		long sum=0;
		Iterator<Long> iter = pq.iterator();
		while(iter.hasNext()) {sum+=iter.next();}
		System.out.println(sum);
		sc.close();
	}
}
