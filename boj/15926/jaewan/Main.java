// 12520 KB, 80 ms
/*
 * 여는 괄호는 스택에 그냥 push
 * 닫는 괄호일 때
 * 1. top이 여는 괄호면 pop 하고 2를 push
 * 1-1. top이 숫자면 더해서 push
 * 2. top이 닫는 괄호면 그냥 push
 * 3. top이 숫자면, top 이전이 여는 괄호면 pop 두번하고 숫자 +2 push
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int n = readInt(), top = 1, max = 0;
        int[] stack = new int[n + 1];
        stack[0] = -999;
        for (int i = 0; i < n; i++) {
            int t = read();
            if (t == '(') {
                stack[top++] = 0;
            } else if (top == 1) {
                stack[top++] = -1;
            } else if (stack[top - 1] == 0) {
                if (stack[top - 2] > 0)
                    stack[top-- - 2] += 2;
                else
                    stack[top - 1] = 2;
            } else if (stack[top - 1] == -1) {
                stack[top++] = -1;
            } else if (stack[top - 1] > 0) {
                if (stack[top - 2] == 0) {
                    stack[top - 2] = stack[top-- - 1] + 2;
                    if (stack[top - 2] > 0)
                        stack[top - 2] += stack[top-- - 1];
                } else
                    stack[top++] = -1;
            }
            max = Math.max(max, stack[top - 1]);
        }
        System.out.println(max);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}