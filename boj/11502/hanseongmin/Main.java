import java.io.*;
import java.math.BigInteger;
import java.util.*;

/*
16708KB, 188ms

미리 소수를 구해두고 n^2으로 2개의 소수를 고른다면 나머지 하나의 수는 유일하게 확정되므로
이 수가 소수인지 아닌지만 판별하면 된다.

문제 자체가 골드바흐의 약한 추측인데 이는 참으로 증명되었으므로 0인 경우는 없다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        //미리 1000까지의 소수를 구해둠
        ArrayList<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= 1000; i++) {
            if (BigInteger.valueOf(i).isProbablePrime(10)) {
                primes.add(i);
            }
        }

        while (T --> 0) {
            int K = nextInt();
            out:
            for (int i = 0; i < primes.size(); i++) {
                //첫 번째 소수 뽑기
                int a = primes.get(i);
                for (int j = i + 1; j < primes.size(); j++) {
                    //두 번째 소수 뽑기
                    int b = primes.get(j);
                    //첫 번째, 두 번째 수를 뽑았으니 c는 자연스럽게 확정됨
                    int c = K - a - b;
                    //이 값을 소수가 들어있는 리스트에 이분탐색으로 찾음
                    int idx = Collections.binarySearch(primes, c);
                    //만일 발견됐다면 c가 소수이므로 값을 적절히 출력
                    if (idx >= 0) {
                        int min = Math.min(a, Math.min(b, c));
                        int max = Math.max(a, Math.max(b, c));
                        int mid = a + b + c - min - max;

                        sb.append(min).append(' ').append(mid).append(' ').append(max).append('\n');
                        break out;
                    }
                }
            }
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
