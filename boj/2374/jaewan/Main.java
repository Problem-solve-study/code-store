// 11356 KB, 64 ms
/*
 * 그리디로 접근, 
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), max = 0, top = 0;
        int[] stack = new int[N];
        long res = 0;
        for (int i = 0; i < N; i++) {
            int t = readInt();
            max = Math.max(max, t);
            if (top == 0) {
                stack[top++] = t;
                continue;
            }
            if (stack[top] == t)
                continue;
            else if (stack[top] < t) {
                res += (t - stack[--top]);
                stack[top++] = t;
            } else
                stack[top - 1] = t;
        }

        res += (max - stack[top - 1]);

        System.out.println(res);
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