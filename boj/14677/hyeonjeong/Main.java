// 20432KB 72ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/** 
 * DFS + 메모이제이션
 * 처음엔 경우의 수가 많이 없을 것 같아서 메모이제이션을 안 했는데,, 18퍼 시초 나네요
 * 외판원이랑 비슷한 느낌으로 메모했습니다.
 */
class Main {
    static final char[] sequence = {'B', 'L', 'D'};
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
    static char[] tablets;
    static int[][] visited;


    public static void main(String[] args) throws IOException {
        n = nextInt();
        tablets = nextLine().toCharArray();

        visited = new int[3 * n][3 * n];
        int result = dfs(0, 3 * n - 1, 0);

        System.out.println(result);
    }

    static int dfs(int left, int right, int depth) {
        if (left > right) {
            return depth;
        }

        if (visited[left][right] > 0) {
            return visited[left][right];
        }

        int max = depth;

        char next = sequence[depth % 3];
        if (tablets[left] == next) {
            int result = dfs(left + 1, right, depth + 1);
            if (result > max) {
                max = result;
            }
        }
        if (tablets[right] == next) {
            int result = dfs(left, right - 1, depth + 1);
            if (result > max) {
                max = result;
            }
        }

        visited[left][right] = max;

        return visited[left][right];
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextLine() throws IOException {
        st.nextToken();
        return st.sval;
    }
}
