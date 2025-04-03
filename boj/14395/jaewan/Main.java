/// 11572 KB, 68 ms
/*
 * 정수 s를 t로 바꾸는 최소 연산 횟수 구하기.
 * 1. s = s + s;
 * 2. s = s - s;
 * 3. s = s * s;
 * 4. s = s / s;
 * 
 * - 결과는 0이 되어서 사용할 일이 없음. s t 모두 1 이상
 * / 결과는 1이 됨.
 * 
 * *와 + 연산만 가지고 BFS.
 * / 연산은 처음에 한 번만 해서 추가.
 * 
 * 범위가 1,000,000,000까지이므로, visit 배열 선언해서 사용하면 터짐.
 * Map을 사용해 visit 처리.
 */

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayDeque<Data> q = new ArrayDeque<>();
        HashMap<Integer, String> visit = new HashMap<>();
        int s = readInt(), t = readInt();
        int rootT = (int) Math.sqrt(t), next;

        visit.put(s, "");
        q.offer(new Data(s, ""));

        next = s * s;
        if (!visit.containsKey(next) && s <= rootT) {
            visit.put(next, "*");
            q.offer(new Data(next, "*"));
        }

        next = s << 1;
        if (!visit.containsKey(next) && next <= t) {
            visit.put(next, "+");
            q.offer(new Data(next, "+"));
        }

        next = 1;
        if (!visit.containsKey(next)) {
            visit.put(1, "/");
            q.offer(new Data(1, "/"));
        }

        while (!q.isEmpty()) {
            Data cur = q.poll();
            if (cur.n == t)
                break;

            // * 연산, 제곱해서 t의 제곱근보다 커지면 할 필요 없음. 작아지는 연산이 없다.
            if (cur.n <= rootT) {
                next = cur.n * cur.n;
                if (!visit.containsKey(next)) {
                    String nextOpts = cur.opts + "*";
                    visit.put(next, nextOpts);
                    q.offer(new Data(next, nextOpts));
                }
            }

            // + 연산, 2배
            next = cur.n << 1;
            if (!visit.containsKey(next) && next <= t) {
                String nextOpts = cur.opts + "+";
                visit.put(next, nextOpts);
                q.offer(new Data(next, nextOpts));
            }
        }

        if (visit.containsKey(t)) {
            if (visit.get(t).length() == 0)
                System.out.println(0);
            else
                System.out.println(visit.get(t));
        } else
            System.out.println(-1);
    }

    static class Data {
        int n;
        String opts;

        public Data(int n, String opts) {
            this.n = n;
            this.opts = opts;
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