// 18740KB 192ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/** 
 * 스택의 TOP과 동일 - POP
 * 스택의 TOP보다 큼 - 해당 숫자까지 스택에 PUSH
 * 스택의 TOP보다 작음 - 오류
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int n = nextInt();
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = nextInt();
        }

        int[] stack = new int[n];
        int top = -1;
        int number = 1;
        int i = 0;
        while (i < n) {
            if (top == -1 || stack[top] < numbers[i]) {
                do {
                    stack[++top] = number++;
                    sb.append("+\n");
                } while (top < n && stack[top] < numbers[i]);
                continue;
            }

            if (stack[top] == numbers[i]) {
                top--;
                i++;
                sb.append("-\n");
                continue;
            }

            if (stack[top] > numbers[i]) {
                System.out.println("NO");
                return;
            }
        }

        System.out.println(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
