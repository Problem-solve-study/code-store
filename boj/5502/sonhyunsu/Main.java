//제출번호: 93474703
//메모리:   109940 KB
//실행시간: 304 ms
import java.io.*;

//펠린드롬을 만들기 위해 추가하는 최소 문자 개수를 구하라고 했을 때 바로 DP가 떠올랐음
//DP[s][e]: [s, e] 구간만큼 문자열을 사용했을 때 펠린드롬을 만들기 위해서 필요한 최소 문자 개수

//1. s와 e의 문자가 서로 같으면
//DP[s][e] = DP[s+1][e-1]임

//2. s와 e의 문자가 서로 다르면
//s 문자를 e 옆에 붙이거나, e 문자를 s 옆에 붙어야 함
//이 때 s문자를 e 옆에 붙이면 [s+1, e] 구간의 펠린드롬 문자열의 양쪽에 s를 붙이는 형태가 되고
//e문자를 s 옆에 붙이면 [s, e-1] 구간의 펠린드롬 문자열의 양쪽에 e를 붙이는 형태가 됨
//따라서 DP[s][e] = min(DP[s+1][e], DP[s][e-1]) + 1임  
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        char[] line = br.readLine().toCharArray();

        int[][] dp = new int[n][n];

		//구간의 길이를 늘리면서 DP를 업데이트 함
        for (int len = 1; len < n; len++) {
            for (int s = 0, e = s + len; e < n; s++, e++) {
				//s의 문자와 e의 문자가 같다면 DP[s+1][e-1]
				//s의 문자와 e의 문자가 다르다면 min(DP[s,e-1], DP[s+1][e]) + 1
                dp[s][e] = line[s] == line[e] ? dp[s+1][e-1] : Math.min(dp[s][e-1], dp[s+1][e]) + 1;
            }
        }

		//전체 구간에 대해서 필요한 최소 문자 개수를 출력
        System.out.print(dp[0][n-1]);
    }
}