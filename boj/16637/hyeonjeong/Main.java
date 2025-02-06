// 11488KB, 68mss

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static int max = Integer.MIN_VALUE;

    static int opSize;
    static boolean[] isWraped;
    static int[] expression;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        isWraped = new boolean[n];
        expression = new int[n];

        char[] tokens = br.readLine().toCharArray();
        for (int i = 0; i < n; i++) {
            if (tokens[i] >= '0' && tokens[i] <= '9')
                expression[i] = tokens[i] - '0';
            else
                expression[i] = tokens[i];
        }

        dfs(1, isWraped);

        System.out.println(max);
    }

    // 연산자마다 괄호가 붙거나 안 붙거나
    static void dfs(int idx, boolean[] isWraped) {
        if (idx >= n) {
            calulate();
            return;
        }

        // idx번째 연산자에 괄호 X
        dfs(idx + 2, isWraped);

        // idx번째 연산자에 괄호 O
        if (idx == 1 || isWraped[idx - 2] == false) {
            isWraped[idx] = true;
            dfs(idx + 2, isWraped);
            isWraped[idx] = false;
        }
    }

    static void calulate() {
        int[] unwrapExpression = new int[n];
        int top = -1;

        // 괄호 먼저 계산
        int i = 0;
        while (i < n) {
            if (expression[i] >= '0' && expression[i] <= '9' || isWraped[i] == false) {
                unwrapExpression[++top] = expression[i++];
                continue;
            }

            int prev = unwrapExpression[top--];
            unwrapExpression[++top] = operation(expression[i++], prev, expression[i++]);
        }

        // 괄호 지운 수식 계산
        int result = unwrapExpression[0];
        for (i = 1; i < top; i += 2) {
            result = operation(unwrapExpression[i], result, unwrapExpression[i + 1]);
        }

        if (result > max)
            max = result;
    }

    static int operation(int op, int a, int b) {
        if (op == '-')
            return a - b;
        if (op == '+')
            return a + b;
        if (op == '*')
            return a * b;
        return -1;
    }
}
