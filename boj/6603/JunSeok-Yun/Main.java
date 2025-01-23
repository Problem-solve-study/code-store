import java.util.Scanner;

public class Main {
	public static int N;
	public static int[] arr;
	public static boolean[] visited;
	public static StringBuilder sb;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true)
		{
			N = sc.nextInt();
			if (N == 0)
				break ;
			arr = new int[N];
			visited = new boolean[N];
			sb = new StringBuilder();
			for (int i = 0; i < N; i++)
				arr[i] = sc.nextInt();
			dfs(0,0);
			System.out.println(sb);
		}
	}
	public static void dfs(int idx, int depth){
		if (depth == 6){
			for (int i = 0; i < N; i++)
			{
				if (visited[i])
					sb.append(arr[i]).append(" ");
			}
			sb.append("\n");
			return ;
		}

		for (int i = idx; i < N; i++)
		{
			if (!visited[i]){
				visited[i] = true;
				dfs(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
}
