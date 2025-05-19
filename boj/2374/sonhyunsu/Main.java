//제출번호: 94140908
//메모리:   12648 KB
//실행시간: 72 ms
import java.io.*;
import java.util.*;

//기본적으로 주어진 배열에서 극댓값과 극솟값을 찾아서 극댓값 - 극솟값 만큼 Add연산을 해야 함
//하지만 모든 위치들을 찾는 건 까다롭기 때문에 스택을 이용해서 연산횟수를 구하고자 했음
//정답은 10^25 이하라고 했는데 계산해보니까 500 * 1e9 가 최댓값인 것 같음 (1 1e9 1 1e9 ...)
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    
	    long res = 0;
	    Deque<Integer> dq = new ArrayDeque<>(); 
	    for (int i = 0; i < n; i++) {
	        int num = nextInt();
            
            //스택의 원소가 내림차순이 되도록 만듦
            //스택의 top이 num보다 작으면 num - stack[top]만큼 Add연산이 필요함
            if (!dq.isEmpty() && dq.peekLast() <= num) {
	            res += num - dq.pollLast();

                //stack[top] 높이부터 num 높이까지 평탄화되었기 때문에 num 이하인 높이들은 stack에 필요 없음
	            while (!dq.isEmpty() && dq.peekLast() <= num) {
                    dq.pollLast();
	            }
            }

            //num 높이를 stack에 추가
	        dq.addLast(num);
	    }
	    
        //만약 stack이 비어있지 않다면 내림차순의 높이들도 평탄화 해야 함
	    if (!dq.isEmpty()) {
            //stack에 저장된 (가장 높은 높이) - (가장 낮은 높이) 만큼 Add연산이 필요함
	        res += dq.peekFirst() - dq.peekLast();
	    }
	    
	    System.out.print(res);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}