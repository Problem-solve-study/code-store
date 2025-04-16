
// 21556KB	256ms
import java.io.*;
import java.util.*;

/**
 * 문제에서 시키는데로 구현했어요
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        stack.add(0);
        int cursor = 1;
        boolean flag = false;
        for (int t = 0; t < T; t++) {
            int num = nextInt();
            while (num > stack.peek()) {
                stack.add(cursor++);
                sb.append("+\n");
            }
            while (num < stack.peek()) {
                stack.pop();
                sb.append("-\n");
            }
            if (num != stack.peek()) {
                flag = true;
            } else {
                stack.pop();
                sb.append("-\n");
            }
        }
        if (flag)
            System.out.print("NO");
        else
            System.out.print(sb);
    }

    // fast i/o
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
