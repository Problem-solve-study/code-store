// 47436KB	180ms
import java.io.*;
import java.util.*;

/**
 * 두 문자열의 탐색위치 i,j 라 했을때
 * 1. i,j 를 비교하는 경우
 * 2. i 와 공백을 비교하는 경우
 * 3. j 와 공백을 비교하는 경우
 * 3가지로 나누어 확인해야함.
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int a, b, c;
    static char[] str1, str2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        
        str1 = br.readLine().toCharArray();
        str2 = br.readLine().toCharArray();
        System.out.println(calculate());
    }
    
    static int calculate() {
        int n = str1.length;
        int m = str2.length;
        int[][] dp = new int[n+1][m+1];

        // 배열 초기화 -> 전부 다 공백과 비교한 값으로 초기화 함.
        // a or c 로 갱신할때는 최댓값 비교를 하지 않았기 때문에 b * (각 위치 == 비교횟수)로만 초기화 해도 충분
        for (int i=0; i<=n; i++) {
            for (int j=0; j<=m; j++) {
                dp[i][j] = b * (i + j);
            }
        }
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (str1[i-1] == str2[j-1]) {
                    dp[i][j] = dp[i-1][j-1] + a;
                } else {
                    dp[i][j] = dp[i-1][j-1] + c;
                }
                // c보다 b가 유리한 경우, 여기서 갱신
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i-1][j] + b, dp[i][j-1] + b));
            }
        }
        return dp[n][m];
    }
}
