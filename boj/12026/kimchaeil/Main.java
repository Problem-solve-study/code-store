//문제: 12026번 BOJ 거리
//메모리: 11920 KB
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
        }
        for (int i = 0; i < n - 1; i++) {
            int next = (arr[i] + 1) % 3; //다음으로 와야할 글자
            if (dp[i] != 0 || i == 0) { //출발지이거나 도달할 수 있는 지점이라면
                for (int j = i; j < n; j++) { //현재 위치 보다 뒤에 있는
                    if (arr[j] == next) { //다음 글자를 찾아서
                        int energy = dp[i] + (j - i) * (j - i); //현 위치에서 뛰었을 때 총 에너지 계산하여
                        dp[j] = dp[j] == 0 ? energy : Math.min(energy, dp[j]); //목적 위치의 dp값과 비교해서 작은값 저장, 만약 처음으로 도달했다면 비교없이 넣기
                    }
                }
            }
        }
        System.out.println(dp[n - 1] == 0 ? -1 : dp[n - 1]); //최종 목적지의 dp값이 0이 아니면 출력하고 0이면(도달할 수 없다면) -1출력
    }
}
