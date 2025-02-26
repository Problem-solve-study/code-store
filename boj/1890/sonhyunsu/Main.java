//제출번호: 90577115
//메모리:   11884 KB
//실행시간: 68 ms
import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        //[n - 1][n - 1]을 넘어가는 jump도 인덱스 에러가 나지 않도록 하기 위해 여유롭게 둠
        long[][] dp = new long[n + 10][n + 10];

        dp[0][0] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int jump = nextInt();

                //만약 뛸 수 있으면 오른쪽과 아래쪽으로 가는 경로를 추가
                if (jump != 0) {
                    dp[i][j + jump] += dp[i][j];
                    dp[i + jump][j] += dp[i][j];
                }
            }
        }

        //도착지점의 경우의 수 출력
        System.out.print(dp[n - 1][n - 1]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}