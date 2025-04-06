import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
	static String str ="";
	
	static boolean balancing() {
		Stack<Character> stk = new Stack<>();
		int len = str.length();
		for(int i =0;i<len;i++) {
			switch(str.charAt(i)) {
			case '[':
				stk.add('[');
				break;
			case ']':
				if(!stk.isEmpty() && stk.pop() == '[')
					break;
				else return false;
			case '(':
				stk.add('(');
				break;
			case ')':
				if(!stk.isEmpty() && stk.pop() == '(')
					break;
				else return false;
			}
		}
		if(stk.empty()) return true;
		return false;
	}
	
	
	static void input() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		while(!str.equals(".")) {
			if(balancing())
				System.out.println("yes");
			else 
				System.out.println("no");
			str = br.readLine();
		}
	}
	public static void main(String[] args) throws IOException {
		input();
	}
}
