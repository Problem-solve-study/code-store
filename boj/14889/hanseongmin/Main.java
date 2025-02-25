import java.io.*;

/*
15016KB, 344ms

n개에서 n - 2개만큼 뽑고 점수 계산하기
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int[][] map;
    static boolean[] isA;
    static int res, n;

    public static void main(String[] args) throws Exception {
        res = Integer.MAX_VALUE;
        n = nextInt();
        isA = new boolean[n];
        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                map[i][j] = nextInt();
            }
        }

        dfs(0, 0);
        bw.write(String.valueOf(res));
        bw.flush();
    }

    static void dfs(int cnt, int idx) {
        if (cnt == n / 2) {
            int a = 0;
            int b = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (isA[i] && isA[j]) {
                        a += map[i][j];
                    }
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!isA[i] && !isA[j]) {
                        b += map[i][j];
                    }
                }
            }

            res = Math.min(res, Math.abs(a - b));
            return;
        }

        for (int i = idx; i < n; i++) {
            isA[i] = true;
            dfs(cnt + 1, i + 1);
            isA[i] = false;
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}