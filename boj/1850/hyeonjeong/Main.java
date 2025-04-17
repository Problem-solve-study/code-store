// 59608KB 200ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/** 
 * 1로 이루어진 수의 GCD == 두 수의 길이의 GCD의 길이만큼 1이 있는 수
 */
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

        if (a < b) {
            long temp = a;
            a = b;
            b = temp;
        }
    
        long gcd = gcd(a, b);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gcd; i++) {
            sb.append('1');
        }

        System.out.print(sb);
    }

    static long gcd(long a, long b) {
        long r = a % b;
        if (r == 0) {
            return b;
        }
        return gcd(b, r);
    }
}
