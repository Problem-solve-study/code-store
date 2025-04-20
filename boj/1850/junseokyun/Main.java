//59112KB, 204ms
import java.io.*;
import java.util.*;

public class Main {
    static long A, B;
    static long res;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        if (A > B) {
            res = gcd(A, B);
        } else {
            res = gcd(B, A);
        }
        for (int i = 0; i < res; i++) {
            sb.append(1);
        }
        System.out.println(sb.toString());
    }

    public static long gcd(long a, long b) {
        long r = 0;
        while (b != 0) {
            r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}