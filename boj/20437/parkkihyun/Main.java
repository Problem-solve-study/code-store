// 18640KB 308ms
import java.io.*;
import java.util.*;

// 브루트포스 + 투포인터 사용
// a부터 z까지 모든 글자를 기준으로 K개 포함하는지 확인

// 있는 문자만 검사하면 시간이 더 줄어들지 모르겠음

class Main {

    // 어떤 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이
    // 어떤 문자를 정확히 K개 포함하고, 문자열의 첫 번째 글자와 마지막 글자가 해당 글자이면서 가장 긴 연속 문자열의 길이

    public static void main(String[] args) throws Exception {
        int T = ni();

        while (T-- > 0) {
            solve();
        }
        System.out.print(sb.toString());
    }

    static char[] W;
    static int K;

    static void solve() throws Exception {
        W = ns().toCharArray();
        K = ni();

        int ans3 = Integer.MAX_VALUE;
        int ans4 = Integer.MIN_VALUE;

        for (char c = 'a'; c <= 'z'; c++) {
            int k = 0;

            int r = 0;
            for (int l = 0; l < W.length; l++) {
                while (r < W.length && k < K) {
                    if (W[r] == c) {
                        k++;
                    }
                    r++;
                }

                if (k == K) {
                    // 4번 과정
                    if (W[l] == W[r - 1]) {
                        ans4 = Math.max(ans4, r - l);
                    }
                    // 3번 과정
                    ans3 = Math.min(ans3, r - l);
                }

                if (W[l] == c) {
                    k--;
                }
            }
        }

        if (ans3 == Integer.MAX_VALUE && ans4 == Integer.MIN_VALUE) {
            sb.append("-1");
        } else {
            sb.append(ans3).append(' ').append(ans4);
        }
        sb.append('\n');
    }

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tks;

    static String ns() throws Exception {
        if (tks == null || !tks.hasMoreTokens())
            tks = new StringTokenizer(br.readLine());
        return tks.nextToken();
    }

    static int ni() throws Exception {
        return Integer.parseInt(ns());
    }
}
