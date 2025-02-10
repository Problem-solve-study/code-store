// 	11492KB	68ms

/**
 * 문제 자체에서 점화식이 제공된다.
 * 재귀 깊이가 그리 깊지 않으므로 탑다운으로 작성하였다
 */

import java.util.*;
import java.io.*;


public class Main {
    static long[] DP = new long[36];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        DP[0] = 1;
        System.out.println(get(Integer.parseInt(br.readLine())));
    }
    static long get(int n) {
        if (DP[n]!=0) return DP[n];
        for (int i=0; i<n; ++i) DP[n] += get(i)*get(n-1-i);
        return DP[n];
    }
}