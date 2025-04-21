//227944KB, 524ms
import java.io.*;
import java.util.*;

public class Main {
  static int N, M, K;
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int sum = 0;
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(st.nextToken());
    M = Integer.parseInt(st.nextToken());
    K = Integer.parseInt(st.nextToken());


    for (int i = 1; i <= M; i++){
      st = new StringTokenizer(br.readLine());
      int t = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());

      sum += r;
      if (sum > K){
        sb.append(i + " " + 1);
        break;
      }
    }

    if (sum > K)
      System.out.println(sb.toString());
    else
      System.out.println(-1);
  }
}