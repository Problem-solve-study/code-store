/*
 * 숫자 K 를 사용해 자연수 X 를 표현.
 * 
 */

import java.util.HashMap;
import java.util.HashSet;

public class Main {
    static int K, N;
    static HashSet<Integer>[] dp = new HashSet[9];
    static HashMap<Integer, Integer> res = new HashMap<>();
    static int[] arr = new int[] { 0, 1, 11, 111, 1_111, 11_111, 111_111, 1_111_111, 11_111_111 };

    public static void main(String[] args) throws Exception {
        K = readInt();
        for (int i = 1; i <= 8; i++)
            dp[i] = new HashSet<>();
        dp[1].add(K);
        res.put(K, 1);

        for (int i = 2; i <= 8; i++) {
            dp[i].add(K * arr[i]);
            res.put(K * arr[i], i);

            for (int j = i - 1; j > 0; j--) {
                for (int t1 : dp[j]) {
                    for (int t2 : dp[i - j]) {
                        int temp = t1 + t2;
                        if (0 < temp && !res.containsKey(temp)) {
                            res.put(temp, i);
                            dp[i].add(temp);
                        }
                        temp = t1 - t2;
                        if (0 < temp && !res.containsKey(temp)) {
                            res.put(temp, i);
                            dp[i].add(temp);
                        }
                        temp = t1 * t2;
                        if (0 < temp && !res.containsKey(temp)) {
                            res.put(temp, i);
                            dp[i].add(temp);
                        }
                        if (t2 == 0)
                            continue;
                        temp = t1 / t2;
                        if (0 < temp && !res.containsKey(temp)) {
                            res.put(temp, i);
                            dp[i].add(temp);
                        }
                    }
                }
            }
        }

        N = readInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int t = readInt();
            if (res.containsKey(t))
                sb.append(res.get(t)).append('\n');
            else
                sb.append("NO").append('\n');
        }
        System.out.print(sb);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static int readInt() throws Exception {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;

        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }

    static byte read() throws Exception {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }
}