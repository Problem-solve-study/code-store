
// 11412 KB, 76 ms
import java.io.IOException;

public class Main {
    static int N, S, res;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        N = readInt();
        S = readInt();
        arr = new int[N];
        for (int i = 0; i < N; i++)
            arr[i] += readInt();

        makePerm(0, 0, 0);
        System.out.println(res);
    }

    static void makePerm(int sum, int cnt, int idx) {
        if (sum == S && cnt > 0)
            res++;

        for (int i = idx; i < N; i++)
            makePerm(sum + arr[i], cnt + 1, i + 1);

    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        boolean negative = false;
        if (c == 45) {
            negative = true;
            c = System.in.read();
        }
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return negative ? -n : n;
    }
}