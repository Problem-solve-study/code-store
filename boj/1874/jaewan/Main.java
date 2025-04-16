// 18660 KB, 168 ms
/*
 * 
 */

import java.io.IOException;
import java.util.ArrayDeque;

public class Main {

    public static void main(String[] args) throws IOException {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int n = readInt(), next = readInt(), left = n;
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            q.push(i);
            sb.append('+').append('\n');
            while (!q.isEmpty() && q.peek() == next) {
                q.pop();
                sb.append('-').append('\n');
                if (--left > 0)
                    next = readInt();
            }
        }
        if (left > 0)
            System.out.print("NO");
        else
            System.out.print(sb);
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