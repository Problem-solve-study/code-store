//제출번호: 95036860
//메모리:   49756 KB
//실행시간: 804 ms

//B를 오름차순으로 줬기 때문에 문제 조건에 맞게 칠할 수 있는 위치를 이분탐색으로 찾으면 됨
import java.io.*;

public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
    static long[] a, b;
    
	public static void main(String[] args) throws IOException {
	    n = (int) nextLong();
	    a = new long[n];
	    b = new long[n];
	    
	    for (int i = 0; i < n; i++) {
	        a[i] = nextLong();
	    }
	    
	    for (int i = 0; i < n; i++) {
	        b[i] = nextLong();
	    }
	    
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < n; i++) {
	        long val = a[i];
	        int left = 0, right = n - 1, mid;
	        
	        while (left <= right) {
	            mid = (left + right) / 2;
	            
	            if (b[mid] <= val) {
	                left = mid + 1;
	            } else {
	                right = mid - 1;
	            }
	        }
	        
	        sb.append(right - i).append(' ');
	    }
	    
	    System.out.print(sb);
	}
	
	static long nextLong() throws IOException {
	    st.nextToken();
	    return (long) st.nval;
	}
}