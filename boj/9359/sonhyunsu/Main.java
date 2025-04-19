
//제출번호: 93342230
//메모리:   11996 KB
//실행시간: 72 ms
import java.io.*;
import java.util.*;

//문제를 보자마자 아 이거 정수론이구나 바로 알았음
//n의 약수를 이용해서 [a, b] 구간 사이에 있는 n의 약수의 배수들을 모두 지우면 정답을 얻을 수 있음
//이 때 n의 약수는 n을 소인수 분해하면 얻을 수 있고,
//n의 이루는 소수들만 구하면 n의 약수들을 모두 지울 수 있음

//n이 만약 2x3x5로 이루어져 있다면 
//2의 배수, 3의 배수, 5의 배수를 모두 지움
//6의 배수, 10의 배수, 15의 배수는 중복해서 지워졌으니 다시 더해줌
//30의 배수는 중복해서 더해졌으니 다시 빼줌

//위와 같은 방법으로 n의 소수들을 이용해서 만들 수 있는 모든 소수의 곱에 대하여 더하고 빼는 방식으로 구함
//[x / (소수의 곱)] 을 하면 [1, x] 구간 안에 있는 (소수의 곱)의 배수의 개수를 알 수 있음

//2^2이나 3^2 형태의 약수는 계산하지 않아도 되는데, 이런 약수들은 2나 3의 배수로 이미 삭제되었기 때문
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            long a = Long.parseLong(st.nextToken()) - 1;
            long b = Long.parseLong(st.nextToken());
            long n = Long.parseLong(st.nextToken());

            // n을 소인수 분해하고, n을 이루는 소수의 리스트를 구함
            List<Long> primes = getPrimes(n);

            // ([1, b] 구간에서 n과 서로소인 숫자의 개수) - ([1, a-1] 구간에서 n과 서로소인 숫자의 개수)를 구함
            // 서로소인 숫자의 개수는 (구간의 길이) - (n과 서로소가 아닌 숫자의 개수)로 구할 수 있음
            long res = b - calcNotCoprime(primes, 0, 0, b, 1) - (a - calcNotCoprime(primes, 0, 0, a, 1));
            sb.append(String.format("Case #%d: ", t)).append(res).append('\n');
        }

        System.out.print(sb);
    }

    // 소수의 곱들 중 [1, num] 구간 안에 있는 소수의 곱의 배수의 개수
    // 소수의 곱은 조합 재귀를 이용해서 만듦 (원래는 부분집합인데 잘 조절하면 조합 코드로 만들 수 있으니까)
    static long calcNotCoprime(List<Long> primes, int idx, int cnt, long num, long factor) {
        // 만약 num 보다 소수의 곱이 더 크다면 더 이상 볼 필요 없음
        if (num < factor) {
            return 0;
        }

        long res = 0;
        // 만약 지금 계산된 소수의 곱이 있다면 결과에서 빼줌
        // 계산한 값을 빼는 이유는 다음과 같음
        // idx가 홀수인 상황에서 이 때 빠진 값은 최종적으로 더해짐
        // idx가 짝수인 상황에서 이 때 빠진 값은 최종적으로 빠짐
        // 이 방법을 쓰면 포함 배제 원리를 이용해서 중복되지 않게 n과 서로소가 아닌 숫자의 개수를 셀 수 있음
        if (factor > 1) {
            res -= num / factor;
        }

        for (int i = cnt; i < primes.size(); i++) {
            // 다음 소수의 곱들을 계산하고 결과를 res에서 뺌
            res -= calcNotCoprime(primes, idx + 1, i + 1, num, factor * primes.get(i));
        }

        return res;
    }

    static List<Long> getPrimes(long n) {
        long originN = n;

        List<Long> primes = new ArrayList<>();

        // n이 2의 배수라면
        if ((n & 1) == 0) {
            primes.add(2L); // 소수 리스트에 2를 넣고

            // 2로 나누어 떨어지지 않을 때까지 2로 나눔
            while ((n & 1) == 0) {
                n >>= 1;
            }
        }

        // 홀수 i에 대하여
        for (long i = 3; i * i <= originN; i += 2) {

            // n이 i로 나누어 떨어진다면
            if (n % i == 0) {
                // 소수 리스트에 i를 넣고
                primes.add(i);

                // i로 나누어 떨어지지 않을 때까지 i로 나눔
                while (n % i == 0) {
                    n /= i;
                }
            }
            // i로 나누어 떨어지지 않을 때까지 나누었기 때문에
            // 9나 25와 같은 홀수로는 나누어 떨어지지 않음
        }

        // 만약 n이 1이 아니라면 남은 n도 소수이므로 소수 리스트에 저장
        if (n != 1) {
            primes.add(n);
        }

        return primes;
    }
}