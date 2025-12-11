// 11584KB  68ms
import java.io.*;
import java.util.*;

public class Main {

    static boolean[] isPrime = new boolean[100_001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        init();

        while (true) {
            String s = br.readLine();
            if (s.equals("0")) break;
            int max = 0;
            int len = s.length();

            for (int i=0; i<len; i++) {
                int num = 0;
                // 길이가 최대 5인 경우까지 계산
                for (int j = 0; j<5; j++) {
                    if (i + j >= len) break;
                    num = num * 10 + (s.charAt(i + j) - '0');
                    if (num > 100_000) break;
                    if (isPrime[num]) {
                        max = Math.max(num, max);
                    }
                }
            }
            sb.append(max).append("\n");
        }

        System.out.print(sb);
    }

    // 여러 테스트 케이스에서 재사용하기 위해 미리 계산
    static void init() {
        Arrays.fill(isPrime, true);
        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= 100000; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j <= 100000; j += i) {
                isPrime[j] = false;
            }
        }
    }
}
