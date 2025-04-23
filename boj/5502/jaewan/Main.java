// 319748 KB, 1748 ms
/*
 * 최소 개수의 문자를 삽입해 팰린드롬이 되게 하는 문자의 개수.
 * BFS로 최소 개수를 탐색.
 * 문자열 길이 N <= 5000
 * 
 *  시간복잡도 O(N^2)
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static char[] word;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        word = br.readLine().toCharArray();
        System.out.println(BFS());
    }

    static int BFS() {
        PriorityQueue<Data> pq = new PriorityQueue<>();
        boolean[][] visit = new boolean[N][N];
        visit[0][N - 1] = true;
        pq.offer(new Data(0, N - 1, 0));
        while (!pq.isEmpty()) {
            Data cur = pq.poll();
            if (cur.r <= cur.l)
                return cur.cnt;

            if (word[cur.l] == word[cur.r] && !visit[cur.l + 1][cur.r - 1]) {
                visit[cur.l + 1][cur.r - 1] = true;
                pq.offer(new Data(cur.l + 1, cur.r - 1, cur.cnt));
                continue;
            }

            if (!visit[cur.l + 1][cur.r]) {
                visit[cur.l + 1][cur.r] = true;
                pq.offer(new Data(cur.l + 1, cur.r, cur.cnt + 1));
            }
            if (!visit[cur.l][cur.r - 1]) {
                visit[cur.l][cur.r - 1] = true;
                pq.offer(new Data(cur.l, cur.r - 1, cur.cnt + 1));
            }
        }
        return -1;
    }

    static class Data implements Comparable<Data> {
        int l, r, cnt;

        public Data(int l, int r, int cnt) {
            this.l = l;
            this.r = r;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Main.Data o) {
            return cnt - o.cnt;
        }
    }
}