// 	11520KB	64ms

import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static char[] expression;
    static int[] stack = new int[3];
    static int top = -1;
    static int max = Integer.MIN_VALUE;
    static int numOfOperator;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        expression = br.readLine().toCharArray();
        numOfOperator = N/2;

        // 후위 표기식 변환
        // 모든 연산자의 우선순위가 같으므로 연산자와 오른쪽 피연산자를 스왑하면 후위 표기식이 만들어짐
        for (int i=1; i<N; i+=2) swap(i, i+1);
        solve(2, false);

        System.out.println(max);
    }

    /**
     * @param k : k번째 연산자 -> 0*1-2의 경우 *(k=1), -(k=2) 
     * @param isLastSelected : k-1번째 연산자에 괄호를 쳤는지 여부
     */
    static void solve(int k, boolean isLastSelected) {
        if (k>numOfOperator) {
            max = Math.max(max, calc());
            return;
        }
        if (isLastSelected) {
            solve(k+1, false);
        }
        else {
            // operatorIdx : k번째 연산자의 위치
            // 후위 표기식이므로, 아래와 같은 규칙으로 스왑하면 먼저 계산됨
            // ex) 0*1-2 => 0*(1-2)의 경우
            // 후위 표기    : 01*2-
            // k=2로 스왑   : 012-*
            // - 가 먼저 계산되는 것을 확인할 수 있음
            int operatorIdx = 2*k;
            swap(operatorIdx-2, operatorIdx-1);
            swap(operatorIdx-1, operatorIdx);
            solve(k+1, true);

            swap(operatorIdx-1, operatorIdx);
            swap(operatorIdx-2, operatorIdx-1);
            solve(k+1, false);
        }
    }
    
    static int calc() {
        for (char c: expression) {
            if ('0'<=c && c<='9') push(c-'0');
            else {
                if (c=='+') push(pop()+pop());
                else if (c=='-') push(-pop()+pop());
                else push(pop()*pop());
            }
        }
        return pop();
    }

    static void swap(int i, int j) {
        char tmp = expression[i];
        expression[i] = expression[j];
        expression[j] = tmp;
    }

    static void push(int d) {
        stack[++top] = d;
    }
    static int pop() {
        return stack[top--];
    }
}
