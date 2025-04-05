//제출번호: 92644999
//메모리:   11912 KB
//실행시간: 948 ms
import java.io.*;
import java.util.*;

//팩토리얼의 각 자릿수만 세면 되어서 실제로 다 곱해보자고 생각함
//처음엔 최대 앞 8자리만 남겨도 자릿수 연산에 오차가 거의 없을 줄 알았음 => 틀림
//앞 11자리 정도 남겼을 때도 질문 게시판 예제 정답과 1씩 차이나는 것을 보고 자릿수를 더 남겨야 겠다고 생각함
//현재까지 곱한 수를 (num 10자리).(fract) 으로 표현하고
//num만 다음 팩토리얼 계산에 사용하니까 맞음 (부동 정밀도 계속 바뀌는데 이게 왜 맞지?)
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));    
    
    public static void main(String[] args) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    int T = nextInt();
	    long max = (long) 1e11;
	    for (int t = 0; t < T; t++) {
    	    long n = nextInt();
    	    int res = 0;
    	    long num = 1;
    	    long fract = 0;
    	    int fractLen = 0;
    	    
    	    for (int i = 1; i <= n; i++) {
              //정수부분과 소수부분 모두 i를 곱함
    	        num *= i;
    	        fract *= i;

              //소수부분에서 정수부분으로 넘어가야 할 값을 계산하고 정수부분에 더함
    	        while (fractLen > 0) {
    	            fract /= 10;
    	            fractLen--;
    	        }
    	        num += fract; 

              //정수부분을 10자리만 남기고 나머지는 소수부분으로 넘김 (소수부분은 초기화)
    	        fract = 0;
    	        int mul = 1;
    	        while (num >= max) {
    	            res++; //넘길 때마다 자릿수가 바뀌니까 res++
    	            fract += num % 10 * mul;
    	            num /= 10;
    	            mul *= 10;
    	            fractLen++;
    	        }
    	    }
        
          //정수부분의 자릿수만큼 res를 더함
    	    while (num > 0) {
    	        res++;
    	        num /= 10;
    	    }
	        sb.append(res).append('\n');
	    }
	    
	    System.out.print(sb);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}
