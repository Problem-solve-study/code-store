// 	13092KB	76ms
import java.io.*;
import java.util.*;
/*
 * sum 값 반환하는 DFS를 사용하여 구현.
 */
public class Main{
  static int N, M, bSum, wSum;
  static boolean[][] visited;
  static char[][] war;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());

    war = new char[M][N];
    for(int i=0; i<M; i++){
      war[i] = br.readLine().toCharArray();
    }
    visited = new boolean[M][N];
    wSum = bSum = 0;

    for(int i=0; i<M; i++){
      for(int j=0; j<N; j++){
        if(visited[i][j]) continue;
        if(war[i][j]=='W'){
          wSum+=Math.pow(dfs(i, j), 2);
        }else{
          bSum+=Math.pow(dfs(i, j), 2);
        }
      }
    }
    System.out.println(wSum+" "+bSum);
  }

  static int dfs(int m, int n){
    int[] dr = {0,1,0,-1};
    int[] dc = {1,0,-1,0};
    visited[m][n] = true;
    int sum = 1;
    for(int i=0; i<4; i++){
      if(n+dr[i]>=0 && n+dr[i]<N && m+dc[i]>=0 && m+dc[i]<M && 
        !visited[m+dc[i]][n+dr[i]] && war[m][n]==war[m+dc[i]][n+dr[i]]){
        sum+=dfs(m+dc[i], n+dr[i]);
      }
    }
    return sum;
  }
}