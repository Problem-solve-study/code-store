import java.util.*;
import java.io.*;


public class Main {

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        HashMap<Character, Integer> map = new HashMap<>();
        map.put('H', 1);
        map.put('C', 12);
        map.put('O', 16);
        map.put('(', 0);


        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < str.length()) {
            char c = str.charAt(i);
            if (c == ')') {
                int multiplier = 1;
                // 다음 문자가 숫자인 경우
                if (i < str.length()-1 && isNumeric(str.charAt(i+1))) {
                    multiplier = str.charAt(i+1)-'0';
                    i++;
                }
                int top, temp = 0;
                do {
                    top = stack.pop();
                    temp += top;
                } while (top != 0);

                if (stack.size() > 0) {
                    stack.push(temp * multiplier);
                }
                else {
                    answer += temp * multiplier;
                }                
            }

            else if (isNumeric(c)) {
                stack.push(stack.pop()*(c-'0'));
            }

            // H,C,O,(
            else { 
                stack.push(map.get(c));
            }

            i++;
        }
        
        while (!stack.isEmpty()) {
            answer += stack.pop();
        }

        System.out.print(answer);
	}


    static boolean isNumeric(char c) {
        int num = c - '0';
        return num > 0 && num < 10;
    }   
}