//17072KB 148ms
// N개의 숫자중 K개의 숫자를뽑아 중복되지 않는 숫자를 만드는 문제
// N의 최대 갯수가 10이고 K의 쵀대 갯수가 4이기 때문에 완탐으로 문제 접근
import java.io.*;
import java.util.*;
public class Main {
	static int N, K;
	static int[] numArr;
	static boolean[] visited;
	static Set<String> set = new HashSet<>();
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		
		numArr = new int[N];
		visited = new boolean[N];
		for(int i = 0; i < N; i++)
			numArr[i] = Integer.parseInt(br.readLine());
		dfs(0,"");
		System.out.println(set.size());
	}

	public static void dfs(int depth, String number){
		if (depth == K){
			set.add(number);
			return ;
		}

		for (int i = 0; i < N; i++){
			if (!visited[i]){
				visited[i] = true;
				dfs(depth + 1, number + numArr[i]);
				visited[i] = false;
			}
		}
	}
}
