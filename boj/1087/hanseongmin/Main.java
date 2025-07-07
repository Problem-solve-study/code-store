import java.io.*;
import java.util.*;

/*
11824KB, 80ms

삼분탐색은 처음 보는 태그라 공부해서 풀어봤음
이분탐색스럽게 찾되, 구간이 3개로 나눠진다는 점이 다른듯
생각보다 개념 자체가 어렵지는 않았다.

쥐들은 시간이 지남에 따라 처음부터 멀어지거나, 가까워졌다가 다시 멀어지게 될 것인데
후자의 경우 나올 수 있는 L의 값이 2차함수의 꼴이 되어서 삼분 탐색으로 구해야 함.

가능한 t를 기준으로 left와 right를 잡고 해당 t일 때 쥐의 위치를 구해서 가능한 최대의 L을 구하는 것을 반복
이를 반복하여 L의 최솟값을 구하여 출력하면 된다.

경계에 있을 때의 쥐는 잡힌게 아니므로 t초일 때 쥐들의 분포를 구하고 x, y 좌표들의 min, max를 구한 뒤
x좌표의 차이와 y좌표의 차이 중 큰 값을 택(최대의 L을 구해야하므로)하는 것으로 L을 구할 수 있다.

실수 오차 처리를 어떻게 할까 고민하다 left와 right의 차이가 1e-15 이상일 때만 돌렸다가 시초났음
이후 그냥 1000번정도 반복하도록 고치니 통과
*/

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static class Mouse {
        int px, py, vx, vy;

        public Mouse(int px, int py, int vx, int vy) {
            this.px = px;
            this.py = py;
            this.vx = vx;
            this.vy = vy;
        }
    }
    static int N;
    static Mouse[] mouses;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        mouses = new Mouse[N];
        for (int i = 0; i < N; i++) {
            mouses[i] = new Mouse(nextInt(), nextInt(), nextInt(), nextInt());
        }

        double left = 0;
        double right = 100_000;
        for (int i = 0; i < 1000; i++) {
            double gap = right - left;
            double m1 = left + (gap / 3);
            double m2 = right - (gap / 3);

            if (getL(m1) > getL(m2)) {
                left = m1;
            } else {
                right = m2;
            }
        }
        System.out.print(getL(left));
    }

    static double getL(double t) {
        double minX, minY, maxX, maxY;
        minX = minY = Integer.MAX_VALUE;
        maxX = maxY = Integer.MIN_VALUE;

        for (int i = 0; i < N; i++) {
            Mouse cur = mouses[i];
            double curX = cur.px + cur.vx * t;
            double curY = cur.py + cur.vy * t;
            minX = Math.min(minX, curX);
            maxX = Math.max(maxX, curX);
            minY = Math.min(minY, curY);
            maxY = Math.max(maxY, curY);
        }

        return Math.max(maxX - minX, maxY - minY);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
