//제출번호: 92143417
//메모리:   80428 KB
//실행시간: 1100 ms
import java.io.*;
import java.util.*;

//우선순위 큐 문제, 나머지 프로세스의 우선순위를 모두 1 상승하는 방법을
//어떻게 구현할 것인지만 생각하면 쉽게 풀 수 있음
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> {
        if (i1[2] == i2[2]) { //우선 순위 값이 같다면
            return Integer.compare(i1[0], i2[0]); //id가 작은 순으로
        }

        return -Integer.compare(i1[2], i2[2]); //우선 순위 값이 큰 순으로
    });

    public static void main(String[] args) throws IOException {
        int t = nextInt();
        int n = nextInt();

        for (int i = 0; i < n; i++) {
            //모든 프로세스를 우선순위 큐에 담음
            pq.add(new int[]{nextInt(), nextInt(), nextInt()});
        }

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int[] item = pq.poll();

            sb.append(item[0]).append('\n');
            //현재 프로세스의 우선 순위 값을 1 감소시킴
            // --> 나머지 우선 순위 값이 모두 1 증가한 효과
            item[2]--;

            //프로세스의 처리 시간을 1 감소함
            item[1]--;

            //만약 프로세스가 모두 처리 됐다면 큐에 넣지 않음
            if (item[1] > 0) {
                pq.add(item);
            }
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}