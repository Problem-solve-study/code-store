
/*
 * 현재 노드에서 다음 순서를 갈 수 있는지 검사.
 * 못가면 부모 노드로 이동해서 검사.
 * 
 */
import java.io.IOException;
import java.util.HashSet;

public class Main {
    static int N, visitCnt;
    // static int[] degree;
    static Node[] graph;

    public static void main(String[] args) throws IOException {
        N = readInt();
        graph = new Node[N + 1];
        // degree = new int[N + 1];
        for (int i = 1; i <= N; i++)
            graph[i] = new Node();
        for (int i = 1; i < N; i++) {
            int a = readInt(), b = readInt();
            graph[a].link.add(b);
            graph[b].link.add(a);
            // degree[a]++;
            // degree[b]++;
        }

        boolean isPossible = false;
        int start = readInt();
        if (start == 1) {
            visitCnt++;
            dfs(1);
            // 순서에 맞게 모두 방문했으면
            if (visitCnt == N)
                isPossible = true;
        }
        System.out.println(isPossible ? 1 : 0);
    }

    static void dfs(int cur) throws IOException {
        // int idx = degree[cur];
        int idx = graph[cur].link.size();
        for (int i = 0; i < idx; i++) {
            int next = readInt();
            // 연결돼 있으면
            if (graph[cur].link.remove(next)) {
                // degree[cur]--;
                // degree[next]--;
                graph[next].link.remove(cur);
                visitCnt++;
                dfs(next);
            } else
                return;
        }
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }

    static class Node {
        HashSet<Integer> link = new HashSet<>();
    }
}