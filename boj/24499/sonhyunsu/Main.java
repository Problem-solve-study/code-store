//제출번호: 94174762
//메모리:   16444 KB
//실행시간: 148 ms
import java.io.*;

//투 포인터를 이용해서 길이가 K인 애플파이의 맛있는 정도의 합의 최댓값을 구하면 됨
//원형이지만 길이를 2N으로 만들면 선형으로 만들 수 있음
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int k = nextInt();
	    int dn = n << 1;
	    
        //선형으로 변환
	    int[] arr = new int[dn + 1];
	    for (int i = 1; i <= n; i++) {
	        arr[i] = arr[n+i] = nextInt();
	    }
	    
        //누적합 만들기
	    for (int i = 1; i <= dn; i++) {
	        arr[i] += arr[i-1];
	    }
	    
	    int max = 0;
	    for (int i = 0; i < n; i++) {
            //나올 수 있는 모든 K 구간에 대해서 최댓값을 구함
	        max = Math.max(max, arr[k+i] - arr[i]);
	    }
	    
	    System.out.print(max);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}