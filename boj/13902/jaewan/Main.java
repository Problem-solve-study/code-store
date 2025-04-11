/*
 * 요리할 때 필요 이상 크기의 웍 사용 x, 주문 받은 짜장면의 그릇 수에 딱 맞게 요리
 * 주문 받은 짜장면의 수 N <= 10,000, 웍의 개수 M <= 1,000
 * 웍의 크기.
 * 
 * 모든 주문을 처리하기 위해 해야 하는 최소 요리 횟수 출력
 * 처리 못하면 -1 출력
 * BFS로 탐색
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashSet;

public class Main {
    static int N, M;
    static int[] size;
    static boolean[] visit;
    static HashSet<Integer> woaks, res;

    public static void main(String[] args) throws IOException {
        N = readInt();
        M = readInt();
        size = new int[M];
        visit = new boolean[N + 1];
        woaks = new HashSet<>();
        for (int i = 0; i < M; i++) {
            size[i] = readInt();
            woaks.add(size[i]);
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (i == j || size[i] + size[j] > N)
                    continue;
                woaks.add(size[i] + size[j]);
            }
        }

        System.out.println(BFS());
    }

    static int BFS() {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for (int t : woaks) {
            q.offer(t);
            visit[t] = true;
        }
        for (int time = 1;; time++) {
            if (visit[N])
                return time;
            int size = q.size();
            if (size == 0)
                return -1;

            while (size-- > 0) {
                int cur = q.poll();
                for (int t : woaks) {
                    if (cur + t > N)
                        continue;
                    if (visit[cur + t])
                        continue;
                    q.offer(cur + t);
                    visit[cur + t] = true;
                }
            }
        }
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}