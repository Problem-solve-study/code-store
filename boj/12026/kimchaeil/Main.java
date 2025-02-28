//문제: 12026번 BOJ 거리
//메모리: 11684 KB
//시간: 76 ms

/*
 * 앞에서 뒤로 가는 문제라 DP로 접근

 * 'B' = 66, 'O' = 79, 'J' = 74
 * ('B'-'B')%3 = 0
 * ('O'-'B')%3 = 1
 * ('J'-'B')%3 = 2
 *
 * 위를 이용해 입력을 받는다
 * +)'B'는 66으로 3의 배수이므로 'B'를 빼주지 않고 바로 %3을 해도 된다
 * ++)if문과 3항연산자로 인한 오버헤드가 생기는 것 같아서 dp를 dp[0]을 제외하고 모두 10_000_000(n의 최대값이 1000이므로 에너지는 999^2를 넘을 수 없다. 혹시 몰라 여유롭게 10_000_000으로 설정)으로 초기화하고 if문과 3항연산자를 제거하였다
 */

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String line = br.readLine();
        br.close();
        byte[] arr = new byte[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (byte) (line.charAt(i) % 3);
            dp[i] = 10_000_000;
        }        
        dp[0]=0;
        for (int i = 0; i < n - 1; i++) {
            int next = (arr[i] + 1) % 3; //다음으로 와야할 글자
            for (int j = i + 1; j < n; j++) {
                if (arr[j] == next) {
                    dp[j] = Math.min(dp[i] + (j - i) * (j - i), dp[j]);
                }
            }
        }
        System.out.println(dp[n - 1] == 10_000_000 ? -1 : dp[n - 1]); //최종 목적지의 dp값이 10_000_000이 아니면 출력하고 10_000_000이면(도달할 수 없다면) -1출력
    }
}
