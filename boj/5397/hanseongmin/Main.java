import java.io.*;
import java.util.*;

/*
 * 222928KB, 740ms
 * 
 * 연결리스트를 써서 구현해야하는 것 같았는데 자바 연결리스트는 좀 하자가 많으므로 배제
 * 어차피 커서 기준으로 양 끝에서 삽입과 삭제가 이루어질 것이므로 덱이 떠올랐다.
 * 커서 기준 왼쪽 문자를 담는 덱과 오른쪽 문자를 담는 덱을 선언하고
 * 커서의 이동에 따라 적절히 원소를 이동시켜준다.
 */

public class Main {
	static int T;
	static String pw;
	static StringBuilder sb = new StringBuilder();
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	T = Integer.parseInt(br.readLine());
    	for (int t = 0; t < T; t++) {
    		pw = br.readLine();
    		ArrayDeque<Character> lq = new ArrayDeque<>();
    		ArrayDeque<Character> rq = new ArrayDeque<>();
    		for (int i = 0; i < pw.length(); i++) {
    			if (pw.charAt(i) == '<') {
    				if (!lq.isEmpty())
    					rq.addFirst(lq.removeLast());
    			} else if (pw.charAt(i) == '>') {
    				if (!rq.isEmpty())
    					lq.addLast(rq.removeFirst());
    			} else if (pw.charAt(i) == '-') {
    				if (!lq.isEmpty())
    					lq.removeLast();
    			} else {
    				lq.addLast(pw.charAt(i));
    			}
    		}
    		lq.forEach(sb::append);
    		rq.forEach(sb::append);
    		sb.append('\n');
    	}
    	System.out.print(sb);
    }
}