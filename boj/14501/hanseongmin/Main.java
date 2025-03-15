import java.io.*;

/*
11524KB, 68ms

전에 퇴사2가 문제로 나온적이 있었는데 해당 문제의 쉬운 버전
N이 매우 작아 브루트포스로도 뚫린다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N;
    static int[] T;
    static int[] P;
    static int res = 0;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        T = new int[N + 1];
        P = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            T[i] = nextInt();
            P[i] = nextInt();
        }

        dfs(1, 0);
        System.out.println(res);
    }

    static void dfs(int day, int sum) {
        if (day > N + 1) return;
        res = Math.max(res, sum);
        for (int i = day; i <= N; i++) {
            dfs(i + T[i], sum + P[i]);
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
