import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static char[] formula;
    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        formula = new char[n];
        String line = br.readLine();
        for (int i=0; i<n; i++) {
            formula[i] = line.charAt(i);
        }
        selectBrackets(2, -1, new HashSet<>());
        
        System.out.print(answer);
    }


    static void selectBrackets(int idx, int prev, HashSet<Integer> brackets) {
        char[] formulaWithBracket = convertPost(brackets);
        answer = Math.max(answer, calculate(formulaWithBracket));
        
        if (idx >= n-2) return;

        brackets.add(idx);
        selectBrackets(idx+4, idx, brackets);
        brackets.remove(idx);
        
        selectBrackets(idx+2, prev, brackets);
    }


    static char[] convertPost(HashSet<Integer> brackets) {
        char[] result = new char[n];
        Stack<Character> stack = new Stack<>();
        int idx = 0;
        result[idx++] = formula[0];
        for (int i=1; i<n; i++) {
            if (formula[i]-'0' >= 0) {
                result[idx++] = formula[i];
                if (brackets.contains(i)) continue;
                while (!stack.isEmpty()) {
                    result[idx++] = stack.pop();
                }
            }
            else {
                stack.add(formula[i]);
            }

        }
        return result;
    }
    

    static int calculate(char[] data) {
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i<n; i++) {
            if (data[i]-'0' >= 0) {
                stack.add(data[i]-'0');
            }
            else {
                int a = stack.pop();
                int b = stack.pop();
                if (data[i] == '+') stack.add(a+b);
                if (data[i] == '-') stack.add(b-a);
                if (data[i] == '*') stack.add(a*b);
            }
        }
        return stack.pop();
    }
}