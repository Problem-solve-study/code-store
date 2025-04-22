import java.io.*;
import java.util.*;

/*
 * 13004KB, 168ms
 * 
 * 결국 연속된 K 구간에서 최소 표준편차를 구하는 문제다.
 * N은 500이라 N^2으로 충분히 구할 수 있고
 * 표준편차식은 문제에서 주어지니 그대로 구현
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, K;
    static int[] arr;
    
    public static void main(String[] args) throws Exception {
    	N = nextInt();
    	K = nextInt();
    	arr = new int[N];
    	for (int i = 0; i < N; i++) {
    		arr[i] = nextInt();
    	}
    	
    	double min = Integer.MAX_VALUE;
    	//가능한 모든 구간을 살펴보며 최소 표준편차 구하기
    	for (int s = 0; s < N; s++) {
    		for (int e = s + K - 1; e < N; e++) {
    			min = Math.min(min, get표준편차(s, e));
    		}
    	}
    	System.out.print(min);
    }
    
    //표준편차와 평균은 문제에서 주어진 식 그대로 구현
    static double get표준편차(int s, int e) {
    	double 평균 = get평균(s, e);
    	double sum = 0;
    	for (int i = s; i <= e; i++) {
    		sum += ((arr[i] - 평균) * (arr[i] - 평균));
    	}
    	return Math.sqrt(sum / (e - s + 1));
    }
    
    static double get평균(int s, int e) {
    	double sum = 0;
    	for (int i = s; i <= e; i++) {
    		sum += arr[i];
    	}
    	return sum / (e - s + 1);
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}