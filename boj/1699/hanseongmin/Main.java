import java.io.*;

/*
 * 12456KB, 116ms
 * 
 * dp[i]: i번째 수를 제곱 수의 합으로 표현할 때 항의 최소개수
 * 
 * 제곱수면 1, 
 * 제곱수가 아니면 자신보다 값이 작은 제곱수를 찾고
 * 해당 제곱수와 합이 자기 자신이 되는 dp 값을 더하기
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N;
    
    public static void main(String[] args) throws Exception {
    	N = nextInt();
    	int[] dp = new int[100001];
    	dp[1] = 1; dp[2] = 2; dp[3] = 3;
    	for (int i = 4; i <= N; i++) {
    		//제곱수라면 1
    		if ((int)Math.sqrt(i) * (int)Math.sqrt(i) == i) {
    			dp[i] = 1;
    		} else {
    			//제곱수가 아니면 자기보다 작은 제곱수를 찾음
	    		int fs = (int)Math.floor(Math.sqrt(i));
	    		dp[i] = dp[fs * fs] + dp[i - fs * fs];
	    		for (int j = fs; j >= 1; j--) {
	    			dp[i] = Math.min(dp[i], dp[j * j] + dp[i - j * j]); 
	    		}
    		}
    	}
    	
    	System.out.println(dp[N]);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}