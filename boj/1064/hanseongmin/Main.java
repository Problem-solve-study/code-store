import java.io.*;

/*
11608KB, 68ms

3개의 점이 나옴
주어진 3개의 점이 모두 같은 직선에 위치할 경우 불가능

가능한 평행사변형은 총 3개가 나옴
a-b, a-c를 두 변으로 하는 평행사변형
a-b, b-c를 두 변으로 하는 평행사변형
a-c, b-c를 두 변으로 하는 평행사변형

3개의 평행사변형의 둘레를 구하고 min, max를 구한 뒤 차이 출력
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int xa, xb, xc, ya, yb, yc;

    public static void main(String[] args) throws Exception {
        xa = nextInt(); ya = nextInt();
        xb = nextInt(); yb = nextInt();
        xc = nextInt(); yc = nextInt();

        if (isImpossible()) {
            System.out.print(-1);
        } else {
            double max = Integer.MIN_VALUE;
            double min = Integer.MAX_VALUE;
            double ab = getLength(xa, ya, xb, yb);
            double ac = getLength(xa, ya, xc, yc);
            double bc = getLength(xb, yb, xc, yc);
            max = Math.max(max, getPerimeter(ab, ac)); min = Math.min(min, getPerimeter(ab, ac));
            max = Math.max(max, getPerimeter(ab, bc)); min = Math.min(min, getPerimeter(ab, bc));
            max = Math.max(max, getPerimeter(ac, bc)); min = Math.min(min, getPerimeter(ac, bc));
            System.out.print(max - min);
        }
    }

    static boolean isImpossible() {
        double ab = getSlope(xa, ya, xb, yb);
        double ac = getSlope(xa, ya, xc, yc);
        double bc = getSlope(xb, yb, xc, yc);
        return ab == ac && ac == bc;
    }

    static double getSlope(int x1, int y1, int x2, int y2) {
        return (double) Math.abs(y1 - y2) / Math.abs(x1 - x2);
    }

    static double getLength(int x1, int y1, int x2, int y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    static double getPerimeter(double a, double b) {
        return 2 * a + 2 * b;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
