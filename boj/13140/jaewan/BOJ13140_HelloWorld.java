
// 12404 KB, 96 ms
import java.io.IOException;

public class BOJ13140_HelloWorld {
    static int N;
    static int[] res = new int[7], nums = new int[6];
    // H, E, L, O, W, R, D;
    static boolean[] select = new boolean[10];

    public static void main(String[] args) throws IOException {
        N = readInt();
        StringBuilder sb = new StringBuilder();
        if (!makePerm(0))
            sb.append("No Answer");
        else {
            sb.append("  ").append(res[0]).append(res[1]).append(res[2]).append(res[2]).append(res[3]).append('\n');
            sb.append('+').append(' ').append(res[4]).append(res[3]).append(res[5]).append(res[2]).append(res[6])
                    .append('\n');
            sb.append("-------").append('\n');
            sb.append(String.format("%7d", N));
        }
        System.out.println(sb.toString());
    }

    static boolean check() {
        int a = res[0] * 10000 + res[1] * 1000 + res[2] * 100 + res[2] * 10 + res[3];
        int b = res[4] * 10000 + res[3] * 1000 + res[5] * 100 + res[2] * 10 + res[6];
        return N == a + b;
    }

    static boolean makePerm(int cnt) {
        if (cnt == 7) {
            if (!check())
                return false;

            return true;
        }
        for (int i = 0; i < 10; i++) {
            // 첫자리는 0 안됨
            if (i == 0 && (cnt == 0 || cnt == 4))
                continue;
            if (select[i])
                continue;
            res[cnt] = i;
            select[i] = true;
            if (makePerm(cnt + 1))
                return true;
            select[i] = false;
        }
        return false;
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
