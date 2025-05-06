//제출번호: 93947749
//메모리:   22316 KB
//실행시간: 256 ms
import java.io.*;
import java.util.*;

//난로를 최소한으로 켜려면 친구의 방문 텀이 가장 짧은 순으로 선택을 해야 함
//n-k만큼 선택한 후 나머지는 성냥으로 난로를 켜면 됨
//성냥으로 난로를 켜는 것도 난로 가동 시간에 들어간다는 점을 주의할 것
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int k = nextInt();
	    
	    int[] diff = new int[n - 1];
	    int prev = nextInt();
	    for (int i = 1, j = 0; i < n; i++, j++) {
	        int cur = nextInt();
	        diff[j] = cur - prev; //차이 값을 diff에 저장, 이 때 차이값은 (prev, cur] 구간의 길이임
	        prev = cur;
	    }
	    
	    Arrays.sort(diff); //가장 작은 텀이 먼저 나오도록 정렬
	    
	    int res = k; //k번은 성냥으로 난로를 켬
	    for (int i = k, j = 0; i < n; i++, j++) {
	        res += diff[j]; //가장 짧은 텀을 n-k번 선택
	    }
	    
	    System.out.print(res);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}