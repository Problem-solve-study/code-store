import java.io.*;
import java.util.*;

/*
16912KB, 124ms

그냥 문제에 주어진대로 시뮬레이션 돌리기
태그보니까 DP로도 풀 수 있는 것 같음
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        Queue<Integer> q = new ArrayDeque<>();
        Queue<Integer> nq = new ArrayDeque<>();
        q.add(1);

        for (int time = 2; time <= N; time++) {
            for (int bug : q) {
                if (!((bug % 2 != 0 && time - bug >= 3) || (bug % 2 == 0 && time - bug >= 4))) {
                    nq.add(bug);
                }
                nq.add(time);
            }
            q = new ArrayDeque<>(nq);
            nq.clear();
        }
        System.out.print(q.size());
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
