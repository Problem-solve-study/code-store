
// 35596 KB, 132 ms
/*
 * dp 보다 BFS가 더 빠르지 않을까?
 * dp는 N만큼 돌아야 하지만, BFS 탐색은 중복 제거만 하면, 조기 종료 가능성이 있으므로.
 */
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Arrays;

public class Main {
    static int N;
    static final int MAX = 999_999_999;

    public static void main(String[] args) throws IOException {
        N = readInt();
        int[] operationCnt = new int[3 * N + 1];
        int[] parent = new int[3 * N + 1];
        parent[N] = N;
        Arrays.fill(operationCnt, MAX);
        operationCnt[N] = 0;
        for (int i = N; i > 0; i--) {
            if (operationCnt[3 * i] + 1 < operationCnt[i]) {
                operationCnt[i] = operationCnt[3 * i] + 1;
                parent[i] = 3 * i;
            }
            if (operationCnt[i << 1] + 1 < operationCnt[i]) {
                operationCnt[i] = operationCnt[i << 1] + 1;
                parent[i] = i << 1;
            }
            if (operationCnt[i + 1] + 1 < operationCnt[i]) {
                operationCnt[i] = operationCnt[i + 1] + 1;
                parent[i] = i + 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(operationCnt[1]).append('\n');
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int x = 1;
        stack.push(x);
        while (x != parent[x]) {
            x = parent[x];
            stack.push(x);
        }
        while (!stack.isEmpty())
            sb.append(stack.pop()).append(" ");
        System.out.println(sb.toString());
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}