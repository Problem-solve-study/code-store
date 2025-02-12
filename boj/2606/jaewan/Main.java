import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, cnt;
	static boolean[] visit;
	static boolean[][] connect;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		visit = new boolean[N + 1];
		connect = new boolean[N + 1][N + 1];
		int m = Integer.parseInt(br.readLine());
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			connect[a][b] = true;
			connect[b][a] = true;
		}

		visit[1] = true;
		dfs(1);
		System.out.println(cnt);
	}

	static void dfs(int t) {
		for (int i = 1; i <= N; i++) {
			if (connect[t][i] && !visit[i]) {
				visit[i] = true;
				cnt++;
				dfs(i);
			}
		}
	}
}
