//제출번호: 89296957
import java.util.*;

public class Main {
    static Stack<Integer> s = new Stack<>();
	public static void main(String[] args) {
        String line = new Scanner(System.in).next();
	    
	    s.push(-1);
	    for (char c : line.toCharArray()) {
	        switch (c) {
	            case 'H': s.push(1); break;
	            case 'C': s.push(12); break;
	            case 'O': s.push(16); break;
	            case '(': s.push(-1); break;
	            case ')': bracketSum(); break;
	            default: s.push(s.pop() * (c - '0'));
	        }
	    }
	    bracketSum();
	    System.out.print(s.pop());
	}
	
	public static void bracketSum() {
	    int res = 0;
	    int val;
	    while (!s.isEmpty() && (val = s.pop()) != -1) {
	        res += val;
	    }
	    s.push(res);
	}
}