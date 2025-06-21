import java.io.*;
import java.util.stream.IntStream;

/*
20392KB, 320ms

기초적인 분리집합 문제인듯
건물들을 연결해주고 강의를 들으면서 다음 건물이 다른 집합에 속해있다면 결과값 + 1 해주는 것을
모든 강의를 들을 때까지 반복하기
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[] disjointSet;

    public static void main(String[] args) throws Exception {
        int N = nextInt(); int M = nextInt();
        disjointSet = new int[N + 1];
        IntStream.rangeClosed(0, N).forEach(n -> disjointSet[n] = n);
        for (int i = 0; i < M; i++) {
            int a = nextInt(); int b = nextInt();
            union(a, b);
        }

        int ans = 0;
        int p = 0;
        for (int i = 0; i < N; i++) {
            int cur = nextInt();
            int pCur = find(cur);

            if (i == 0) {
                p = pCur;
                continue;
            }

            if (p != pCur) {
                ans++;
                p = pCur;
            }
        }
        System.out.print(ans);
    }

    static int find(int n) {
        if (disjointSet[n] == n) return n;
        return disjointSet[n] = find(disjointSet[n]);
    }

    static void union(int a, int b) {
        disjointSet[find(a)] = disjointSet[find(b)];
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
