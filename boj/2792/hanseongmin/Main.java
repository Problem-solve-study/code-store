import java.io.*;

/*
121884KB, 1740ms

질투심으로 매개변수 탐색 돌리기.
결정함수는 시뮬레이션으로 구현해도 AC 처리해주는 듯
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    static int N, M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        N = nextInt(); M = nextInt();
        arr = new int[M];
        for (int i = 0; i < M; i++) {
            arr[i] = nextInt();
        }

        long s = 0;
        long e = Long.MAX_VALUE;
        long mid;
        while (s <= e) {
            mid = s + (e - s) / 2;
            if (check(mid)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.print(s);
    }

    //현재 주어진 질투심으로 규칙대로 배치 가능한지 시뮬레이션
    static boolean check(long n) {
        int marbleIdx = 0;
        long marbleCnt = arr[marbleIdx];
        for (int i = 0; i < N; i++) {
            marbleCnt -= n;
            if (marbleCnt <= 0) {
                if (marbleIdx + 1 == arr.length) break;
                marbleCnt = arr[++marbleIdx];
            }
        }

        return marbleCnt <= 0;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
