//제출번호: 96090006
//메모리:   19604 KB
//실행시간: 192 ms
import java.io.*;

//문제를 보면 A 센터를 특정 건물에 연결하고 B 센터까지의 거리를 구함
//트리의 루트에서 집합을 합치고 새 루트까지의 거리를 구하는 거라 유니온파인드를 생각
//이 때 기본적인 유니온파인드는 간선의 가중치가 없는데 이번 문제에서는 있기 때문에
//간선의 가중치를 적절하게 업데이트 하는 방향으로 해결

//p[i]: i 노드가 가리키는 부모노드
//dist[i]: i 노드에서 p[i]노드까지의 거리
public class Main {

    static int[] p, dist;

    public static void main(String[] args) throws IOException {
        int T = nextInt();

        p = new int[20001];
        dist = new int[20001];
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int n = nextInt();

            for (int i = 1; i <= n; i++) {
                p[i] = i;
                dist[i] = 0;
            }

            int cmd;
            while ((cmd = nextChar()) != 'O') {
                if (cmd == 'E') {
                    int company = nextInt();
                    find(company);
                    sb.append(dist[company]).append('\n');
                } else {
                    union(nextInt(), nextInt());
                }
            }
        }

        System.out.print(sb);
    }

    static void union(int a, int b) {
        int center = find(a);
        p[center] = b;
        dist[center] = Math.abs(center - b) % 1000;
    }

    static int find(int x) {
        if (p[x] == x) {
            return x;
        }
        
        int parent = p[x];
        p[x] = find(p[x]);
        dist[x] += dist[parent];
        
        return p[x];
    }

    static int nextChar() throws IOException {
        int c = System.in.read();
        System.in.read();

        return c;
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}