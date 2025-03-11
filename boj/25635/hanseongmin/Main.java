import java.io.*;
import java.util.*;

/*
 * 20608KB, 308ms
 * 
 * max를 먼저 배정하고 이후의 값들을 배정한다는 아이디어를 떠올리기 굉장히 힘들었다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N;
	static long[] arr;
	
    public static void main(String[] args) throws Exception {
    	N = nextInt();
    	arr = new long[N];
    	for (int i = 0; i < N; i++) {
    		arr[i] = (long) nextInt();
    	}
    	
    	long max = Arrays.stream(arr).max().orElse(0);
    	long sum = Arrays.stream(arr).sum() - max;
    	if (max <= sum + 1) {
    		System.out.println(sum + max);
    	} else {
    		System.out.println(sum + sum + 1);
    	}
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}