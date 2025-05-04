// 109604KB	2568ms
import java.io.*;
import java.util.*;

/**
 * 처음엔 팰린드롬 수를 구하고 나서 소수 판별을 하려고 했는데
 * 태그에 에라토스테네스의 체가 있어서 소수를 먼저 구해도 되나...? 생각함
 * 크기 1억의 배열을 만들면 메모리 초과에 소수판별에만 거의 n루트n이라 터질꺼라고 생각했는데 통과시켜줬다.
 */
public class Main {
    static boolean[] notPrime;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        notPrime = new boolean[b + 1];

        // 소수 마킹
        for (int i=2; i <= b; i++) {
            if (notPrime[i]) continue;

            for (int j = 2*i; j<=b; j+=i) {
                notPrime[j] = true;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i=a; i<=b; i++) {
            if (notPrime[i]) continue;
            if (isPalindrome(i)) {
                sb.append(i).append("\n");
            }
        }
        sb.append("-1");
        System.out.print(sb);
    }
    
    static boolean isPalindrome(int num) {
        if (num < 0 || (num % 10 == 0 && num != 0)) return false;

        int reversed = 0;
        while (num > reversed) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
        }
        return num == reversed || num == reversed / 10;
    }
}
