import java.io.*;
import java.util.*;

/*
11760KB, 80ms

[사고 흐름]
에너지의 양이 제곱꼴로 늘어나서 많이 점프하면 오히려 손해
적게 점프하는게 이득인가? -> 예제 6을 보면 이것도 아님. 친절한 문제네

그리디하게 선택하는 것이 답이 아닌듯 DP로 가보자
1차원 DP를 만들고 n^2을 돌리면 풀리겠다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
    	int n = nextInt();
    	String block = nextToken();
    	
    	//1차원 DP 생성
    	//DP[i]: i번째 블록에서 얻을 수 있는 최소 에너지
    	int[] dp = new int[n];
    	//최소 에너지를 구해야하므로 초기값은 적당히 큰 수로 초기화.
    	//Integer.MAX_VALUE로 초기화하면 오버플로우 위험 있음
    	Arrays.fill(dp, 10_000_000);
    	//0번째 타일의 에너지는 반드시 0
    	dp[0] = 0;
    	
    	//i번째 타일을 보며 j번째 타일의 dp값을 갱신
    	for (int i = 0; i < n; i++) {
    		char curC = block.charAt(i);
    		char nextC = getNextChar(curC);
    		for (int j = i + 1; j < n; j++) {
    			//i < j를 만족시키는 j가 다음 타일이라면 dp 값을 최소로 갱신
    			if (block.charAt(j) != nextC) continue;
    			int e = (j - i) * (j - i);
    			dp[j] = Math.min(dp[j], dp[i] + e);
    		}
    	}

    	//n번째 타일의 최소 에너지 출력
    	System.out.println(dp[n - 1] == 10_000_000 ? -1 : dp[n - 1]);
    }
    
    static char getNextChar(char c) {
    	if (c == 'B') return 'O';
    	else if (c == 'O') return 'J';
    	else return 'B';
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
    
    static String nextToken() throws Exception {
    	st.nextToken();
    	return st.sval;
    }
}