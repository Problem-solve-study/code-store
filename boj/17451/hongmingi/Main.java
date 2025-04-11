// 57324KB	296ms
import java.io.*;
import java.util.*;
/*
 * 행성의 끝에서부터 차례로 역탐색하면서 속도가 커질때는 그 속도를, 작아질 때는 작은 속도의 배수값으로 반환하는 그리디 문제. 
 */
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());
    int[] planets = new int[n];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i=0; i<n; i++) planets[i] = Integer.parseInt(st.nextToken());
    
    long speed = planets[n-1];
    for(int i=n-2; i>=0; i--){
      if(planets[i]>=speed) speed = planets[i];
      else{
        speed = planets[i]*(long)Math.ceil((double)speed/planets[i]);
      }
    }
    System.out.println(speed);
  }
}
