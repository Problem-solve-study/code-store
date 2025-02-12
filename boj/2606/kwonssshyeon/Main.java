//	11604KB	68ms
import java.io.*;
import java.util.StringTokenizer;

/**
 * 1에서 방문할 수 있는 모든 정점을 찾는 문제
 * 그래프 탐색인데 Queue 같은 자바 컬렉션을 사용하지 않으려고 DFS 채택
 */

public class Main {
    static int n;
    static boolean[][] connected;
    static boolean[] visited;
    static int answer;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

		connected = new boolean[n+1][n+1];
        for (int i=0; i<m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            connected[a][b] = true;
            connected[b][a] = true;
        }
        visited = new boolean[n+1];
        dfs(1);
        System.out.println(answer);
    }

    static void dfs(int node) {
        visited[node] = true;
        
        for (int next=1; next<=n; next++) {
            if (!visited[next] && connected[node][next]) {
                visited[next] = true;
                answer++;
                dfs(next);
            }
        }
    }
}