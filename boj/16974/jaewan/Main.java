
/*
 * lv(L) = B + lv(L-1) + P + lv(L-1) + B
 * 
 * lv 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] layers = new long[51], patties = new long[51];

    public static void main(String[] args) throws IOException {
        layers[0] = 1;
        patties[0] = 1;

        for (int i = 1; i <= 50; i++) {
            layers[i] = (layers[i - 1] << 1) + 3;
            patties[i] = (patties[i - 1] << 1) + 1;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] in = br.readLine().split(" ");
        System.out.println(func(Integer.parseInt(in[0]), Long.parseLong(in[1])));
    }

    static long func(int N, long X) {
        long res = 0;
        if (X >= layers[N])
            return patties[N];
        if (X <= 1)
            return 0;

        // 첫 번 빼기
        X--;
        res += func(N - 1, X);
        // N-1 의 전체 레이어 빼기
        X -= layers[N - 1];

        if (X > 0) {
            // 중간에 있는 패티
            res++;
            X--;
            res += func(N - 1, X);
        }
        return res;
    }

    static long readLong() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        long n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}