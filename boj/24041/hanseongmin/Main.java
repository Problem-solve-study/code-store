import java.io.*;
import java.util.*;

/*
 * 174708KB, 1364ms
 * 
 * 날짜마다 세균 수가 변하므로 무조건 O(N)으로 탐색할 수밖에 없다.
 * 따라서 매개변수 탐색으로 답이 될 수 있는 날짜를 log로 탐색하면 시간 내에 볼 수 있겠다고 생각, 매개변수 탐색으로 접근했다.
 * 결정함수를 짤 때는 중요하지 않은 재료는 맥스힙에 넣고 K개만큼 빼서 총 세균 수의 합이 G 이하인지 아닌지를 판별하면 된다. 
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N, G, K;
	static ArrayList<long[]> o0 = new ArrayList<>();
	static ArrayList<long[]> o1 = new ArrayList<>();
	static PriorityQueue<Long> h = new PriorityQueue<>(Collections.reverseOrder());
	
	public static void main(String[] args) throws Exception {
		N = nextInt(); //N개의 재료가 있음
		G = nextInt(); //세균이 G마리 이하면 먹을 수 있음
		K = nextInt(); //중요하지 않은 재료를 K만큼 뺄 수 있음
		for (int i = 0; i < N; i++) {
			long[] e = new long[] {nextInt(), nextInt()};
			if (nextInt() == 0) {
				o0.add(e);
			} else {
				o1.add(e);
			}
		}
		
		//매개변수 탐색 시작
		long l = 1;
		long r = Integer.MAX_VALUE;
		while (l <= r) {
			long mid = (l + r) / 2;
			if (check(mid)) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		System.out.print(r);
	}
	
	static boolean check(long day) {
		//중요한 재료의 세균 수의 합을 계산
		long sum = 0;
		for (long[] arr : o0) {
			long s = arr[0];
			long l = arr[1];
			sum += (s * Math.max(1, day - l));
		}
		
		//중요하지 않은 세균 수의 합을 계산
		long sum1 = 0;
		h.clear();
		for (long[] arr : o1) {
			long s = arr[0];
			long l = arr[1];
			long value = (s * Math.max(1, day - l));
			sum1 += (s * Math.max(1, day - l));
			h.add(value);
		}
		
		//굳이 안빼도 G 이하면 가능한 경우
		if ((sum + sum1) <= G) return true;
		//G 이하가 아니라면 세균 수가 큰 중요하지 않은 재료를 차례대로 빼봄
		for (int i = 0; i < K && !h.isEmpty(); i++) {
			sum1 -= h.remove();
		}
		//최종적으로 세균 수가 G 이하인지 확인
		return (sum + sum1) <= G;
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}