import java.io.IOException;
import java.util.HashSet;

public class Main {
    static int L, K;
    static int[][] stars;

    public static void main(String[] args) throws IOException {
        readInt();
        readInt();
        L = readInt();
        K = readInt();
        stars = new int[2][K];
        HashSet<Integer> xPos = new HashSet<>(), yPos = new HashSet<>();
        for (int i = 0; i < K; i++) {
            stars[0][i] = readInt();
            stars[1][i] = readInt();
            xPos.add(stars[0][i]);
            yPos.add(stars[1][i]);
        }

        int max = 0;

        for (Integer x : xPos)
            for (Integer y : yPos)
                max = Math.max(max, count(x, y));
        System.out.println(K - max);
    }

    static int count(int x, int y) {
        int cnt = 0;
        for (int i = 0; i < K; i++) {
            if (x <= stars[0][i] && stars[0][i] <= x + L &&
                    y <= stars[1][i] && stars[1][i] <= y + L)
                cnt++;
        }

        return cnt;
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
