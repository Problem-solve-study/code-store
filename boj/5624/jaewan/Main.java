
// 13980 KB, 116 ms
import java.io.IOException;

public class Main {
    static final int GAP = 1_000_000;

    public static void main(String[] args) throws IOException {
        int N = readInt(), cnt = 0;
        int[] arr = new int[N];
        boolean[] visit = new boolean[(GAP << 1) + 1];

        for (int i = 0; i < N; i++)
            arr[i] = readInt();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (visit[(arr[i] - arr[j]) + GAP]) {
                    cnt++;
                    break;
                }
            }
            for (int j = 0; j <= i; j++) {
                visit[arr[i] + arr[j] + GAP] = true;
            }
        }
        System.out.println(cnt);

    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;

    }
}