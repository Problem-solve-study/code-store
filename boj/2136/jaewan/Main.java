/* 
 * 충돌한 개미는 반대 방향으로 움직이게 된다.
 * 충돌에 충돌에 충돌 ... 반복하면 어떻게 될까?
 * 
 * 개미는 완전탄성충돌을 하므로,
 * 결국 최종 떨어지는 방향은 최초 각 방향으로 움직이던 개미들의 수 만큼 떨어지게 된다.
 * 
 * 즉, 각 개미들의 각각 최초에 움직이던 방향과 상관없이
 * 좌측으로 움직이던 개미들의 수가 a 라면, 좌측 a 마리의 개미는 좌측으로 떨어지고
 * 우측으로 움직이던 개미들의 수가 b 라면, 우측 b 마리의 개미는 우측으로 떨어진다.
 * 
 * 이를 통해 가장 마지막에 떨어지는 개미의 번호를 구할 수 있다.
 * 좌측으로 움직이던 개미들의 숫자가 a 면,
 * a번째 개미 또는 a+1 번쨰 개미가 제일 마지막에 떨어질 것이다.
 * 
 * 그때까지 걸리는 시간은, 
 * 1. 최초에 좌측으로 움직이던 개미들 중 가장 우측에 있던 값
 * 2. L - 우측으로 움직이던 개미들 중 가장 좌측에 있던 값
 * 중 큰값이다.
 * 
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {
    static int N, L;

    static Ant[] ants;

    public static void main(String[] args) throws IOException {
        N = readInt();
        L = readInt();
        int left = 0, right = 0, cnt = 0;
        ants = new Ant[N];
        for (int i = 0; i < N; i++) {
            int temp = readInt();
            if (temp < 0) {
                temp = -temp;
                cnt++;
                left = Math.max(left, temp);
            } else {
                right = Math.max(right, L - temp);
            }
            ants[i] = new Ant(i + 1, temp);
        }
        Arrays.sort(ants);

        int i = 0;
        if (left < right) {
            i = ants[cnt].n;
        } else
            i = ants[cnt - 1].n;

        System.out.printf("%d %d", i, Math.max(left, right));
    }

    static class Ant implements Comparable<Ant> {
        int n, pos;

        public Ant(int n, int pos) {
            this.n = n;
            this.pos = pos;
        }

        @Override
        public int compareTo(Main.Ant o) {
            return pos - o.pos;
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
        boolean negative = c == 45;
        if (negative)
            c = read();
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;
    }
}