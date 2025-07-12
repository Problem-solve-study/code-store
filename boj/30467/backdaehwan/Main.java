import java.io.*;
import java.util.*;

public class Main {
    static int N, L, S;
    static int[] seg;
    static Set<Integer> ts;
    static HashMap<Integer, Integer> toCmp;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        L = ni();
        seg = new int[N+1 << 2];
        ts = new TreeSet<>();
        toCmp = new HashMap<>();

        S = 1;
        while (S < N+1) S<<=1;
        int[] A = new int[N];
        for (int i=0; i<N; ++i) ts.add(A[i]=ni());
        toCmp = new HashMap<>();
        int v = 0;
        for (int k: ts) toCmp.put(k, v++);
        for (int i=0; i<N; ++i) A[i] = toCmp.get(A[i]);

        long res = 0;
        long cnt = 0;
        for (int i=0; i<L; ++i) {
            int cr = query(1, 0, S-1, 0, A[i]-1);
            add(A[i], 1);
            res = Math.max(res, cnt += cr);
        }
        for (int i=L; i<N; ++i) {
            int lr = query(1, 0, S-1, A[i-L]+1, S-1);
            add(A[i-L], -1);
            int cr = query(1, 0, S-1, 0, A[i]-1);
            add(A[i], 1);
            res = Math.max(res, cnt += (cr-lr));
        }
        System.out.println(res);
    }

    static void add(int i, int v) {
        i += S; seg[i] += v;
        while ((i>>=1)>0) seg[i] = seg[i<<1] + seg[(i<<1)+1];
    }

    static int query(int i, int cl, int cr, int ql, int qr) {
        if (ql<=cl && cr<=qr) return seg[i];
        if (cr<ql || qr<cl) return 0;
        int cm = (cl+cr) >> 1;
        return query(i<<1, cl, cm, ql, qr) + query((i<<1)+1, cm+1, cr, ql, qr);
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}