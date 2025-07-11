//제출번호: 96200589
//메모리:   77096
//실행시간: 488
import java.io.*;
import java.util.*;

//+-1 BFS에서 텔레포트가 추가된 문제
//그냥 텔레포트를 위한 그래프에 +-1 좌표도 추가하면 간단하게 풀 수 있음
public class Main {
    public static void main(String[] args) throws IOException {
        int n = nextInt(), m = nextInt(), s = nextInt(), e = nextInt();

        int[] visited = new int[n + 1]; 
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = -1; i < n; i++) {
            List<Integer> tmp = new ArrayList<>();
            tmp.add(i);
            tmp.add(i + 2);
            graph.add(tmp);
        }

        for (int i = 0; i < m; i++) {
            int a = nextInt(), b = nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(s);
        visited[s] = 1;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nNode : graph.get(node)) {
                if (0 < nNode && nNode <= n && visited[nNode] == 0) {
                    visited[nNode] = visited[node] + 1;
                    q.add(nNode);
                }
            }
        }

        System.out.print(visited[e] - 1);
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}