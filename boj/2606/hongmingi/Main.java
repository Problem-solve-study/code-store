//14212KB	112ms
import java.io.*;
import java.util.*;
/*
 * dfs 연습문제. 1을 포함하지 않는 것을 고려 안해서 2번 틀렸음. 문제를 잘 읽어야 한다는 걸 다시 느낌.
 */
public class Main {
    static int n;
    static boolean[] visited;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n+1][n+1];
        visited = new boolean[n+1];

        int T = Integer.parseInt(br.readLine());
        int x,y;

        StringTokenizer st;
        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            map[x][y] = map[y][x] = 1;
        }

        dfs(1);
        // 1을 포함하지 않기에 cnt -1부터 시작.
        int cnt = -1;
        for(boolean v:visited){
            if(v) cnt++;
        }

        System.out.println(cnt);
        
    }    

    static void dfs(int i){
        visited[i] = true;
        for(int j=0; j<=n; j++){
            if(map[i][j]==1 && !visited[j]){
                dfs(j);
            }
        }
    }
}
