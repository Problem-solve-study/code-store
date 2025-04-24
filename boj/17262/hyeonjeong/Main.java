// 58956KB 300ms

import java.io.*;

/*
 * 가장 늦게 오는 사람 - 가장 먼저 나가는 사람
 * 음수면 0 출력
 */
class Main {
    static final StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        int[][] students = new int[n][2];

        int firstEnd = Integer.MAX_VALUE;
        int lastStart = 0;
        for (int i = 0; i < n; i++) {
            students[i][0] = nextInt();
            students[i][1] = nextInt();

            if (students[i][0] > lastStart) {
                lastStart = students[i][0];
            }

            if (students[i][1] < firstEnd) {
                firstEnd = students[i][1];
            }
        }

        int time = lastStart - firstEnd;
        if (time < 0) {
            System.out.print(0);
            return;
        }

        System.out.println(time);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
