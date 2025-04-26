//제출번호:	93608637
//메모리:	22084 KB
//실행시간:	240 ms
import java.io.*;
import java.util.*;

//문제를 보니까 이거 정렬해서 쓰면 쉽게 풀 수 있겠다 싶었음
//정렬 후 투 포인터를 이용해서 비교해야 할 쌍의 개수를 구했음
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int[] files = new int[n];
	    for (int i = 0; i < n; i++) {
	        files[i] = nextInt();
	    }
	    
		//파일 크기 순으로 오름차순 정렬
	    Arrays.sort(files);
	    
	    long res = 0;
	    int compareIdx = 0;
	    for (int curIdx = 0; curIdx < n; curIdx++) {
	        double compareSize = 0.9 * files[curIdx]; //현재 파일 기준으로 비교할 파일 크기 계산

			//만약 비교 대상보다 작은 파일이면 
	        while (files[compareIdx] < compareSize) {
	            compareIdx++; //쌍을 만들 수 없는 파일이므로 다음으로 넘어감
	        }
	        
			//만들 수 있는 쌍의 구간을 결과에 더함
			//원래 만들 수 있는 쌍은 [compareIdx, curIdx - 1] 구간에 있는 파일들과 curIdx 파일임
			//구간의 길이를 구하면 curIdx - 1 - compareIdx + 1이 되고, 식을 정리하면 curIdx - compareIdx가 됨
	        res += curIdx - compareIdx;
	    }
	    
	    System.out.print(res);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}