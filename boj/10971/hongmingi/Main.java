// 	12200KB	252ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * dfs 백트래킹을 통해 구현.
 * 
 */
public class Main{
    static int N, res;
    static boolean[] visited;
    static int[][] W;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        W = new int[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) W[i][j] = Integer.parseInt(st.nextToken());
        }
        res = Integer.MAX_VALUE;

        visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            visited[i] = true;
            dfs(i, i, 0, 1); 
            visited[i] = false;
        }
        System.out.println(res);
    }

    static void dfs(int start, int curr, int sum, int count) {
        if (count == N) {
            if (W[curr][start] != 0) {
                res = Math.min(res, sum + W[curr][start]);
            }
            return;
        }

        for (int next = 0; next < N; next++) {
            if (!visited[next] && W[curr][next] != 0) {
                visited[next] = true;
                dfs(start, next, sum + W[curr][next], count + 1);
                visited[next] = false; 
            }
        }
    }
}
