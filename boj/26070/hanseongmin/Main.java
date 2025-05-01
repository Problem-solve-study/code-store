import java.io.*;
import java.util.*;

/*
11652KB, 64ms

그냥 시뮬레이션 돌리면 되는 문제인듯
언제까지 시뮬을 돌려야할까 고민하다가 그냥 BFS스럽게 돌리기로 결정
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        long A = ni(); long B = ni(); long C = ni();
        long X = ni(); long Y = ni(); long Z = ni();
        HashSet<ArrayList<Long>> set = new HashSet<>();
        Queue<ArrayList<Long>> q = new ArrayDeque<>();
        ArrayList<Long> init = new ArrayList<>();
        init.add(A); init.add(B); init.add(C); init.add(X); init.add(Y); init.add(Z); init.add(0L);
        q.add(init); set.add(init);
        long res = 0;
        while (!q.isEmpty()) {
            ArrayList<Long> cur = q.remove();
            long a = cur.get(0); long b = cur.get(1); long c = cur.get(2);
            long x = cur.get(3); long y = cur.get(4); long z = cur.get(5);
            long cnt = cur.get(6);
            res = cnt;

            //현재 쓸 수 있는 식권을 다 씀
            long aUsed = Math.min(a, x);
            long bUsed = Math.min(b, y);
            long cUsed = Math.min(c, z);
            a -= aUsed; x -= aUsed; cnt += aUsed;
            b -= bUsed; y -= bUsed; cnt += bUsed;
            c -= cUsed; z -= cUsed; cnt += cUsed;

            //교환할 수 있는 식권을 최대한 교환
            while ((a == 0 && x >= 3) || (b == 0 && y >= 3) || (c == 0 && z >= 3)) {
                if (a == 0 && x >= 3) {
                    y += x / 3;
                    x %= 3;
                }

                if (b == 0 && y >= 3) {
                    z += y / 3;
                    y %= 3;
                }

                if (c == 0 && z >= 3) {
                    x += z / 3;
                    z %= 3;
                }
            }

            ArrayList<Long> newList = new ArrayList<>();
            newList.add(a); newList.add(b); newList.add(c); newList.add(x); newList.add(y); newList.add(z); newList.add(cnt);
            if (!set.contains(newList)) {
                set.add(newList);
                q.add(newList);
            }
        }
        System.out.print(res);
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
