//문제: BOJ 11724번
//메모리: 44868 KB
//시간: 344 ms

/*
 * 모든 노드에 대해 방문하지 않았던 노드라면 해당 노드를 시작으로 BFS를 돌린다.
 * BFS를 돌린 횟수가 연결 요소의 개수다
 */

import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws IOException {
        int n = nextInt(), m = nextInt();
        List<List<Integer>> edge = new ArrayList<>();
        for(int i=0;i<=n;i++)
            edge.add(new ArrayList<>());
        while(m-->0){
            int a = nextInt(), b=nextInt();
            edge.get(a).add(b);
            edge.get(b).add(a);
        }

        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new ArrayDeque<>();
        int cnt = 0;
        for(int i=1;i<=n;i++){
            if(visited[i]) continue;
            cnt++;
            queue.add(i);
            visited[i]=true;
            while(!queue.isEmpty()){
                int now = queue.poll();
                for(int next : edge.get(now)){
                    if(visited[next]) continue;
                    queue.add(next);
                    visited[next]=true;
                }
            }
        }
    System.out.println(cnt);
    }
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
