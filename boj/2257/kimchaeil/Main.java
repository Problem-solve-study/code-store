import java.util.*;
public class Main {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		Stack<Integer> stack = new Stack<>();
		int tmp;
		for(int i=0;i<str.length();i++) {
			switch(str.charAt(i)){
			case '(':
				stack.add(null);
				break;
			case ')':
				tmp=0;
				while(true) {
					Integer n = stack.pop();
					if(n==null)break;
					tmp+=n;
				}
				stack.add(tmp);
				break;
			case 'H':
				stack.add(1);
				break;
			case 'C':
				stack.add(12);
				break;
			case 'O':
				stack.add(16);
				break;
			default:
				tmp = stack.pop();
				tmp*=str.charAt(i)-'0';
				stack.add(tmp);
			}
		}
		tmp=0;
		while(!stack.isEmpty()) {
			tmp+=stack.pop();
		}
		System.out.println(tmp);
		sc.close();
	}
}
