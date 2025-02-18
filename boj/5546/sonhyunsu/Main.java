//제출번호: 90231700
//메모리:   11556 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//[날짜][파스타의 종류][연속으로 먹은 일수]를 기준으로 dp를 계산하면 풀리는 문제
//dp 식을 구현하는 게 매우매우 귀찮음
public class Main {

    static int dp[][][] = new int[101][4][3];
    static int specific[] = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            specific[Integer.parseInt(st.nextToken())] = Integer.parseInt(st.nextToken());
        }

        //만약 첫 날에 특정 파스타만 먹는다면
        if (specific[1] > 0) {
            dp[1][specific[1]][1] = 1; //특정 파스타만 가능한 가짓수에 추가한다.
        } else {
            dp[1][1][1] = dp[1][2][1] = dp[1][3][1] = 1; //모든 파스타를 가능한 가짓수에 추가한다.
        }

        for (int i = 2; i <= n; i++) {
            //만약 i번째 날에 특정 파스타만 먹는다면
            if (specific[i] > 0) {
                int food = specific[i]; 
                for (int prevFood = 1; prevFood < 4; prevFood++) {
                    //특정 파스타를 제외한 나머지 파스타를 i - 1일까지 먹은 가짓수를 더한다.
                    if (prevFood != food) {
                        dp[i][food][1] += dp[i - 1][prevFood][1] + dp[i - 1][prevFood][2];
                        dp[i][food][1] %= 10000;
                    }
                }
                //i - 1일에 특정 파스타를 먹은 경우에는 연속으로 먹었으니까 거기에 맞춰서 업데이트 한다.
                dp[i][food][2] = dp[i - 1][food][1];
            } else {
                //만약 모든 파스타를 먹을 수 있는 날이면
                //모든 파스타를 한 번씩 돌면서 dp를 업데이트 한다. dp식은 위의 if문과 동일하다.
                for (int food = 1; food < 4; food++) {
                    for (int prevFood = 1; prevFood < 4; prevFood++) {
                        if (prevFood != food) {
                            dp[i][food][1] += dp[i - 1][prevFood][1] + dp[i - 1][prevFood][2];
                            dp[i][food][1] %= 10000;
                        }
                    }
                    dp[i][food][2] = dp[i - 1][food][1];
                }
            }
        }
        
        //n일에 먹을 수 있는 모든 가짓수를 더한다.
        int res = 0; 
        for (int food = 1; food < 4; food++) {
            for (int count = 1; count < 3; count++) {
                res += dp[n][food][count];
            }
        }

        System.out.print(res % 10000);
    }
}