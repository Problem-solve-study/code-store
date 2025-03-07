// 26824KB	256ms
import java.io.*;
import java.util.*;

/**
 * 다익스트라를 1번 돌면서 지나온 경로에 G-H 가 있는지 mark 배열에 기록
 * 나머지는 집가서 자세히 쓰겠습니다 ... 정말로로
 */
public class Main {
    static int n,m,t;
    static int s,g,h;
    static int[] targets;
    static ArrayList<Node>[] map;
    static class Node implements Comparable<Node> {
        int pos, cost;
        Node (int pos, int cost) {
            this.pos = pos;
            this.cost = cost;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }
    static byte[] mark;
    static int[] dist;
    static final byte NONE = 0, CATCHED = 1, TEMP_CATCHED = 2;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        int TC = nextInt();
        for (int tc=0; tc<TC; tc++) {
            n = nextInt();
            m = nextInt();
            t = nextInt();
            s = nextInt();
            g = nextInt();
            h = nextInt();
            map = new ArrayList[n+1];
            for (int i=1; i<=n; i++) {
                map[i] = new ArrayList<>();
            }
            for (int i=0; i<m; i++) {
                int a = nextInt();
                int b = nextInt();
                int d = nextInt();
                map[a].add(new Node(b, d));
                map[b].add(new Node(a, d));
            }
            targets = new int[t];
            for (int i=0; i<t; i++) {
                targets[i] = nextInt();
            }
            
            dijkstra();

            ArrayList<Integer> answer = new ArrayList<>();
            for (int target : targets) {
                if (mark[target] == CATCHED) {
                    answer.add(target);
                }
            }
            Collections.sort(answer);
            for (int node : answer) {
                sb.append(node).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }


    static void dijkstra() {
        mark = new byte[n+1];
        dist = new int[n+1];
        Arrays.fill(dist, 100_000_000);

        queue.add(new Node(s, 0));
        dist[s] = 0;
        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (dist[now.pos] < now.cost) continue;
            
            if (mark[now.pos] == TEMP_CATCHED) {
                mark[now.pos] = CATCHED;
            }
            for (Node next : map[now.pos]) {
                int nextDist = dist[now.pos] + next.cost;
                if (dist[next.pos] > nextDist) {
                    mark[next.pos] = (mark[now.pos] == CATCHED || (now.pos == g && next.pos == h) || (now.pos == h && next.pos == g))
                        ? TEMP_CATCHED
                        : NONE;
                    dist[next.pos] = nextDist;
                    queue.add(new Node(next.pos, dist[next.pos]));
                } 
                else if (dist[next.pos] == nextDist) {
                    if (mark[now.pos] == CATCHED || (now.pos == g && next.pos == h) || (now.pos == h && next.pos == g)) {
                        mark[next.pos] = TEMP_CATCHED;
                    }
                }
            }
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
