
//11512KB 68ms
import java.io.*;
import java.util.*;

public class Main {
    static long N, M, K;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        long res = 1;

        res = div(N, M);
        System.out.println(res % K);
    }

    public static long div(long num, long loop) {
        long val = 0;
        if (loop == 1) {
            return num % K;
        }
        val = div(num, loop / 2);
        if (loop % 2 == 1) {
            val = (val % K * val % K * num % K) % K;
        } else {
            val = (val % K * val % K) % K;
        }

        return val % K;
    }
}
