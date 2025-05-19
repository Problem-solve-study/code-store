// 13192 KB, 104 ms
/*
 * 위상 정렬로 사이클 판단.
 * 모든 Node 에 visit 안했으면 "IMPOSSIBLE"
 * 
 * 모든 노드 간에 상대적인 정보가 있으므로 ? 가 출력되는 경우는 없을듯
 * 
 * 실제 위상 정렬 수행 안하고 degree 만으로 판단 가능
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int T = readInt();
        for (int t = 0; t < T; t++) {
            StringBuilder sb = new StringBuilder();
            int n = readInt();

            int[] rank = new int[n + 1];
            for (int i = 1; i <= n; i++)
                rank[readInt()] = i;

            int[] inDegree = new int[n + 1];
            for (int i = 1; i <= n; i++)
                inDegree[i] = rank[i];

            int m = readInt();
            for (int i = 0; i < m; i++) {
                int a = readInt(), b = readInt();
                if (rank[a] < rank[b]) {
                    inDegree[a]++;
                    inDegree[b]--;
                } else {
                    inDegree[a]--;
                    inDegree[b]++;
                }
            }

            boolean[] visit = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                if (inDegree[i] <= 0 || inDegree[i] > n)
                    continue;
                visit[inDegree[i]] = true;
                rank[inDegree[i]] = i;
            }

            boolean flag = false;
            for (int i = 1; i <= n; i++) {
                sb.append(rank[i]).append(' ');
                if (!visit[i])
                    flag = true;
            }
            System.out.println(flag ? "IMPOSSIBLE" : sb);
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