// 11596KB	72ms
import java.io.*;
import java.util.*;

public class Main {
	static int n, m, num[];
	static int answer;

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		num = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<n; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(0,0,0);
		System.out.print(answer);
	}
	
	
	static void dfs(int idx, int cnt, int sum) {
		if (sum > m) return;
		if (cnt == 3) {
			answer = Math.max(answer, sum);
			return;
		}
		
		for (int i=idx; i<n; i++) {
			dfs(i+1, cnt+1, sum+num[i]);
		}
	}
}
