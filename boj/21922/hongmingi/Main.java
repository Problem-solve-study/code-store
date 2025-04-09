// 387772KB	760ms
import java.io.*;
import java.util.*;
/*
 * 순수 구현. 탐색 함수는 재귀로 구현함.
 */
public class Main {
  static int N, M;
  static int[][] map;
  static boolean[][] canSit;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    map = new int[N+1][M+1];
    canSit = new boolean[N+1][M+1];
    
    for(int i=1; i<=N; i++){
      st = new StringTokenizer(br.readLine());
      for(int j=1; j<=M; j++){
        map[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int i=1; i<=N; i++){
      for(int j=1; j<=M; j++){
        if(map[i][j]==9){
          canSit[i][j] = true;
          checkDown(i, j);
          checkUp(i, j);
          checkLeft(i, j);
          checkRight(i, j);
        }
      }
    }

    int cnt = 0;
    for(int i=1; i<=N; i++){
      for(int j=1; j<=M; j++){
        if(canSit[i][j]) cnt++;
      }
    }

    System.out.println(cnt);
  }

  static void checkDown(int r, int c){
    //아래 탐색
    int i = r+1;
    if(i>N) return;
    
    canSit[i][c] = true;
    if(map[i][c]==0 || map[i][c]==1) checkDown(i, c);
    if(map[i][c]==3) checkLeft(i, c);
    if(map[i][c]==4) checkRight(i, c);
  }

  static void checkUp(int r, int c){
    //위 탐색
    int i = r-1;
    if(i<0) return;
    
    canSit[i][c] = true;
    if(map[i][c]==0 || map[i][c]==1) checkUp(i, c);
    if(map[i][c]==3) checkRight(i, c);
    if(map[i][c]==4) checkLeft(i, c);
  }

  static void checkLeft(int r, int c){
    //왼쪽 탐색
    int i = c-1;
    if(i<0) return;
    
    canSit[r][i] = true;
    if(map[r][i]==0 || map[r][i]==2) checkLeft(r, i);
    if(map[r][i]==3) checkDown(r, i);
    if(map[r][i]==4) checkUp(r, i);
  }

  static void checkRight(int r, int c){
    //오른쪽 탐색
    int i = c+1;
    if(i>M) return;
    
    canSit[r][i] = true;
    if(map[r][i]==0 || map[r][i]==2) checkRight(r, i);
    if(map[r][i]==4) checkDown(r, i);
    if(map[r][i]==3) checkUp(r, i);
  }
}
