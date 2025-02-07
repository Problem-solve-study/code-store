//19864KB, 236ms
import java.util.*;

public class Main {
	static int N, size;
	static char[] arr;
	static Set<String> numSet = new HashSet<>();
	static List<String> numList = new ArrayList<>();
	static List<Character> operList = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		String str = sc.next();
		size = N/2 + 1;
		arr = new char[size];
		int maxVal = Integer.MIN_VALUE;
		
		int idx = 0;
		for (int i = 0; i < str.length(); i++){
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9'){
				arr[idx++] = str.charAt(i); // 식에서 숫자만 추출
			}else
				operList.add(str.charAt(i)); // 식에서 연산자 추출
		}

		for (int i = 0; i <= size/2; i++) // 사용 가능한 괄호의 수 0 ~ arr.size()/2 만큼 dfs 
			dfs(0, i, 0,"");
        
        for (String formula : numList){
			int res = calculateFormula(formula);
			maxVal = Math.max(maxVal, res);
		}
		System.out.println(maxVal);
		sc.close();
	}

	public static void dfs(int idx, int maxPair, int pairCnt, String cur ){
		if (pairCnt > maxPair)
			return ;
		if (idx >= size){
			if (!numSet.contains(cur)){ // 중복되지 않은 괄호 조합
				numSet.add(cur);
				numList.add(addOperator(cur)); // 괄호 조합에 연산자 삽입
			}
			return ;
		}

		dfs(idx + 1, maxPair, pairCnt, cur + arr[idx]);
		if (idx < size - 1)
			dfs(idx + 2, maxPair, pairCnt, cur + "(" + arr[idx] + arr[idx + 1] + ")");
	}

	// 연산자를 끼워넣어서 문자열 반환하는 함수
	// ex) 입력값이 2(56) 이고 식에 들어가야 하는 연산자가 *,- 라면 반환값은 2*(5-6)
	public static String addOperator(String cur) {
		StringBuilder sb = new StringBuilder();
		int operIdx = 0;

		for (int i = 0; i < cur.length(); i++) {
			char ch = cur.charAt(i);

			if (ch == '(') {
				sb.append(ch).append(cur.charAt(i + 1)).append(operList.get(operIdx++)).append(cur.charAt(i + 2));
				i += 2;
			} else {
				sb.append(ch);
				if (i < cur.length() - 1 && operIdx < operList.size() && cur.charAt(i + 1) != ')') {
					sb.append(operList.get(operIdx++));
				}
			}
		}
		return sb.toString();
	}

	// 만들어진 식을 스택에 담아 계산
	public static int calculateFormula(String str){
		Stack<Integer> stack = new Stack<>();
		int num1, num2, temp;
		int res;
		char oper;
		for (int i = 0; i < str.length(); i++){
			char ch = str.charAt(i);
			if (Character.isDigit(ch))
				stack.add(ch - '0'); // 숫자라면 바로 스택에 삽입
			else if (ch == '('){ // 괄호가 들어오면 괄호 안의 값 부터 계산
				num1 = str.charAt(i + 1) - '0';
				oper = str.charAt(i + 2);
				num2 = str.charAt(i + 3) - '0';
				res = calculate(num1, oper, num2);
				stack.add(res); // 연산이 끝나면 스택에 다시 삽입
				i += 4; // 연산해준 만큼 인덱스 이동
			}
			else
				stack.add((int)ch); // 연산자도 스택에 삽입

			// 스택 사이즈가 3이라면, 즉 숫자, 연산자, 숫자가 스택에 존재한다는 뜻이기에
			// 계산한 결과값을 다시 스택에 삽입
			if (stack.size() == 3){ 
				num1 = stack.pop();
				temp = stack.pop();
				oper =  (char)temp;
				num2 = stack.pop();
				res = calculate(num2, oper, num1);
				stack.add (res);
			}
		}
		// 최종적으로 스택에 남은 값을 반환
		return stack.pop();
	}

	public static int calculate(int num1, char oper, int num2){
		int res = 0;
		switch(oper){
			case '+' :
				res = num1 + num2;
				break ;
			case '-' : 
				res = num1 - num2;
				break ;
			case '*' : 
				res = num1 * num2;
				break ;
		}
		return res;
	}
}