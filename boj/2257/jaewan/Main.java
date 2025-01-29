package workspace;

import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inStr = sc.next();
		Stack<Integer> stack = new Stack<>();
		int prev = 0, cur = 0;
		for (char ch : inStr.toCharArray()) {
			if (ch == 'C' || ch == 'O' || ch == 'H') {
				if (ch == 'C')
					prev = 12;
				if (ch == 'O')
					prev = 16;
				if (ch == 'H')
					prev = 1;
				cur += prev;
			}
			if ('2' <= ch && ch <= '9') {
				cur += prev * (ch - '1');
				prev = 0;
			}
			if (ch == '(') {
				stack.push(cur);
				cur = 0;
				prev = 0;
			}
			if (ch == ')') {
				prev = cur;
				cur = stack.pop();
				cur += prev;
			}
		}
		System.out.println(cur);
	}
}