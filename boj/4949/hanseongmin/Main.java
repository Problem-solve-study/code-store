import java.io.*;
import java.util.*;

/*
 * 16512KB, 132ms
 * 
 * 기초 스택 문제
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		outer:
		while (true) {
			String line = br.readLine();
			if (line.equals(".")) {
				break;
			}
			
			ArrayDeque<Character> s = new ArrayDeque<>();
			for (int i = 0; i < line.length(); i++) {
				char curChar = line.charAt(i);
				if (curChar == '(' || curChar == '[') {
					s.addLast(curChar);
				} else if (curChar == ')') {
					if (s.isEmpty() || !(s.peekLast() == '(')) {
						sb.append("no").append('\n');
						continue outer;
					}
					s.removeLast();
				} else if (curChar == ']') {
					if (s.isEmpty() || !(s.peekLast() == '[')) {
						sb.append("no").append('\n');
						continue outer;
					}
					s.removeLast();
				}
			}
			
			if (s.isEmpty()) {
				sb.append("yes").append('\n');
			} else {
				sb.append("no").append('\n');
			}
		}
		System.out.print(sb);
	}
}
