//제출번호: 89412137
//메모리:   46048 KB
//실행시간: 328 ms
import java.util.*;
import java.io.*;

public class Main {
    static int[][] dp = new int[100001][2];
	public static void main(String[] args) throws IOException {
	    BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
	    
	    int n = Integer.parseInt(re.readLine());
	    for (int i = 1; i <= n; i++) {
	        StringTokenizer st = new StringTokenizer(re.readLine());
	        st.nextToken(); st.nextToken();
	        int num = Integer.parseInt(st.nextToken());
	        
            dp[i][1] = dp[i-1][0] + num;
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
	    }
	    System.out.print(Math.max(dp[n][0], dp[n][1]));
	}
}

/* 메모리 줄인 버전 - dp크기를 줄였는데 메모리 티가 안 남..
//제출번호: 89412322
//메모리:   43204 KB
//실행시간: 328 ms
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
	    
	    StringTokenizer st;
	    int[][] dp = new int[2][2];
	    int num, c, p;
	    int n = Integer.parseInt(re.readLine());
	    for (int i = 1; i <= n; i++) {
	        st = new StringTokenizer(re.readLine());
	        st.nextToken(); st.nextToken();
	        num = Integer.parseInt(st.nextToken());
	        c = i % 2;
	        p = (i - 1) % 2;
	        
            dp[c][1] = dp[p][0] + num;
            dp[c][0] = Math.max(dp[p][0], dp[p][1]);
	    }
	    System.out.print(Math.max(dp[n%2][0], dp[n%2][1]));
	}
}
*/