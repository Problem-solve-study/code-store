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
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    static int N;
    static Lane[] lanes;

    public static void main(String[] args) throws IOException {
        N = readInt();
        lanes = new Lane[N];

        for (int i = 0; i < N; i++)
            lanes[i] = new Lane(readInt(), readInt(), readInt());
        Arrays.sort(lanes);

        ArrayList<Lane> result = new ArrayList<>();
        Lane temp = lanes[0];
        for (int i = 1; i < N; i++) {
            // 겹침
            if (lanes[i].start <= temp.end) {
                temp.end = Math.max(temp.end, lanes[i].end);
                temp.cost = Math.min(temp.cost, lanes[i].cost);
            } else {
                result.add(temp);
                temp = lanes[i];
            }
        }
        result.add(temp);

        StringBuilder sb = new StringBuilder();
        sb.append(result.size()).append('\n');
        for (Lane lane : result)
            sb.append(lane.start).append(' ').append(lane.end).append(' ').append(lane.cost).append('\n');
        System.out.print(sb);
    }

    static class Lane implements Comparable<Lane> {
        int start, end, cost;

        public Lane(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Main.Lane o) {
            return start - o.start;
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