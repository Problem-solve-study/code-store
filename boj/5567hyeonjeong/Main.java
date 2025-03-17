import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(br.readLine());
		boolean[][] matrix = new boolean[N][N];
		int M = Integer.parseInt(br.readLine());
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken()) - 1;
			int v = Integer.parseInt(st.nextToken()) - 1;
			matrix[u][v] = true;
			matrix[v][u] = true;
		}
		
		boolean[] visited = new boolean[N];
		for (int i = 0; i < N; i++) {
			if (matrix[0][i]) {
				visited[i] = true;
				for (int j = 0; j < N; j++) {
					if (matrix[i][j]) {
						visited[j] = true;
					}
				}
			}
		}
		
		int count = 0;
		for (int i = 1; i < N; i++) {
			if (visited[i]) {
				count++;
			}
		}
		
		System.out.println(count);
	}
}
