import java.io.*;
import java.util.*;

/*
 * 11864KB, 68ms
 * 
 * 처음에는 적당히 G일 때 이리저리하면 괄호가 되나? 싶었는데 잘안됐고 이거 어떻게하지 했었는데
 * N이 20인걸 보고 브루트포스라는걸 깨달음
 * 가능한 모든 문자열의 종류를 완전 탐색하며 올바른 괄호 문자열인 경우를 찾아주면 된다.
 */

public class Main {
	static ArrayDeque<Character> stack = new ArrayDeque<>();
	static String result = null;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		findStr(str, 0);
		System.out.print(result);
	}
	
	static void findStr(String str, int index) {
		//끝까지 도달하면 올바른 괄호 문자열인지를 판단하고 종료
		if (index == str.length()) {
			if (isValid(str)) {
				result = str;
			}
			return;
		}
		
		//현재 위치의 문자가 G가 아니라면 바로 다음 문자열로
		if (str.charAt(index) != 'G') {
			findStr(str, index + 1);
		} else {
			//현재가 G라면 (를 한 번 넣어보고 )를 한 번 넣어봄
			char[] strArr = str.toCharArray();
			strArr[index] = '('; 
			findStr(new String(strArr), index + 1);
			if (result != null) return;
			strArr[index] = ')';
			findStr(new String(strArr), index + 1);
		}
	}
	
	static boolean isValid(String s) {
		stack.clear();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				stack.add(c);
			} else if (c == ')') {
				if (stack.isEmpty() || stack.peekLast() == ')') {
					return false;
				}
				stack.removeLast();
			}
		}
		
		return stack.isEmpty();
	}
}