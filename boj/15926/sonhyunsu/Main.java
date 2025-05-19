//제출번호: 94446961
//메모리:   16684 KB
//실행시간: 108 ms
import java.io.*;

//쉽게 풀 수 있을 줄 알았는데 4트나 해서 슬펐음
//여는 괄호일 떄는 -1 삽입만 함
//닫는 괄호일 때는 스택의 top에 뭐가 있는 지 확인
// 1. 여는 괄호의 의미인 -1이 있다면 빼고 2를 넣음
// 2. 숫자가 있다면 숫자를 뺀 뒤에 여는 괄호가 있는 지 확인
// 2-1. 여는 괄호가 있다면 -1을 빼고 숫자 +2를 넣음
// 그 외는 올바른 괄호를 이어나갈 수 없으므로 아무것도 집어 넣지 않음
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    int n = Integer.parseInt(br.readLine());
	    char[] brackets = br.readLine().toCharArray();
	    int[] stack = new int[n];
	   
	    int max = 0, pos = -1;
    	for (int i = 0; i < n; i++) {
    	    if (brackets[i] == '(') {
    	        stack[++pos] = -1;
    	    } else {
    	        int num = 0;
    	        if (pos >= 0 && stack[pos] > 0) {
    	            num += stack[pos--];
    	        }
    	        
    	        if (pos >= 0 && stack[pos] == -1) {
    	            pos--;
    	            num += 2;
    	            
    	            if (pos >= 0 && stack[pos] > 0) {
    	                stack[pos] += num;
    	            } else {
    	                stack[++pos] = num;
    	            }
    	            
        	        max = Math.max(max, stack[pos]);
    	        }
    	        
	        }
    	}
    	
    	System.out.print(max);
	}
}