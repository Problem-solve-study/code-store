/**
 * 	145060KB	680ms
 * 어렵긴 한데 플1 난이도는 아닌거 같네요. 플3 정도?
 * 조건 생각 못한게 있어서 10틀했습니다 ^^7
 * 결국 처음 코드에 조건 추가한게 맞았다는..
 * 
 * [사고흐름]
 * DP? 세그트리? 좀 헷갈렸습니다.
 * 세그트리의 노드를 아래와 같이 생각하니 풀릴 것 같아서 시도했습니다.
 * Prefix(L) - 현재 범위의 첫 번째 원소부터 시작하는 최댓값
 * Suffix(R) - 현재 범위의 마지막 원소부터 시작하는 최댓값
 * All(A)    - 현재 범위를 모두 포함하는 값
 * Max(M)    - 현재 범위에서 만들 수 있는 최댓값
 * 
 * 병합할 때에는 위 정보를 활용할 수 있다고 생각했습니다.
 * 
 * [알고리즘 설명]
 * 세그트리의 쿼리에 대한 부분은 자명하니 생략하고, 병합관련 함수 merge()에 대해서만 설명하겠습니다.
 * Current(C), LeftChild(LC), RightChild(RC) 로 표현하겠습니다
 * 
 * C.A = LC.A + RC.A + V 입니다.
 * V가 붙는 이유는 V는 j-i이기 때문에 `원소 개수 - 1`압니다. 각 원소 사이에 V라는 값이 들어간다고 생각하면 편합니다.
 * R, L, A, M 이 이것을 따르고 있다고 가정할 때, 병합시 V를 추가하면 각 원소 사이에 모두 V가 들어가 있다고 생각할 수 있습니다.
 * 
 * C.L = max( LC.L , LC.A+RC.L+V ) 입니다.
 * LC.A 와 RC.L 는 연속적인 구간이므로, V를 추가해서 이을 수 있습니다.
 * LC.L 와 비교했을때 더 큰 값을 넣으면 됩니다.
 * 
 * C.R 도 동일합니다.
 * 
 * C.M = max( C.L , C.R , C.M , LC.M , RC.M , LC.R+RC.L+V ) 입니다.
 * 저는 LC.R+RC.L+V 이걸 빼먹어서 10틀 했습니다. 기존에 왼쪽자식의 Suffix와 오른쪽 자식의 Prefix를 합친 결과는 없으니 이 값을 추가해야합니다.
 */

import java.io.*;

public class Main {
    static final int L=0, R=1, A=2, M=3, INF=10000;

    static int N, Q, U, V, S;
    static int[][] seg;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        Q = ni();
        U = ni();
        V = ni();
        seg = new int[N<<2][4];
        S = 1;
        while (S<N) S<<=1;
        for (int i=0; i<N; ++i) {
            int c = ni() * U;
            for (int j=0; j<4; ++j) seg[S+i][j] = c;
        }
        StringBuilder res = new StringBuilder();
        for (int c=S-1; c>0; --c) merge(seg[c], seg[c<<1], seg[(c<<1)+1]);
        for (int i=0; i<Q; ++i) {
            int c = ni();
            int a = ni();
            int b = ni();
            if (c == 1) update(a-1, b * U);
            else res.append(search(1, 0, S-1, a-1, b-1)[M]).append('\n');
        }
        System.out.print(res);
    }

    static void update(int i, int v) {
        i += S;
        for (int j=0; j<4; ++j) seg[i][j] = v;
        while ((i>>=1)>0) merge(seg[i], seg[i<<1], seg[(i<<1)+1]);
    }

    static int[] search(int i, int cl, int cr, int ql, int qr) {
        if (ql<=cl && cr<=qr) return seg[i];
        if (qr<cl || cr<ql) return new int[]{-INF,-INF,-INF,-INF};
        int cm = cl+cr >> 1;
        return merge(new int[4], search(i<<1, cl, cm, ql, qr), search((i<<1)+1, cm+1, cr, ql, qr));
    }

    static int[] merge(int[] c, int[] lc, int[] rc) {
        c[A] = lc[A] + rc[A] + V;
        c[L] = Math.max(Math.max(c[A], lc[L]), lc[A] + rc[L] + V);
        c[R] = Math.max(Math.max(c[A], rc[R]), lc[R] + rc[A] + V);
        c[M] = Math.max(
                Math.max(Math.max(c[L], c[R]), c[A]),
                Math.max(Math.max(lc[M], rc[M]), lc[R] + rc[L] + V)
        );
        return c;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
