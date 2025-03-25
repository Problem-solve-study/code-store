import java.io.*;
import java.util.*;

/*
 * 33268KB, 328ms
 * 
 * 귀도 반 로섬 문제가 생각났던 문제.
 * 스택써서 문제에서 주어진대로 뚝딱뚝딱 만들면 된다.
 * 정답 비율이 상당히 낮은데, 실수할만한 구석이 많아서 그런거같다. 나도 2틀함 
 * 꼼꼼하게만 짠다면 스택을 써야한다는 점도 문제에서 주어지고 구현의 난해함도 딱히 없다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	final int LIMIT = 1_000_000_000;
    	StringBuilder sb = new StringBuilder();
    	ArrayDeque<Long> stack = new ArrayDeque<>();
    	
    	program:
    	while (true) {
    		sb.append('\n');
    		ArrayDeque<String> codes = new ArrayDeque<>();
    		ArrayDeque<Long> inputs = new ArrayDeque<>();
    		
	    	//명령어 입력받기
	    	while (true) {
	    		String code = br.readLine();
	    		if (code.equals("END"))
	    			break;
	    		if (code.equals("QUIT"))
	    			break program;
	    		codes.add(code);
	    	}
	    	
	    	//입력값 입력받기. MUL 명령어 수행 시 오버플로우 발생 가능하므로 long으로
	    	int n = Integer.parseInt(br.readLine());
	    	for (int i = 0; i < n; i++) {
	    		inputs.add(Long.parseLong(br.readLine()));
	    	}
	    	br.readLine();
	    	
	    	//명령어 실행
	    	for (long input : inputs) {
	    		boolean isError = false;
	    		stack = new ArrayDeque<>();
	    		stack.add(input);
	    		for (String code : codes) {
	    			try {
		    			if (code.equals("POP")) {
		    				stack.removeLast();
		    			} else if (code.equals("INV")) {
		    				stack.add(-stack.removeLast());
		    			} else if (code.equals("DUP")) {
		    				stack.add(stack.peekLast());
		    			} else if (code.equals("SWP")) {
		    				//이항 연산자들은 첫 번째 숫자가 먼저 들어가야하므로 숫자를 변수에 담아서 순서에 맞게 넣는다.
		    				long n1 = stack.removeLast();
		    				long n2 = stack.removeLast();
		    				stack.add(n1); stack.add(n2);
		    			} else if (code.equals("ADD")) {
		    				stack.add(stack.removeLast() + stack.removeLast());
		    			} else if (code.equals("SUB")) {
		    				long n1 = stack.removeLast();
		    				long n2 = stack.removeLast();
		    				stack.add(n2 - n1);
		    			} else if (code.equals("MUL")) {
		    				stack.add(stack.removeLast() * stack.removeLast());
		    			} else if (code.equals("DIV")) {
		    				long n1 = stack.removeLast();
		    				long n2 = stack.removeLast();
		    				//나눗셈의 연산 자체는 절댓값으로 수행
		    				long res = Math.abs(n2) / Math.abs(n1);
		    				//이후 피연산자 중 음수가 1개라면 몫의 부호를 음수로
		    				if ((n1 > 0 && n2 < 0) || (n1 < 0 && n2 > 0)) {
		    					res *= -1;
		    				}
		    				stack.add(res);
		    			} else if (code.equals("MOD")) {
		    				long n1 = stack.removeLast();
		    				long n2 = stack.removeLast();
		    				//모듈러의 연산 자체는 절댓값으로 수행
		    				long res = Math.abs(n2) % Math.abs(n1);
		    				//피제수의 부호가 음수라면 결과값의 부호를 음수로
		    				if (n2 < 0) {
		    					res *= -1;
		    				}
		    				stack.add(res);
		    			} else { //NUM
		    				StringTokenizer st = new StringTokenizer(code);
		    				st.nextToken();
		    				long num = Long.parseLong(st.nextToken());
		    				stack.add(num);
		    			}
		    			
		    			//이 부분 때문에 2틀
		    			//stack이 empty일 때는 여기서 에러가 나면 안된다.
		    			if (!stack.isEmpty() && Math.abs(stack.peekLast()) > LIMIT) {
		    				isError = true;
		    				break;
		    			}
	    			} catch (Exception e) {
	    				//그 외 0으로 나눈다던가 하는 경우에는 try-catch로 에러를 잡음
	    				isError = true;
	    				break;
	    			}
	    		}
	    		
	    		if (stack.size() != 1 || isError) {
	    			sb.append("ERROR");
	    		} else {
	    			sb.append(stack.peekLast());
	    		}
	    		sb.append('\n');
	    	}
    	}
    	
    	System.out.print(sb.toString().trim());
    }
}