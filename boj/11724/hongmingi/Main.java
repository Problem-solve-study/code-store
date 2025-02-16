//114308KB	464ms

import java.io.*;
import java.util.*;

/*
 * DFS 연습문제! 
 */
public class Main {
    static int n, m, a, b, cnt;
    static boolean[][] map;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        visited = new boolean[n+1];
        visited[0] = true;
        map = new boolean[n+1][n+1];
        cnt = 0;
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            
            map[a][b] = map[b][a] = true;
        }

        for(int i=1; i<=n; i++){
            if(!visited[i]){
                dfs(i);
                cnt++;
            } 
        }
        System.out.println(cnt);
    }

    static void dfs(int a){
        visited[a] = true;
        for(int i=1; i<=n; i++){
            if(!visited[i]&&map[a][i])  dfs(i);
        }
    }
}
