//제출번호: 89844421
//메모리:   13404 KB
//실행시간: 104 ms
import java.io.*;
import java.util.*;

//처음에는 gcd, lcm으로 처리할 수 있을까 했는데 뭔가 잘 안 됨
//어쩔 수 없이 소인수분해해서 처리
public class Main {

    //true면 소수가 아님, false면 소수임
    //0과 1은 소수가 아니지만 사용하지 않아서 따로 초기화하진 않음
    static boolean[] isPrime = new boolean[1000001];

    public static void main(String[] args) throws IOException {
        //에라토스테네스의 체
        for (int i = 2; i < 1000; i++) {
            //만약 i가 소수라면
            if (!isPrime[i]) {
                for (int j = i * i; j < 1000000; j += i) {
                    //i의 배수들은 모두 합성수이므로 소수가 아니라고 기록함
                    isPrime[j] = true;
                }
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());
        long l = Long.parseLong(st.nextToken());

        //l은 a와 b의 c의 최소공배수이므로 반드시 a와 b로 나눠져야 한다.
        if (l % a != 0 || l % b != 0) {
            System.out.print(-1);
        } else {
            long res = 1;
            for (int i = 2; 1L * i * i <= l; i++) {
                //만약 l이 소수인 i로 나눠진다면
                if (!isPrime[i] && l % i == 0) {
                    //i에 대한 a, b, l의 인수를 구한다.
                    int af = fact(a, i);
                    int bf = fact(b, i);
                    int lf = fact(l, i);

                    //a와 b의 인수로 l을 만들 수 없다면 c의 인수가 l을 만들어야 한다.
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