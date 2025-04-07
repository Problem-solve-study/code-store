import java.io.*;

/*
83152KB, 436ms

약간의 응용이 첨가된 세그먼트 트리?
노드에 해당 구간 내부 Ai + Aj의 최댓값(a)과 최대 원소(b)를 동시에 저장
Ai + Aj의 최댓값은 max(max(leftA, rightA), leftB + rightB)로 구할 수 있음
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, M;
    static int[] arr;
    static int[][] tree;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        arr = new int[N + 1];
        //0: a + b의 최댓값, 1: 트리 값의 최댓값
        tree = new int[N * 4][2];
        for (int i = 1; i <= N; i++) {
            arr[i] = nextInt();
        }

        init(1, 1, N);
        M = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            int order = nextInt();
            int a = nextInt();
            int b = nextInt();
            if (order == 1) {
                update(a, b, 1, 1, N);
            } else {
                sb.append(query(a, b, 1, 1, N)[0]).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void init(int treeIdx, int l, int r) {
        if (l == r) {
            tree[treeIdx][0] = tree[treeIdx][1] = arr[l];
            return;
        }

        int mid = (l + r) / 2;
        init(treeIdx * 2, l, mid);
        init(treeIdx * 2 + 1, mid + 1, r);
        tree[treeIdx][0] = Math.max(tree[treeIdx * 2][1] + tree[treeIdx * 2 + 1][1], Math.max(tree[treeIdx * 2][0], tree[treeIdx * 2 + 1][0]));
        tree[treeIdx][1] = Math.max(tree[treeIdx * 2][1], tree[treeIdx * 2 + 1][1]);
    }

    static void update(int arrIdx, int v, int treeIdx, int l, int r) {
        if (arrIdx < l || r < arrIdx) return;
        if (l == r) {
            arr[arrIdx] = v;
            tree[treeIdx][0] = tree[treeIdx][1] = v;
            return;
        }

        int mid = (l + r) / 2;
        update(arrIdx, v, treeIdx * 2, l, mid);
        update(arrIdx, v, treeIdx * 2 + 1, mid + 1, r);
        tree[treeIdx][0] = Math.max(tree[treeIdx * 2][1] + tree[treeIdx * 2 + 1][1], Math.max(tree[treeIdx * 2][0], tree[treeIdx * 2 + 1][0]));
        tree[treeIdx][1] = Math.max(tree[treeIdx * 2][1], tree[treeIdx * 2 + 1][1]);
    }

    static int[] query(int ql, int qr, int treeIdx, int l, int r) {
        if (qr < l || r < ql) return new int[] {0, 0};
        if (ql <= l && r <= qr) {
            return tree[treeIdx];
        }

        int mid = (l + r) / 2;
        int[] leftSubTree = query(ql, qr, treeIdx * 2, l, mid);
        int[] rightSubTree = query(ql, qr, treeIdx * 2 + 1, mid + 1, r);
        return new int[] {Math.max(leftSubTree[1] + rightSubTree[1], Math.max(leftSubTree[0], rightSubTree[0])), Math.max(leftSubTree[1], rightSubTree[1])};
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
