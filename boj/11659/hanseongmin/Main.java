import java.io.*;
import java.util.*;

/*
22992KB, 296ms
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    
    public static void main(String[] args) throws Exception {
    	int n = nextInt();
    	int m = nextInt();
    	//값을 입력받으면서 바로 누적합 배열 생성
    	int[] arr = new int[n + 1];
    	for (int i = 1; i <= n; i++) {
    		arr[i] = arr[i - 1] + nextInt();
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	//누적합 배열을 바탕으로 구간 합을 바로 계산
    	for (int i = 0; i < m; i++) {
    		sb.append(Math.abs(arr[nextInt() - 1] - arr[nextInt()])).append('\n');
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}