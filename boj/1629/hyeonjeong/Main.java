// 11508KB 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * a ^ b % c = (a % c) ^ b 
 */
public class Main {
    static long a;
    static int b;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        long result = aExp(b);

        System.out.println(result);
    }

    //a ^ exp를 logN으로 구하는 함수
    static long aExp(int exp) {
        if (exp == 0) {
            return 1;
        }

        long result = aExp(exp / 2);

        if (exp % 2 == 1) {
            return (((result * result) % c) * a) % c;
        }

        return (result * result) % c;
    }
}
