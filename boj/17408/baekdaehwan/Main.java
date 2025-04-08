/**
 * 	65904KB	408ms
 * 
 * [사고흐름]
 * 원소 두 개짜리 세그트리라고 생각했습니다.
 * 일반적인 구간 최대 문제랑 거의 동일하네요.
 * 너무 웰노운이니 알고리즘 설명은 생략합니다.
 */

import java.io.*;

public class Main {
    static int N, M, S;
    static int[][] seg;
    static int[] min = {Integer.MIN_VALUE, Integer.MIN_VALUE};

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        seg = new int[N<<2][2];
        
        S = 1;
        while (S<=N) S<<=1;
        for (int i=0; i<N; ++i) seg[S+i][0] = ni();
        init(1);
        
        M = ni();
        StringBuilder res = new StringBuilder();
        for (int i=0; i<M; ++i) {
        	if (ni() == 1) update(ni()-1, ni());
        	else res.append(sum(getRange(1, 0, S-1, ni()-1, ni()-1))).append('\n');
        }
        System.out.print(res);
    }

    static int sum(int[] a) {
    	return a[0]+a[1];
    }
    
    static int[] getRange(int i, int cl, int cr, int ql, int qr) {
    	if (ql<=cl && cr<=qr) return seg[i];
    	if (cr<ql || qr<cl) return min;
    	
    	int cm = (cl+cr)>>1;
    	int[] tmp = new int[2];
		max2(tmp, getRange(i<<1, cl, cm, ql, qr), getRange((i<<1)+1, cm+1, cr, ql, qr));
		return tmp;
    }
    
    static void update(int i, int v) {
    	i += S;
    	seg[i][0] = v;
    	while ((i>>=1) > 0) max2(seg[i], seg[i<<1], seg[(i<<1)+1]);
    }
    
    static int[] init(int i) {
    	if (i<S) max2(seg[i], init(i<<1), init((i<<1)+1));
    	return seg[i];
    }
    
    static void max2(int[] dest, int[] a, int[] b) {
    	int ai = 0;
    	int bi = 0;
    	dest[0] = a[ai]>b[bi]? a[ai++]:b[bi++];
    	dest[1] = Math.max(a[ai], b[bi]);
    }
    
    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}