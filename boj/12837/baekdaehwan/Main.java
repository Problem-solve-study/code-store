/**
 * 49652KB	280ms
 * 
 * 무난한 구간합 문제, 세그트리를 한 번도 써본적 없다면 좋은 문제인것 같네요.
 */

import java.io.*;

public class Main {
    static int N, Q, S;
    static long[] seg;
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        Q = ni();
        S = 1;
        seg = new long[N<<2];
        while (S<N) S<<=1;
    	
    	StringBuilder sb = new StringBuilder();
    	for (int i=0; i<Q; ++i) {
    		if (ni()==1) update(ni()-1, ni());
    		else sb.append(search(1, 0, S-1, ni()-1, ni()-1)).append('\n');
    	}
        System.out.print(sb);
    }
    
    static void update(int i, int v) {
    	i+=S; seg[i]+=v;
    	while ((i>>=1)>0) seg[i] = seg[i<<1] + seg[(i<<1)+1];
    }
    
    static long search(int i, int cl, int cr, int ql, int qr) {
    	if (cr<ql || qr<cl) return 0;
    	if (ql<=cl&&cr<=qr) return seg[i];
    	int cm = (cl+cr)>>1;
    	return search(i<<1, cl, cm, ql, qr) + search((i<<1)+1, cm+1, cr, ql, qr);
    }
    
    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}