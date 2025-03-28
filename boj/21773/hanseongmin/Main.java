import java.io.*;
import java.util.*;

/*
93060KB, 1516ms

힙 문제. 나머지 프로세스들의 우선 순위를 모두 올려야하는 조건이 있는데
나를 제외한 모든 프로세스의 우선 순위를 올리는 것이므로 그냥 나를 내리면 된다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        PriorityQueue<int[]> h = new PriorityQueue<>(Comparator.comparingInt((int[] arr) -> -arr[2]).thenComparingInt(arr -> arr[0]));
        int T = nextInt();
        int n = nextInt();
        for (int i = 0; i < n; i++) {
            h.add(new int[] {nextInt(), nextInt(), nextInt()});
        }

        StringBuilder sb = new StringBuilder();
        while (T --> 0 && !h.isEmpty()) {
            int[] cur = h.remove();
            sb.append(cur[0]).append('\n');
            //실행 시간과 우선순위를 동시에 감소
            cur[1]--; cur[2]--;
            //실행 시간이 남지 않으면 힙에 넣으면 안됨
            if (cur[1] != 0) {
                h.add(cur);
            }
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
