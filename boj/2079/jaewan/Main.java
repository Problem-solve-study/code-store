
/*
 * 팰린드롬 구하기
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inStr = br.readLine();

        int len = inStr.length();
        boolean[][] isPalindrome = new boolean[len][len];

        for (int i = 0; i < len; i++)
            isPalindrome[i][i] = true;
        for (int i = 0; i < len - 1; i++)
            if (inStr.charAt(i) == inStr.charAt(i + 1))
                isPalindrome[i][i + 1] = true;

        for (int gap = 2; gap < len; gap++) {
            for (int i = 0; i + gap < len; i++) {
                if (isPalindrome[i + 1][i + gap - 1]
                        && inStr.charAt(i) == inStr.charAt(i + gap)) {
                    isPalindrome[i][i + gap] = true;
                }
            }
        }

        int[][] dp = new int[len][len];
        dp[0][0] = 1;
        for (int i = 1; i < len; i++) {
            // 이전까지의 값 + 현재 알파벳으로 초기화
            dp[0][i] = dp[0][i - 1] + 1;
            if (isPalindrome[0][i]) {
                dp[0][i] = 1;
                continue;
            }
            for (int j = i - 1; j > 0; j--) {
                if (isPalindrome[j][i])
                    dp[0][i] = Math.min(dp[0][i], dp[0][j - 1] + 1);
            }
        }
        System.out.println(dp[0][len - 1]);
    }
}