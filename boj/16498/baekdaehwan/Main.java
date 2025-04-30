/**
 * 14068KB	92ms
 * 
 * [사고 흐름]
 * 정렬인것 같았습니다.
 * 먼저 단순하게 생각해보았습니다. 만약 N개의 카드에서 a, b, c를 뽑는 것이라면 연속된 세 수를 고르는 것이 정답이 될거라고 생각했습니다.
 * 
 * 
 * 실제 문제의 조건과 동일한 경우에는 다음과 같습니다.
 * A, B, C의 카드를 모두 합쳐서 정렬한 후, 세 카드를 구하는 경우, a, b, c는 다음과 같습니다.
 * a = i
 * b = i 보다 큰 인덱스 중 가장 처음으로 만나는 다른 사람의 카드
 * c = i 보다 큰 인덱스 중 가장 처음으로 만나는 b와 다른 사람의 카드
 * 즉, 한 카드(a)를 잡고, 다른 두 플레이어에서 a보다 크면서 가장 차이가 작은 카드를 고르면 a가 가장 작은 수일때의 대한 최소 차이값을 구할 수 있다고 생각했습니다.
 * 
 * 
 * a를 A의 카드 중 하나라고 할 때, 
 * B와 C에서 a보다 크면서 가장 차이가 작은 카드를 고르는 것은 lowerBound로 logN으로 구할 수 있으니, 이를 바탕으로 시간복잡도를 계산해 보았습니다.
 * 총 시간복잡도는 (모든 카드 수) * 2log(한 플레이어의 카드 수)이므로, 최악의 경우 3000 * 2log(1000)이 됩니다.
 * 그렇다면 1초 안에 무조건 통과하기 때문에 위 방법으로 구현하였습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] A;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        K = ni();
        A = new int[3][];
        A[0] = new int[N];
        A[1] = new int[M];
        A[2] = new int[K];
        for (int i=0; i<N; ++i) A[0][i] = ni();
        for (int i=0; i<M; ++i) A[1][i] = ni();
        for (int i=0; i<K; ++i) A[2][i] = ni();
        for (int i=0; i<3; ++i) Arrays.sort(A[i]);

        int res = Integer.MAX_VALUE;
        for (int a=0; a<3; ++a) {
            int b = (a+1)%3;
            int c = (a+2)%3;
            for (int av: A[a]) res = Math.min(res, Math.max(lb(A[b], av), lb(A[c], av)) - av);
        }
        System.out.println(res);
    }

    static int lb(int[] arr, int t) {
        int l = 0;
        int r = arr.length;
        while (l<r) {
            int m = (l+r)>>1;
            if (arr[m] < t) l = m+1;
            else r = m;
        }
        if (r<arr.length) return arr[r];
        else return Integer.MAX_VALUE;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}