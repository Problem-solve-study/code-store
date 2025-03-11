// 17420KB	132ms
import java.io.*;
import java.util.*;
/*
 * 백트래킹을 통해 합계 코스트가 최소가 되는 3개의 포인트를 완탐해서 구하는 문제.
 */
public class Main{
    static int[][] map;
    static boolean[][] visited;
    static int minCost, n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited = new boolean[n][n];
        minCost = Integer.MAX_VALUE;
        dfs(0, 0);
        System.out.println(minCost);
    }

    private static void dfs(int cnt, int cost){
        int[] dr = {1,0,-1,0,0};
        int[] dc = {0,1,0,-1,0};
        if(cnt==3){
            minCost = Math.min(minCost, cost);
        }
        for(int i=1; i<n-1; i++){
            loop2: for(int j=1; j<n-1; j++){
                //이미 꽃이 있는 지점이 겹칠 경우 continue
                for(int k=0; k<5; k++){
                    if(visited[i+dr[k]][j+dc[k]])   continue loop2;
                }
                //아닐 경우 꽃 배치 후 재귀 돌림
                for(int k=0; k<5; k++){
                    visited[i+dr[k]][j+dc[k]] = true;
                    cost+= map[i+dr[k]][j+dc[k]];
                }
                if(cost<minCost){
                    dfs(cnt+1, cost);
                }
                //재귀 끝난 경우 다시 원복
                for(int k=0; k<5; k++){
                    visited[i+dr[k]][j+dc[k]] = false;
                    cost-= map[i+dr[k]][j+dc[k]];
                }
            }
        }
    }
}