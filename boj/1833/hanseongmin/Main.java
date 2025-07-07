import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
22308KB, 356ms

이미 연결된 간선이 주어져있는 인접 행렬에서의 MST
MST는 크루스칼 사용
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, C, M;
    static int[] ds;
    static int[][] mat;
    static PriorityQueue<int[]> h = new PriorityQueue<>(Comparator.comparingInt(arr -> arr[2]));

    public static void main(String[] args) throws Exception {
        N = nextInt();
        //분리 집합 초기화
        ds = new int[N];
        IntStream.range(0, N).forEach(n -> ds[n] = n);
        mat = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mat[i][j] = nextInt();
                if (mat[i][j] < 0) {
                    //인접 행렬이므로 i < j일 때만 union
                    if (i < j) {
                        union(i, j);
                        C -= mat[i][j];
                    }
                } else if (mat[i][j] > 0) {
                    //0보다 큰 경우면 최소 비용 간선을 뽑아오기 위해 힙에 삽입
                    h.add(new int[] {i, j, mat[i][j]});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!h.isEmpty()) {
            //힙에서 뽑아보고
            int[] cur = h.remove();
            //유니온에 성공하면
            if (union(cur[0], cur[1])) {
                //해당 정보를 저장
                C += cur[2];
                M++;
                sb.append(cur[0] + 1).append(' ').append(cur[1] + 1).append('\n');
            }
        }

        System.out.println(C + " " + M);
        System.out.print(sb);
    }

    static int find(int n) {
        if (ds[n] == n) return n;
        return ds[n] = find(ds[n]);
    }

    static boolean union(int n1, int n2) {
        int p1 = find(n1);
        int p2 = find(n2);

        if (p1 != p2) {
            ds[p1] = p2;
            return true;
        }

        return false;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
