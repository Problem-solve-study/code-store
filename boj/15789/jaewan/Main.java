/* 
 * disjoint set - rank 활용
 * 
 * 입력 상태가 주어진 후에, CTP 왕국은 동맹국의 수가 큰 순서대로 동맹을 맺으면 됨.
 *
 * 최초 상태에서 상태가 변하는 일은 없으므로,
 * PQ를 활용해 K 마다 rank 배열 탐색하지 않고 한번만 탐색할 수 있다.
 * 
 * parent[i] == i 기준으로
 * parent[i] != parent[h] 한솔 왕국 동맹국 아니고
 * parent[i] != parent[c] ctp 왕국 동맹국 아닌 것의 i와 rank를 pq에 등록
 * 
 */

import java.io.IOException;
import java.util.PriorityQueue;

public class Main {
    static int N, M, C, H, K;
    static int[] parent, rank;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        parent = new int[N + 1];
        rank = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for (int i = 0; i < M; i++)
            union(readInt(), readInt());
        C = readInt();
        H = readInt();
        K = readInt();

        PriorityQueue<Ally> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (parent[i] != i || parent[i] == parent[C] || parent[i] == parent[H])
                continue;
            pq.offer(new Ally(i, rank[i]));
        }

        while (!pq.isEmpty() && K-- > 0) {
            Ally cur = pq.poll();
            union(C, cur.idx);
        }

        int rootC = find(C);
        System.out.println(rank[rootC]);
    }

    static int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    static void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        if (rootU != rootV) {
            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
                rank[rootV] += rank[rootU];
            } else {
                parent[rootV] = rootU;
                rank[rootU] += rank[rootV];
            }
        }
    }

    static class Ally implements Comparable<Ally> {
        int idx, rank;

        public Ally(int idx, int rank) {
            this.idx = idx;
            this.rank = rank;
        }

        @Override
        public int compareTo(Main.Ally o) {
            return o.rank - this.rank;
        }
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}