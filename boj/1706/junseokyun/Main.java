// 11668KB, 68ms
import java.io.*;
import java.util.*;

public class Main {
  static int R, C;
  static char[][] map;
  static TreeSet<String> set = new TreeSet<>();
  public static void main(String[] args)throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    StringBuilder sb;

    R = Integer.parseInt(st.nextToken());
    C = Integer.parseInt(st.nextToken());
    map = new char[R][C];

    for (int i = 0; i < R; i++){
      String str = br.readLine();
      for (int j = 0; j < C; j++){
        map[i][j] = str.charAt(j);
      }
    }

    // 가로 낱말
    for (int i = 0; i < R; i++){
      boolean flag = true;
      sb = new StringBuilder();
      for (int j = 0; j < C; j++){
        if (map[i][j] != '#'){
          flag = true;
        }
        if (flag && map[i][j] != '#'){
          sb.append(map[i][j]);
          if (j + 1 == C || map[i][j + 1] == '#'){
            if(sb.toString().equals("") || sb.length() == 1){
              flag = false;
              sb = new StringBuilder();
            }else{
              flag = false;
              set.add(sb.toString());
              sb = new StringBuilder();
            }
          }
        }
      }
    }

    // 세로 낱말
    for (int i = 0; i < C; i++){
      boolean flag = true;
      sb = new StringBuilder();
      for (int j = 0; j < R; j++){
        if (map[j][i] != '#'){
          flag = true;
        }
        if (flag && map[j][i] != '#'){
          sb.append(map[j][i]);
          if (j + 1 == R || map[j + 1][i] == '#'){
            if(sb.toString().equals("") || sb.length() == 1){
              flag = false;
              sb = new StringBuilder();
            }else{
              flag = false;
              set.add(sb.toString());
              sb = new StringBuilder();
            }
          }
        }
      }
    }

    System.out.println(set.first());
  }
}