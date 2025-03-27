
// 26500 KB, 180 ms
/*
 * 에라토스테네스의 채
 */
import java.io.IOException;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        N = readInt();
        int[] res = new int[N + 1];
        res[1] = 1;
        int cnt = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= N; i++) {
            if (res[i] == 0)
                cnt++;
            for (int j = 1; i * j <= N; j++)
                if (res[i * j] == 0)
                    res[i * j] = cnt;
        }
        sb.append(cnt).append('\n');
        for (int i = 1; i <= N; i++)
            sb.append(res[i]).append(' ');
        System.out.println(sb.toString());
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}