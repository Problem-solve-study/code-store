import java.io.*;
import java.math.BigInteger;
import java.util.*;
import java.util.Map.Entry;

/*
12216KB, 80ms

우리가 찾을 건 LCM(a, b, c) = l을 만족시키는 a, b, c를 구하는 것인데 a, b는 구해져 있으니 c만 구하면 된다.
c가 가능한 값이 10^12까지 가능하므로 O(n)으로는 시간초과 예상

LCM은 주어진 숫자들을 소인수분해를 해서 각 인수의 최대 제곱수만큼을 택하여 곱하는 것이므로
주어진 a, b를 소인수분해한다. 이후 LCM(a, b)를 구하고, LCM(a, b)는 LCM(a, b, c)와 배수 관계에 있을 것이므로
해당 조건을 체크하여 -1인지 아닌지를 판별한다.

LCM(a, b)에서 L이 되려면 L / LCM(a, b)만큼의 소인수를 LCM(a, b)에 반영을 해줘야 할 것이기 때문에
L / LCM(a, b)를 소인수분해 해준 후 a, b의 인수들을 확인하여 반영해준다.

반영해줄 때 L / LCM(a, b)가 가진 인수의 개수만큼 더해줘야하는데 1씩 더하다가 계속 맞왜틀 하고 있었다.
 */

public class Main {
    static class Number {
        long n;
        HashMap<Long, Long> primeFactors;

        Number(long n) {
            this.n = n;
            primeFactors = new HashMap<>();

            for (long i = 2; i <= n;) {
                if (n % i == 0) {
                    primeFactors.put(i, primeFactors.getOrDefault(i, 0L) + 1);
                    n /= i;
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        Number a = new Number(Integer.parseInt(st.nextToken()));
        Number b = new Number(Integer.parseInt(st.nextToken()));
        long l = Long.parseLong(st.nextToken());

        BigInteger gcd = BigInteger.valueOf(a.n).gcd(BigInteger.valueOf(b.n));
        long ab = (a.n * b.n) / gcd.longValue();
        if (ab > l || l % ab != 0) {
            bw.write(String.valueOf(-1));
        } else {
            Number c = new Number(l / ab);
            for (Entry<Long, Long> entry : c.primeFactors.entrySet()) {
                long max = Math.max(
                    a.primeFactors.getOrDefault(entry.getKey(), 0L), 
                    b.primeFactors.getOrDefault(entry.getKey(), 0L)
                );
                c.primeFactors.put(entry.getKey(), max + entry.getValue());
            }

            long res = 1;
            for (Entry<Long, Long> entry : c.primeFactors.entrySet()) {
                res *= Math.pow(entry.getKey(), entry.getValue());
            }
            bw.write(String.valueOf(res));
        }
        bw.flush();
    }
}