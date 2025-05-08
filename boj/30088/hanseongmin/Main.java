import java.io.*;
import java.util.*;

/*
 * 15216KB, 264ms
 * 
 * 이전 부서의 퇴근 시간들이 합산되어 현재 부서의 퇴근 시간과 합쳐지므로
 * 이전 부서의 퇴근 시간을 최대한 작게 만들어야 한다.
 * 따라서 면담 소요 시간이 가장 짧은 부서부터 차례대로 면담을 수행하면 
 * 모든 부서가 퇴근하는 데 걸리는 시간을 최소로 만들 수 있다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int N = nextInt();
		//면담 소요 시간이 가장 짧은 부서를 뽑기 위한 힙
		PriorityQueue<Integer> h = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int len = nextInt();
			int sum = 0;
			for (int j = 0; j < len; j++) {
				sum += nextInt();
			}
			h.add(sum);
		}
		
		//정답을 저장
		long res = 0;
		//이전 부서의 퇴근 시간의 합
		long sum = 0;
		while (!h.isEmpty()) {
			int curV = h.remove();
			res += sum + curV;
			sum += curV;
		}
		System.out.print(res);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}