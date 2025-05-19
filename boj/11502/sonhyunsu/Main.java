//제출번호: 94362322
//메모리:   11948 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//1000이하의 소수를 모두 구하고 소수 두 개를 고른 뒤, n - a - b 를 이용해서 나머지 하나를 구함
//나머지 하나도 소수인지 판단하면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        List<Integer> primes = new ArrayList<>();
        boolean[] isPrime = new boolean[1001];
        isPrime[0] = isPrime[1] = true;
        for (int i = 2; i * i < 1001; i++) {
            if (!isPrime[i]) {
                primes.add(i);
                for (int j = i * i; j < 1001; j += i) {
                    isPrime[j] = true;
                }
            }
        }

        int t = nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = nextInt();

            boolean found = false;

            find:
            for (int a : primes) {
                for (int b : primes) {
                    if (n - a - b > 0 && !isPrime[n - a - b]) {
                        sb.append(a).append(' ').append(b).append(' ').append(n - a - b);
                        found = true;
                        break find;
                    }
                }
            }

            if (!found) {
                sb.append(0);
            }

            sb.append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}