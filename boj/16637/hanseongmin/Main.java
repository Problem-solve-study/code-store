// 15284KB, 124ms

import java.io.*;
import java.util.ArrayDeque;

public class Main {
    static int res = Integer.MIN_VALUE;
    static String f;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        f = br.readLine();

        select(n / 3 + 1, 0, new boolean[n + 1]);
        bw.write(String.valueOf(res));
        bw.flush();
    }

    public static void select(int limit, int cnt, boolean[] bracket) {
        //연산자를 선택하여 괄호 추가
        res = Math.max(res, getValue(bracket));
        if (limit == cnt) {
            return;
        }

        for (int i = 1; i < f.length(); i += 2) {
            //인접한 연산자는 선택하면 안됨
            if (bracket[i]) continue;
            if (i - 2 >= 0 && bracket[i - 2]) continue;
            if (i + 2 < f.length() && bracket[i + 2]) continue;

            bracket[i] = true;
            select(limit, cnt + 1, bracket);
            bracket[i] = false;
        }
    }

    public static int getValue(boolean[] bracket) {
        //수식을 계산
        //괄호가 씌여진 연산자를 먼저 계산하여 새로운 식을 생성
        ArrayDeque<Integer> operand = new ArrayDeque<>();
        ArrayDeque<Character> operator = new ArrayDeque<>();
        for (int i = 0; i < f.length(); i++) {
            if (Character.isDigit(f.charAt(i))) {
                operand.addLast(f.charAt(i) - '0');
            } else {
                if (bracket[i] && i != 1) {
                    int o1 = operand.removeLast();
                    int o2 = f.charAt(i + 1) - '0';
                    calc(operand, o1, o2, f.charAt(i++), false);
                } else {
                    operator.add(f.charAt(i));
                }
            }
        }

        while (operand.size() > 1) {
            calc(operand, operand.removeFirst(), operand.removeFirst(), operator.removeFirst(), true);
        }

        return operand.peek();
    }

    public static void calc(ArrayDeque<Integer> operand, int a, int b, char o, boolean isFirst) {
        switch (o) {
            case '+':
                if (isFirst) operand.addFirst(a + b);
                else operand.addLast(a + b);
                break;
            case '-':
                if (isFirst) operand.addFirst(a - b);
                else operand.addLast(a - b);
                break;
            case '*':
                if (isFirst) operand.addFirst(a * b);
                else operand.addLast(a * b);
                break;
        }
    }
}