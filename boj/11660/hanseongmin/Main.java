import java.io.*;
import java.util.*;

/*
 * 30916KB, 376ms
 * 
 * 2차원 누적합 기본 문제
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    
    public static void main(String[] args) throws Exception {
    	int n = nextInt();
    	int m = nextInt();
    	//배열을 입력 받음
    	int[][] arr = new int[n + 1][n + 1];
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			arr[i][j] = nextInt();
    		}
    	}
    	
    	//누적합 배열 생성
    	int[][] sum = new int[n + 1][n + 1];
    	for (int i = 1; i <= n; i++) {
    		for (int j = 1; j <= n; j++) {
    			sum[i][j] = arr[i][j] + sum[i - 1][j] + sum[i][j - 1] - sum[i - 1][j - 1];
    		}
    	}
    	
    	//누적합 배열을 이용하여 연산
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < m; i++) {
    		int r1 = nextInt();
    		int c1 = nextInt();
    		int r2 = nextInt();
    		int c2 = nextInt();
    		sb.append(sum[r2][c2] - sum[r1 - 1][c2] - sum[r2][c1 - 1] + sum[r1 - 1][c1 - 1]).append('\n');
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}