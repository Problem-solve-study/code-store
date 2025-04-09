// 12380KB	76ms
import java.io.*;
import java.util.*;
/*
 * 플로이드 워셜로 풀라고 만든 문제이긴 한데 일단은 BFS로 풀이.
 */
public class Main {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    int N = Integer.parseInt(st.nextToken());
    int M = Integer.parseInt(st.nextToken());
    boolean[] visited = new boolean[N+1];
    int[][] bacon = new int[N+1][N+1];
    ArrayList<Integer>[] relation = new ArrayList[N+1];
    for(int i=1; i<=N; i++) relation[i] = new ArrayList<>();

    int a, b;
    for(int i=0; i<M; i++){
      st = new StringTokenizer(br.readLine());
      a = Integer.parseInt(st.nextToken());
      b = Integer.parseInt(st.nextToken());
      relation[a].add(b);
      relation[b].add(a);
    }

    Queue<Node> queue = new ArrayDeque<Node>();
    Node node;
    int depth;
    for(int i=1; i<=N; i++){
      visited = new boolean[N+1];
      visited[i] = true;
      queue.add(new Node(i, 0));
      while(!queue.isEmpty()){
        node = queue.poll();
        for(int r:relation[node.idx]){
          depth = node.depth+1;
          if(!visited[r]){
            visited[r] = true;
            queue.add(new Node(r, depth));
            bacon[i][r] = depth;
          }
        }
      }
      
    }
    int minIdx = 0;
    int minSum = Integer.MAX_VALUE;
    for(int i=1; i<=N; i++){
      int sum = 0;
      for(int j=1; j<=N; j++){
        sum+=bacon[i][j];
      }
      if(sum<minSum){
        minSum=sum;
        minIdx = i;
      }
    }
    System.out.println(minIdx);
  }
}

class Node{
  int idx, depth;
  public Node(int idx, int depth){
    this.idx = idx;
    this.depth = depth;
  }
}
