/**
 * Memory : 11948
 * Time : 316
 */

package p_4134;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static long N;


    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            long n = Long.parseLong(st.nextToken());

            long res = solve(n);
            sb.append(res).append("\n");
        }
        System.out.println(sb.toString());
    }

    static long solve(long n) {
        if (n <= 1)
            return 2;

        while (true) {
            boolean flag = false;

            for (int i = 2; (long)i * i <= n; ++i) {
                if (n % i == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag == false)
                return n;
            ++n;
        }
    }
}
