//  11508KB	68ms

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] DP;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        DP = new int[N+1];
        System.out.println(fibonacci(N));
    }
    static int fibonacci(int n) {
        if (DP[n] != 0) {
            return DP[n];
        }
        else if (n < 2) {
            DP[n] = 1;
            return DP[n];
        }
        DP[n] = (fibonacci(n-1) + fibonacci(n-2) + 1) % 1_000_000_007;
        return DP[n];
    }
}
