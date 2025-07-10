import java.io.*;
import java.util.*;

/*
32952KB, 360ms

스택은 어차피 LIFO 구조이므로 원래 입력된 x0이 그대로 나오는 구조임
따라서 스택의 원소는 변하지 않으므로 큐의 원소만 관리하면 됨
값들을 입력받고 적절히 큐의 원소를 관리하며 나오는 원소를 출력
만일 모두 스택인 경우면 입력된 순서 그대로 나오게 되므로
이 부분만 따로 관리
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        ArrayDeque<Integer> q = new ArrayDeque<>();
        boolean[] isQueue = new boolean[N];
        for (int i = 0; i < N; i++) {
            isQueue[i] = nextInt() == 0;
        }
        for (int i = 0; i < N; i++) {
            int v = nextInt();
            if (isQueue[i]) q.addLast(v);
        }

        int M = nextInt();
        StringBuilder sb = new StringBuilder();
        //모두 스택이면 그냥 입력된 순서 그대로 출력
        if (q.isEmpty()) {
            for (int i = 0; i < M; i++) {
                sb.append(nextInt()).append(' ');
            }
        } else {
            for (int i = 0; i < M; i++) {
                sb.append(q.removeLast()).append(' ');
                q.addFirst(nextInt());
            }
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}