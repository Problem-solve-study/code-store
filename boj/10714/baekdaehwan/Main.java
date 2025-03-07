/**
 * 	46868KB	200ms
 */

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[][] DP;
	static long[] A;
	
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
//		내가 조각을 먹고 난 후의 상태 DP[li][bc]에서 나올 수 있는 최댓값
    	N = ni();
    	A = new long[N];
    	DP = new long[N][N];
    	for (int i=0; i<N; ++i) A[i] = ni();
    	long res = 0;
    	for (int cur=0; cur<N; ++cur) {
    		int li = (cur-1+N) % N;
    		long tmp = getDP(li, 1);
    		res = Math.max(res, tmp+A[cur]);
    	}
    	System.out.println(res);
    }
    
    public static long getDP(int li, int bc) {
    	if (bc >= N-1) return 0;
    	
    	if (DP[li][bc] == 0) {
    		int ri = (li+1+bc) % N;
    		if (A[li] > A[ri]) {
    			int nli = (li-1+N) % N;
    			int nbc = bc+1;
        		int nri = (nli+1+nbc) % N;
    			DP[li][bc] = Math.max(getDP((nli-1+N) % N, nbc+1)+A[nli], getDP(nli, nbc+1)+A[nri]);
    		}
    		else {
    			int nbc = bc+1;
        		int nri = (li+1+nbc) % N;
    			DP[li][bc] = Math.max(getDP((li-1+N) % N, nbc+1)+A[li], getDP(li, nbc+1)+A[nri]);
    		}
    	}
    	return DP[li][bc];
    }
    
    private static int ni() throws Exception {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}