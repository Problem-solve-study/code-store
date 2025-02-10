//제출번호: 89844421
//메모리:   13404 KB
//실행시간: 104 ms
import java.io.*;
import java.util.*;

//처음에는 gcd, lcm으로 처리할 수 있을까 했는데 뭔가 잘 안 됨
//어쩔 수 없이 소인수분해해서 처리
public class Main {
    static boolean[] isPrime = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        for (int i = 2; i < 1000; i++) {
            if (!isPrime[i]) {
                for (int j = i * i; j < 1000000; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long l = Long.parseLong(st.nextToken());

        if (l % a != 0 || l % b != 0) {
            System.out.print(-1);
        } else {
            long res = 1;
            for (int i = 2; 1L * i * i <= l; i++) {
                if (!isPrime[i] && l % i == 0) {
                    int af = fact(a, i);
                    int bf = fact(b, i);
                    int lf = fact(l, i);

                    if (lf > af && lf > bf) {
                        for (int j = 0; j < lf; j++) {
                            res *= i;
                        }
                    }
                }
            }

            System.out.print(res);
        }
    }

    static int fact(long n, int p) {
        int count = 0;
        while (n % p == 0) {
            count++;
            n /= p;
        }

        return count;
    }
}