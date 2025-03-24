//제출번호: 91832593
//메모리:   13240 KB
//실행시간: 112 ms
import java.io.*;

//횡단보도를 잇는 모습이 전깃줄 문제와 비슷해서 LIS(N^2) 알고리즘을 채택
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt() + 1; // <= 대신 < 를 사용하기 위해 +1, 나중에 1 빼줘야 함
	    int[] left = new int[n];
	    int[] right = new int[n];
	    
	    for (int i = 1; i < n; i++) {
	        left[i] = nextInt();
	    }
	    for (int i = 1; i < n; i++) {
	        right[i] = nextInt();
	    }
	    
	    int[] dp = new int[n]; //이을 수 있는 최대 횡단보도 수
	    int[] max = new int[n]; //1~j 구간의 횡단보도 최댓값
	    for (int i = 1; i < n; i++) {
	        for (int j = 1; j < n; j++) {
                //만약에 횡단보도를 이을 수 있다면
	            if (Math.abs(left[i] - right[j]) <= 4) {
                    //1 ~ j - 1까지 이어진 횡단보도의 최댓값에서 1을 더한 값을 업데이트
	                dp[j] = Math.max(dp[j], max[j - 1] + 1);
	            }
	        }
	        
            //left[i]를 이어봤을 때의 횡단보도의 최댓값을 모두 구했다면
            //구간 최댓값을 갱신함.
	        max[1] = dp[1];
	        for (int j = 2; j < n; j++) {
	            max[j] = Math.max(max[j - 1], dp[j]);
	        }
	    }
	    
        //1 ~ n의 구간 최댓값을 구함
	    System.out.print(max[n - 1]);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}