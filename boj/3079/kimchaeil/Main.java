//문제: BOJ 3079번 입국심사
//메모리: 15816 KB
//시간: 224 ms

/*
 * 매개변수탐색, 매개변수를 시간으로 매개변수로 받고 해당 시간 내에 처리가능한 최대 인원을 반환하는 함수를 이용
 * 반환받은 최대 인원이 m보다 작은지 또는 크거나 같은지에 따라 left, right 이동
 * 최악의 경우, N=1,M=1,000,000,000,T1=1,000,000,000이다.
 * 즉, 최대 시간을 1e18로 잡아야 한다. 그래서 left와 right를 long으로 선언한다.
 * 최대 인원은 넘겨받은 time이 1e18이고 n이 100,000이고 모든 T가 1이라면
 * cnt는 1e23까지 누적된다. 즉, 반환값은 long도 부족하므로 double로 선언했다.
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		n = nextInt();
		int m = nextInt();
		arr = new int[n];
		for(int i=0;i<n;i++) {
			arr[i]=nextInt();
		}
		
		long left = 0;
		long right = (long) 1e18;
		while(left<=right) {
			long mid = (left+right)/2;
			if(maxPeople(mid)<m) {
				left=mid+1;
			}else {
				right=mid-1;
			}
		}
		System.out.println(left);
	}
	static double maxPeople(long time) {
		double cnt = 0;
		for(int t :arr) {
			cnt+=time/t;
		}
		return cnt;
	}
	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
