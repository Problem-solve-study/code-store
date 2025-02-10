import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static long[] memo;
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        memo = new long[n + 1];
        memo[0] = 1L;

        System.out.println(t(n));
    }

    static long t(int x) {
        if (memo[x] != 0) {
            return memo[x];
        }

        for (int i = 0; i < x; i++) {
            memo[x] += t(i) * t(x-1-i);
        }

        return memo[x];
    }
}