/*
 * 5가지 동작, 처음은 0분, 0보다 작아지면 0분
 * 1. ADDH = +60
 * 2. ADDT = +10
 * 3. MINT = -10
 * 4. ADDO = +1
 * 5. MINO = -1
 * 
 * 설정해야 할 시간이 주어질 때, 눌러야 하는 버튼 최소 횟수와 방법
 * 
 * 여러 가지면, 사전 순으로 앞선 순서.
 * BFS로 구하기.
 * 59 까지만 구하고, 60 이상인 녀석은 ADDH 연산 해야 함. 더해서 출력하기.
 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    static final int MAX = 60;
    static int[][] count = new int[5][MAX + 1];
    static boolean[] visit = new boolean[MAX + 1];
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] di = { -1, 1, -10, 10, 60 };
        ArrayDeque<Integer> q = new ArrayDeque<>();

        q.offer(0);
        visit[0] = true;
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int i = 0; i < 5; i++) {
                int ni = cur + di[i];
                if (ni < 0 || ni > MAX || visit[ni])
                    continue;
                visit[ni] = true;
                q.offer(ni);
                for (int j = 0; j < 5; j++)
                    count[j][ni] = count[j][cur];
                count[i][ni]++;
            }
        }

        int n = readInt();
        for (int i = 0; i < n; i++)
            print(readInt());
        System.out.println(sb.toString());
    }

    static void print(int idx) {
        int t = idx / 60;
        idx %= 60;
        for (int i = 4; i >= 0; i--) {
            sb.append(i == 4 ? count[i][idx] + t : count[i][idx]).append(' ');
        }
        sb.append('\n');
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