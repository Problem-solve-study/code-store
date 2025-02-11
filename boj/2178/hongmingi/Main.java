// 14764KB	112ms

/*
 * 처음에 DFS로 시도했으나 map 크기가 커질 경우 전부 탐색하는데 시간이 많이 소요되서 시간 초과 문제가 있었음.
 * 다음부터 최단거리탐색은 BFS로 바로 하는걸로.
 */
import java.io.*;
import java.util.*;

public class Main {
    static int n, m, cnt;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<m; j++){
                map[i][j] = s.charAt(j)-'0';
                if(map[i][j]==0){
                    visited[i][j] = true;
                }
            }
        }
        
        int xx = 0;
        int yy = 0;
        cnt = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0,0,cnt});

        int[] route;
        while(!q.isEmpty()){
            route = q.poll();
            xx = route[0];
            yy = route[1];
            cnt = route[2];

            if(xx==n-1 && yy==m-1){
                System.out.println(route[2]);
                break;
            }

            for(int i=0; i<4; i++){
                if(xx+dx[i]>=0 && xx+dx[i]<n && yy+dy[i]>=0 && yy+dy[i]<m){
                    if(!visited[xx+dx[i]][yy+dy[i]] && map[xx+dx[i]][yy+dy[i]]==1){
                        q.offer(new int[]{xx+dx[i], yy+dy[i], cnt+1});
                        visited[xx+dx[i]][yy+dy[i]] = true;
                    }  
                }
            }
        }
    }    
}
