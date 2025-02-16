import java.io.*;
import java.util.stream.IntStream;

/*
20004KB, 352ms

기초 그래프 탐색 문제.
예전에 DFS로 한 번 풀어봤었길래 이번엔 유니온 파인드로 해결
String 이슈로 메모리가 엄청 많이 나오는 것 같길래 StringTokenizer를 StreamTokenizer로 교체했더니
메모리와 속도 모두 빨라졌다.(120304KB, 528ms -> 20004KB, 352ms) 참고하면 좋을듯.
 */

public class Main {
    static int[] disjointSet;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StreamTokenizer st = new StreamTokenizer(br);

        int n = nextInt(st);
        int m = nextInt(st);
        //분리 집합 생성 후 초기화
        disjointSet = new int[n + 1];
        IntStream.rangeClosed(0, n).forEach(e -> disjointSet[e] = e);

        //간선을 입력받고 유니온
        for (int i = 0; i < m; i++) {
            int v1 = nextInt(st);
            int v2 = nextInt(st);
            union(v1, v2);
        }

        //각 집합의 대표만을 카운팅
        int res = 0;
        for (int i = 1; i <= n; i++) {
            if (find(i) == i) {
                res++;
            }
        }

        bw.write(String.valueOf(res));
        bw.flush();
    }

    static int nextInt(StreamTokenizer st) throws Exception {
        st.nextToken();
        return (int) st.nval;
    }

    static int find(int n) {
        if (n == disjointSet[n]) {
            return n;
        }

        return disjointSet[n] = find(disjointSet[n]);
    }

    static void union(int n1, int n2) {
        disjointSet[find(n1)] = find(n2);
    }
}
