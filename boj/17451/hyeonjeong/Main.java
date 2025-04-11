// 58444KB 300ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 뒤에서부터 탐색하면서 나보다 내 앞의 친구가 작으면 나보다 크거나 같게 올리기
// a * b >= c -> b >= a/c
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        long[] stack = new long[n];
        int top = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            stack[++top] = Long.parseLong(st.nextToken());
        }

        long prev = stack[top--];
        while (top >= 0) {
            if (stack[top] >= prev) {
                prev = stack[top--];
                continue;
            }

            int exp = searchLowerBound(2, (int) (prev / stack[top]) + 2, stack[top], prev);
            System.out.printf("%d * %d = %d >= %d\n", stack[top], exp, stack[top] * exp, prev);
            
            stack[top] *= exp;
            prev = stack[top--];
        }

        System.out.println(prev);
    }

    static int searchLowerBound(int left, int right, long origin, long prev) {
        if (left >= right) {
            return left;
        }

        int mid = (left + right) / 2;
        if (origin * mid < prev) {
            return searchLowerBound(mid + 1, right, origin, prev);
        }
        
        return searchLowerBound(left, mid, origin, prev);
    }

    static void displayTestcase() {
        int n = 10;

        StringBuilder sb = new StringBuilder();
        sb.append(n).append('\n');
        for (int i = 0; i < n; i++) {
            sb.append((int) (Math.random() * 1_000) + 1).append(' ');
        }

        System.out.println(sb);
    }
}
