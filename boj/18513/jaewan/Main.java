
// 40572 KB, 276 ms
/*
 * 가장 가까운 샘터까지의 거리의 합이 최소가 되게.
 * 샘터별로 좌, 우에 K채의 집을 배치.
 * 있으면 넘어가고 -> 방문 배열을 set으로 사용(좌표가 2억개)
 */
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        N = readInt();
        K = readInt();
        HashSet<Integer> visit = new HashSet<>();
        ArrayDeque<int[]> q = new ArrayDeque<>();
        int[] sam = new int[N];
        for (int i = 0; i < N; i++) {
            sam[i] = readInt();
            visit.add(sam[i]);
            q.offer(new int[] { sam[i], 0 });
        }
        long res = 0;
        while (K > 0) {
            int[] cur = q.poll();
            for (int d = -1; d <= 1; d += 2) {
                int next = cur[0] + d;
                if (!visit.contains(next)) {
                    visit.add(next);
                    q.offer(new int[] { next, cur[1] + 1 });
                    res += cur[1] + 1;
                    if (--K == 0)
                        break;
                }
            }
        }
        System.out.println(res);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}