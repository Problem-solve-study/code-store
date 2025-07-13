import java.io.*;
import java.util.*;

/*
34780KB, 544ms

현재 원소 기준 왼쪽에 있는 원소들 중 자신보다 작은 값을 가진 원소의 개수를 빠르게 찾는 문제.
자신보다 작은 값을 기준으로 구간 쿼리를 날려야하므로 값을 기준으로 세그먼트 트리를 구성한다.
이때 값의 범위가 상당히 크므로 좌표 압축으로 세그 트리에 들어오도록 해줌.

전체 구간에서 S 길이만큼의 구간만큼만을 보므로 슬라이딩 윈도우로 최대 값을 적절히 계산한다.

첫 구간만큼을 먼저 계산해주고
이후에는 구간에서 빠지는 값을 세그트리에서 빼준 후 해당 값보다 큰 값의 개수만큼 빼준다.
카운팅된 원소들은 당시 구간 쿼리를 날렸을 때 현재 빠지는 값을 카운팅했을 것인데, 이를 카운팅하면 안되므로 빼주는 것
구간에 들어오는 값을 세그트리에 넣고 자신보다 작은 값의 개수를 센다.

오버플로우 조심
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, S;
    static int[] A, tree;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        S = nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) A[i] = nextInt();

        //좌표 압축
        TreeSet<Integer> set = new TreeSet<>();
        for (int a : A) set.add(a);
        HashMap<Integer, Integer> map = new HashMap<>();
        int idx = 0;
        for (int s : set) map.put(s, idx++);
        for (int i = 0; i < N; i++) A[i] = map.get(A[i]);

        //세그먼트 트리를 만들고
        //첫 구간에 따른 값을 계산
        tree = new int[map.size() * 4];
        long res = 0;
        for (int i = 0; i < S; i++) {
            res += query(1, 0, map.size() - 1, 0, A[i] - 1);
            update(1, 0, map.size() - 1, A[i], 1);
        }

        //슬라이딩 윈도우로 최댓값 계산
        long cur = res;
        for (int i = 1; i <= N - S; i++) {
            //구간에서 빠지는 값보다 큰 값의 개수를 계산하여 cur에서 뺌
            cur -= query(1, 0, map.size() - 1, A[i - 1] + 1, map.size() - 1);
            //구간에서 빠지는 값을 세그 트리에서 뺌
            update(1, 0, map.size() - 1, A[i - 1], -1);
            //구간에 들어오는 값보다 작은 값의 개수를 계산하여 cur에 더함
            cur += query(1, 0, map.size() - 1, 0, A[i + S - 1] - 1);
            //구간에 들어오는 값을 세그 트리에 더함
            update(1, 0, map.size() - 1, A[i + S - 1], 1);
            res = Math.max(res, cur);
        }
        System.out.print(res);
    }

    static int query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return 0;
        if (ql <= l && r <= qr) return tree[node];

        int mid = (l + r) / 2;
        int lv = query(node * 2, l, mid, ql, qr);
        int rv = query(node * 2 + 1, mid + 1, r, ql, qr);
        return lv + rv;
    }

    static void update(int node, int l, int r, int idx, int diff) {
        if (idx < l || r < idx) return;
        tree[node] += diff;

        if (l != r) {
            int mid = (l + r) / 2;
            update(node * 2, l, mid, idx, diff);
            update(node * 2 + 1, mid + 1, r, idx, diff);
            tree[node] = tree[node * 2] + tree[node * 2 + 1];
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}