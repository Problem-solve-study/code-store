import java.io.*;
import java.util.*;

/*
28892KB, 640ms

A - B의 합의 최댓값을 구하는 것이므로 A는 최댓값, B는 최솟값 선택을 반복
이때 A - B가 0보다 작아지면 거래를 진행하지 않으므로 이를 0으로 간주
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int M = nextInt();
        //최대 A를 뽑기 위한 맥스힙
        PriorityQueue<Integer> aH = new PriorityQueue<>(Collections.reverseOrder());
        //최소 B를 뽑기 위한 민힙
        PriorityQueue<Integer> bH = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            aH.add(nextInt());
        }
        for (int i = 0; i < M; i++) {
            bH.add(nextInt());
        }

        //long을 써야함은 친절하게 문제에서 알려준다
        long res = 0;
        while (!bH.isEmpty()) {
            int b = bH.remove();
            if (!aH.isEmpty()) {
                int a = aH.remove();
                //고객 만족도를 계산
                res += Math.max(a - b, 0);
            }
        }

        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
