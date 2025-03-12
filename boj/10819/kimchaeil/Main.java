//문제: BOJ 10819번 차이를 최대로
//메모리: 11892 KB
//시간: 80 ms

/*
 *  순열 문제
 */

import java.io.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int n;
	static int[] arr;
	static boolean[] visited;
	static int result;
	public static void main(String[] args) throws IOException {
		n = nextInt();
		arr = new int[n];
		for(int i=0;i<n;i++)
			arr[i]=nextInt();
		visited = new boolean[n];
		for(int i=0;i<n;i++) {
			visited[i]=true;
			permutation(1,arr[i],0);
			visited[i]=false;
		}
		System.out.println(result);
	}
	
	static void permutation(int depth, int prev, int sum) {
		if(depth==n) {
			result = result<sum?sum:result;
			return;
		}
		for(int i=0;i<n;i++) {
			if(!visited[i]) {
				visited[i]=true;
				permutation(depth+1,arr[i],sum+Math.abs(prev-arr[i]));
				visited[i]=false;
			}
		}
		
		
	}
	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
