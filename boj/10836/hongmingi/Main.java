// 323620KB	4220ms
import java.io.*;
import java.util.*;
/*
 * 정직하게 구현. 근데 사실 알고보면 2열 이상에 있는 애벌레들은 제일 1행의 값과 다 똑같은 것으로 되기 때문에
 * 굳이 계산을 할 필요가 없었음.
 * 정직하게 구현할거면 sysout으로 하면 무조건 메모리랑 시간이 터지기 때문에 stringbuilder를 사용해서 구현해야됨
 */
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());
    
    int[][] map = new int[M][M];
    for(int i=0; i<M; i++) Arrays.fill(map[i], 1);
    for(int t=0; t<N; t++){
      st = new StringTokenizer(br.readLine());
      int cnt0 = Integer.parseInt(st.nextToken());
      int cnt1 = Integer.parseInt(st.nextToken());
      int cnt2 = Integer.parseInt(st.nextToken());
      //int[][] plus = new int[M][M];
      for(int k=M-1; k>=0; k--){
        if(cnt0>0){
          cnt0--;
          continue;
        }else if(cnt1>0){
          cnt1--;
          map[k][0] += 1;
        }else{
          cnt2--;
          map[k][0] += 2;
        }
      }
      for(int k=1; k<M; k++){
        if(cnt0>0){
          cnt0--;
          continue;
        }else if(cnt1>0){
          cnt1--;
          map[0][k] += 1;
        }else{
          cnt2--;
          map[0][k] += 2;
        }
      }
      
    }
    for(int i=1; i<M; i++){
      for(int j=1; j<M; j++){
        map[i][j] = Math.max(map[i-1][j], Math.max(map[i-1][j-1], map[i][j-1]));
      }
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < M; i++) {
      for (int j = 0; j < M; j++) {
        sb.append(map[i][j]).append(" ");
      }
      sb.append("\n");
    }
    System.out.print(sb);
  }
}