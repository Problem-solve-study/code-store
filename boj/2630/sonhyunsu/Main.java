import java.io.*;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int white, blue;
    static int[][] d;

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        d = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                d[i][j] = nextInt();
            }
        }

        DNQ(0, 0, n, n);
        System.out.printf("%d%n%d", white, blue);
    }

    static void DNQ(int sx, int sy, int ex, int ey) {
        if (ex - sx == 1 || ey - sy == 1) {
            if (d[sx][sy] == 1) {
                blue++;
            } else {
                white++;
            }
            return;
        }

        boolean isSame = true;
        int type = d[sx][sy];
        for (int x = sx; x < ex; x++) {
            for (int y = sy; y < ey; y++) {
                isSame &= type == d[x][y];
            }
        }

        if (isSame) {
            if (type == 1) {
                blue++;
            } else {
                white++;
            }
        } else {
            int mx = (sx + ex) / 2;
            int my = (sy + ey) / 2;
            DNQ(sx, sy, mx, my);
            DNQ(sx, my, mx, ey);
            DNQ(mx, sy, ex, my);
            DNQ(mx, my, ex, ey);
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}