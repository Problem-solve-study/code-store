// 	65816KB	396ms
import java.io.*;
import java.util.*;
/*
 * depth 계산이 포함된 dfs 문제.
 * 처음 해본 문제라 좀 헤맸음.
 * depth 변수를 따로 answer로 두고 dfs를 돌릴때 D 값보다 dfs의 결과값이 클 때마다 answer를 +1.
 * 이후 전체 루트는 answer * 2인데 answer가 0일때도 고려해야 함.
 */
public class Main {
  static int N, S, D, answer;
  static ArrayList<Integer>[] edges;
  static boolean[] visited;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    N = Integer.parseInt(st.nextToken());
    S = Integer.parseInt(st.nextToken());
    D = Integer.parseInt(st.nextToken());
    
    edges = new ArrayList[N+1];
    for(int i=1; i<=N; i++) edges[i] = new ArrayList<>();
    int from, to;
    for(int i=1; i<N; i++){
      st = new StringTokenizer(br.readLine());
      from = Integer.parseInt(st.nextToken());
      to = Integer.parseInt(st.nextToken());
      edges[from].add(to);
      edges[to].add(from);
    }
    visited = new boolean[N+1];
    answer = 0;
    visited[S] = true;
    int cnt = dfs(S);
    System.out.println((answer-1)>0?(answer-1)*2:0);
  }

  static int dfs(int idx){
    int cnt = 0;
    for(int node : edges[idx]){
      if(!visited[node]){
        visited[node] = true;
        cnt = Math.max(dfs(node), cnt);
      }
    }
    if(cnt>=D) answer++;
    return cnt+1;
  }
}
