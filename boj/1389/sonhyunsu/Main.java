//제출번호: 92831035
//메모리:   12760 KB
//실행시간: 88 ms
import java.io.*;

//그냥 플로이드-워샬로 모든 정점에서 모든 정점으로 최단거리를 구해서 답을 찾으면 됨
//케빈 베이컨 수는 최단거리의 합으로 금방 구할 수 있고
//케빈 베이컨 수가 같으면 작은 번호를 출력하라고 했으니 그것만 잘 고려해서 출력하면 됨 
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int m = nextInt();

		int[][] graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				graph[i][j] = 100000000;
			}
			graph[i][i] = 0;
		}

		for (int i = 0; i < m; i++) {
			int a = nextInt() - 1;
			int b = nextInt() - 1;

			graph[a][b] = graph[b][a] = 1;
		}

        //플로이드-워샬 알고리즘, 그냥 모든 a, b에 대해서 최단거리를 구함 
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (graph[i][j] > graph[i][k] + graph[k][j]) {
						graph[i][j] = graph[i][k] + graph[k][j];
					}
				}
			}
		}

		int min = Integer.MAX_VALUE;
		int target = -1;
		for (int i = 0; i < n; i++) {
			int kebin = 0;
            //케빈 베이컨 수를 구함
			for (int j = 0; j < n; j++) {
				kebin += graph[i][j];
			}

            //만약 지금까지 최솟값보다 작다면 업데이트
			if (min > kebin) {
				min = kebin;
				target = i;
			}
		}

        //유저 번호 출력
		System.out.print(target + 1);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}