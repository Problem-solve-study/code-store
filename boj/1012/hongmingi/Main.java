//16324KB 148ms
import java.io.*;
import java.util.*;
/*
 * dfs 연습문제. 어제 꺼랑 비슷한듯?
 */
public class Main {
    static int n,m,k,cnt;
    static int[][] map;
    static boolean[][] visited;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        
        int xx, yy;
        for(int t=0; t<T; t++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            map = new int[n][m];
            visited = new boolean[n][m];
            
            //배추심기
            for(int i=0; i<k; i++){
                st = new StringTokenizer(br.readLine());
                xx = Integer.parseInt(st.nextToken());
                yy = Integer.parseInt(st.nextToken());
                map[xx][yy] = 1;
            }

            cnt = 0;
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(map[i][j] == 1 && !visited[i][j]){
                        dfs(i, j);
                        cnt++;
                    } 
                }
            }
            System.out.println(cnt);
        }
    }

    static void dfs(int i, int j){
        int[] dx = {1,0,-1,0};
        int[] dy = {0, 1, 0, -1};

        visited[i][j] = true;
        for(int k=0; k<4; k++){
            if(i+dx[k]>=0 && i+dx[k]<n && j+dy[k]>=0 && j+dy[k]<m){
                if(map[i+dx[k]][j+dy[k]] == 1 && !visited[i+dx[k]][j+dy[k]])  dfs(i+dx[k], j+dy[k]);
            }
        }
    }
}
