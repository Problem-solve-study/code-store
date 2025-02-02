import java.io.*;

public class Main {
    static int[] s = new int[2000];
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        s[0] = 1;
        s[1] = 1;
        s[2] = 3;
        System.out.println(solve(N));
    }
    public static int solve(int i) {
        if (s[i]!=0) return s[i];
        s[i] = (solve(i-1) + 2*solve(i-2)) % 10007;
        return s[i];
    }
}