// 11704KB	84ms
import java.io.*;
import java.util.*;

public class Main {
    static int a,b;
    static long l;
    static long gcdAB, lcmAB;
    static long answer = Long.MAX_VALUE;


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        l = Long.parseLong(st.nextToken());

        gcdAB = gcd(a, b);
        lcmAB = a * b / gcdAB;
        
        check();
        System.out.print(answer == Long.MAX_VALUE ? -1 : answer);
    }


    /** L의 약수 중 a,b,c 의 최소공배수가 L인것이 있는지를 확인 */
    static void check() {
        long result;
        for (int i=1; i<=Math.sqrt(l); i++) {
            if (l % i == 0) {
                result = lcm(a,b,i);
                if (result == l) {
                    answer = Math.min(answer, i);
                    return;
                }
                result = lcm(a,b,l/i);
                if (result == l) {
                    answer = Math.min(answer, l/i);
                }
            }
        }
    }


    /** a,b,c 의 최소공배수 */
    static long lcm(int a, int b, long c) {
        return (long) lcmAB*c / gcd(lcmAB, c);
    }

    /** a,b 의 최대공약수 */
    static long gcd(long a, long b) {
        if (a > b) {
            long temp = a;
            a = b;
            b = temp;
        }
        
        while (a != 0) {
            long temp = a;
            a = b % a;
            b = temp;
        }
        return b;
    }
}