// 	210916KB	944ms
import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    static int n, m;
    static int[] num = new int[501], indegree = new int[501];
    static boolean[] visited = new boolean[501];
    static ArrayList<Integer>[] map = new ArrayList[501];
    static HashSet<Node> set = new HashSet<>();
    static class Node {
        int a, b;
        Node(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public boolean equals(Object o) {
            Node node = (Node) o;
            return this.a == node.a && this.b == node.b; 
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
    /**
     * 순서를 출력하는걸 보고 위상정렬은 캐치함
     * 초기 순서가 주어지기 때문에 해당 순서로 모든 2점의 선후관계를 알 수 있다.
     * 만약, 2점의 상대적인 순서가 바뀌었다면 반대로 그래프를 구성하면됨.
     * 
     * 일단 입력을 다 받아두고 위의 내용을 고려하여 그래프를 구성함.
     * 그 다음 위상정렬 수행
     * 
     * 위상정렬에서 indegree == 0이되면 큐에 추가하는데, 이렇게 추가된 노드의 개수가 여러개라면
     * 그들 사이의 관계는 구할 수 없다.
     * 그래서 queue의 크기가 1을 넘으면 ?
     * 모든 점을 방문할 수 없다면 IMPOSSIBLE 을 출력함.
     */
    public static void main(String[] args) throws Exception {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();

        for (int t=0; t<T; t++) {
            n = nextInt();
            init();
            for (int i=1; i<=n; i++) {
                num[i] = nextInt();
            }
            m = nextInt();
            for (int i=0; i<m; i++) {
                int a = nextInt();
                int b = nextInt();
                set.add(new Node(a, b));
                set.add(new Node(b, a));
            }

            for (int i=1; i<=n-1; i++) {
                for (int j=i+1; j<=n; j++) {                    
                    if (set.contains(new Node(num[i], num[j]))) {
                        map[num[j]].add(num[i]);
                        indegree[num[i]]++;
                    }
                    else {
                        map[num[i]].add(num[j]);
                        indegree[num[j]]++;
                    }
                }
            }
            sb.append(topologySort()).append("\n");
        }
        System.out.print(sb);
    }

    static String topologySort() {
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i=1; i<=n; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        if (queue.isEmpty()) {
            return "IMPOSSIBLE";
        }
        StringBuilder sb = new StringBuilder();
        int count = 0;
        while (!queue.isEmpty()) {
            if (queue.size() > 1) {
                return "?";
            }
            int now = queue.poll();
            visited[now] = true;
            count++;
            sb.append(now).append(" ");

            for (int next : map[now]) {
                indegree[next]-=1;
                if(indegree[next] == 0 && !visited[next]) {
                    queue.add(next);
                }
            }
        }
        return (count == n) ? sb.toString() : "IMPOSSIBLE";
    }

    static void init() {
        for (int i=1; i<=n; i++) {
            num[i] = 0; indegree[i] = 0; visited[i] = false;
            map[i] = new ArrayList<>();
        }
        set.clear();
    }

    static int nextInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}