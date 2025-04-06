//Memory 14242kb Time 104ms.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N; // 뽑는 카드 수
    static int mod = 10007;
    static int[][] dp = new int[53][53]; // 조합 계산을 위한 메모이제이션 배열

    // 입력 처리 함수
    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
    }

    // 조합 계산 함수
    static int combination(int n, int r) {
        if (r == 0 || n == r) return 1; // 조합의 기본 조건
        if (dp[n][r] > 0) return dp[n][r]; // 메모이제이션 활용
        return dp[n][r] = (combination(n - 1, r - 1) + combination(n - 1, r))%mod;
    }

    // 풀이 함수
    static void solution() {
    	int ans = 0;
        for(int i = 1; i<=N/4;i++) {
        	if(i%2 == 1)
        		ans = (ans + combination(13, i)*combination(52-i*4, N-i*4))%mod;
        	else
        		ans = (ans - (combination(13, i)*combination(52-i*4, N-i*4)%mod)+mod)%mod;
        	
        }
        
        System.out.println(ans);
    }

    // 메인 함수
    public static void main(String[] args) throws IOException {
        input(); // 입력 처리
        solution(); // 풀이 수행
    }
}
