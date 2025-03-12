import java.io.*;
import java.util.*;

/*
 * 11536KB, 68ms
 * 
 * 그냥 대놓고 문제에서 냅색이라고 말하고 있다.
 * 저번에 냅색을 1차원으로 구할 수 있다는 소리를 듣고 공부해서 적용시켜 봤다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
    public static void main(String[] args) throws Exception {
    	int N = nextInt();
    	int[] L = new int[N + 1];
    	int[] J = new int[N + 1];
    	for (int i = 1; i <= N; i++) {
    		L[i] = nextInt();
    	}
    	for (int i = 1; i <= N; i++) {
    		J[i] = nextInt();
    	}
    	
    	//1차원 냅색. dp[i]: 무게가 i일 때 최대 가치
    	int[] dp = new int[101];
    	//반복문 자체는 2차원이랑 동일하게 돌린다.
    	for (int i = 1; i <= N; i++) {
    		//단, 무게를 갱신할 때는 반드시 뒤에서부터 갱신해야지 확실하게 직전 값을 가져올 수 있다.
    		for (int j = 100; j >= 0; j--) {
    			//인덱스를 벗어나면 가지쳐주고
    			if (j - L[i] <= 0) continue;
    			//그게 아니면 현재 아이템을 선택하는 경우와 선택하지 않는 경우의 최댓값을 택함
    			dp[j] = Math.max(dp[j], dp[j - L[i]] + J[i]);
    		}
    	}
    	System.out.println(dp[100]);
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}