/* 
 * 시작점 기준으로 오름차순 정렬하고, 겹치면 합치기
 * 이전 노선의 끝 점이 다음 노선의 시작점보다 크거나 같으면 겹침.
 * 
 * lane[i - 1].end >= lane[i].start
 * 
 * 그럼 합치기 : 시작점 오름차순으로 정렬했으니까 시작점은 lane[i].start 고, 끝 점은 max(lane[i].end, lane[i + 1].end)
 * 
 * 해서 안겹치면 개편 후 노선 하나 완성. result 에 저장.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static 구간[] list;

    public static void main(String[] args) throws Exception {
        N = readInt();
        list = new 구간[N];
        for (int i = 0; i < N; i++) {
            int s = readInt(), e = readInt();
            list[i] = new 구간(s, e, i + 1);
        }

        // 시작 오름차순
        Arrays.sort(list, (o1, o2) -> o1.S - o2.S);

        // 끝 시각 기준 min-heap
        PriorityQueue<구간> pq = new PriorityQueue<>((o1, o2) -> o1.E - o2.E);

        int max = 0, bestTime = -1;

        for (구간 cur : list) {
            // cur.S 이전에 끝난 것들 제거
            while (!pq.isEmpty() && pq.peek().E < cur.S)
                pq.poll();

            // 현재 구간 추가
            pq.add(cur);

            // 최대 갱신 시 스냅샷
            if (pq.size() > max) {
                max = pq.size();
                bestTime = cur.S;
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(max).append('\n');
        for (구간 g : list)
            if (g.S <= bestTime && bestTime <= g.E)
                sb.append(g.idx).append(' ');
        System.out.print(sb);
    }

    static class 구간 {
        int S, E, idx;

        public 구간(int s, int e, int idx) {
            S = s;
            E = e;
            this.idx = idx;
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