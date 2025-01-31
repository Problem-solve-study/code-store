//14228KB, 108ms
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String Formula = br.readLine();
		Stack<Integer> stack = new Stack<>();
		int sum = 0;
		for (int i = 0; i < Formula.length(); i++) {
			char c = Formula.charAt(i);
			if (c == '(')
				stack.push(0);
			else if (c == 'C')
				stack.push(12);
			else if (c == 'H')
				stack.push(1);
			else if (c == 'O')
				stack.push(16);
			else if (c >= '2' && c <= '9') {
				int num = stack.pop() * (c - '0');
				stack.push(num);
			}
			else if (c == ')'){
				int temp = 0;
				while (true){
					if (stack.peek() == 0){
						stack.pop();
						break;
					}
					temp += stack.pop();
				}
				stack.push(temp);
			}
		}
		while (!stack.isEmpty())
			sum += stack.pop();
		System.out.println(sum);
	}
}
