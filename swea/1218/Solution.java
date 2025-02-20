import java.io.*;
import java.util.*;

/*
 * 24320KB, 101ms
 */

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
    public static void main(String[] args) throws Exception {
    	HashMap<Character, Character> map = new HashMap<>();
    	map.put(')', '(');
    	map.put('}', '{');
    	map.put(']', '[');
    	map.put('>', '<');
    	
    	StringBuilder sb = new StringBuilder();
    	for (int t = 1; t <= 10; t++) {
    		br.readLine();
    		ArrayDeque<Character> s = new ArrayDeque<>();
    		String line = br.readLine();
    		int l = line.length();
    		for (int i = 0; i < l; i++) {
    			char c = line.charAt(i);
    			//여는 괄호면 스택에 push
    			if (c == '(' || c == '{' || c == '[' || c == '<') {
    				s.addLast(c);
    			} else {
    				//닫는 괄호가 들어왔는데 스택이 비어있거나
    				//짝과 맞지 않는 괄호라면 유효하지 않음
    				if (s.isEmpty() || s.peekLast() != map.get(c)) {
    					break;
    				} else {
    					s.removeLast();
    				}
    			}
    		}
    		
    		sb.append(String.format("#%d %d\n", t, s.isEmpty() ? 1 : 0));
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
}