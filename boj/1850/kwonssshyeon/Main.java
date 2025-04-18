// 	59304KB	212ms
import java.io.*;
import java.util.StringTokenizer;

/**
 * 유클리드 호제법
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long result = gcd(a, b);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < result; i++) {
            sb.append("1");
        }
        System.out.print(sb);
    }

    static long gcd(long a, long b) {
        if (b == 0)
            return a;
        return gcd(b, a % b);
    }
}