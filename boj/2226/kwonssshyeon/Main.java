import java.math.BigInteger;
import java.util.*;

/**
 * 1
 * 01
 * 1001
 * 01101001
 * 
 * 01 은 다음 턴에 1001로 치환되므로 연속된 0그룹이 1개가 된다.
 * 그외의 경우는 0이 연속될 수 없기 때문에 한 글자마다 0을 1개씩 가지게 된다.
 * 즉, 이전에 나온 01의 개수 + 01 이 아닌 1,0 의 개수가 이번 턴의 답이 된다.
 * 
 * 몇개 더 해보고 DP다 생각했는데, 점화식을 못찾겠어서
 * https://velog.io/@xx0hn/BOJ-Python-2226%EB%B2%88-%EC%9D%B4%EC%A7%84%EC%88%98
 * 참고했어요 ...
 *
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n < 3) {
            if (n == 1) System.out.println(0);
            else System.out.println(1);
        }
        else {       
            BigInteger[] dp = new BigInteger[n];
            dp[0] = BigInteger.ZERO;
            dp[1] = BigInteger.ONE;
            dp[2] = BigInteger.ONE;

            for (int i = 3; i < n; i++) {
                dp[i] = dp[i-1].add(dp[i-2].multiply(BigInteger.valueOf(2)));
            }

            System.out.println(dp[n - 1]);
        }
        sc.close();
    }
}