// 14064 KB, 112 ms
/*
 * 괄호 짝 짓기. 스택 이용
 * 열리는 괄호가 들어오면 푸쉬, 닫히는 괄호가 들어오면 pop 해서 비교.
 * peek의 괄호와 짝이 안맞으면 잘못된 문자열.
 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        boolean flag = true, isEnd = true;
        int c;
        while ((c = System.in.read()) != -1) {
            switch (c) {
                case '[':
                case '(':
                    isEnd = false;
                    stack.push(c);
                    break;
                case ')':
                    isEnd = false;
                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    if (stack.pop() != '(') {
                        flag = false;
                        break;
                    }
                    break;
                case ']':
                    isEnd = false;
                    if (stack.isEmpty()) {
                        flag = false;
                        break;
                    }
                    if (stack.pop() != '[') {
                        flag = false;
                        break;
                    }
                    break;
                case '.':
                    if (isEnd)
                        break;
                    if (!stack.isEmpty())
                        flag = false;
                    sb.append(flag ? "yes" : "no").append('\n');
                    stack = new ArrayDeque<>();
                    flag = true;
                    isEnd = true;
                    System.in.read();
                    // System.in.read(); // 윈도우는 CRLF
                    break;
                default:
                    isEnd = false;
            }
        }
        System.out.println(sb.toString());
    }
}