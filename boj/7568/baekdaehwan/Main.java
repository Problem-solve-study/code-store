/**
 * 	11564KB	68ms
 * 
 * [사고흐름]
 * 정렬 문제인가..? (1틀 적립)
 * 문제에서 '덩치 등수는 자신보다 더 "큰 덩치"의 사람의 수로 정해진다'라고 나타나 있습니다.
 * 정렬하는 경우 A == B, B > C, A == C 라는 상황이 발생할 수 있으므로, 잘못 정렬됩니다.
 * N^2이 정해인것 같습니다 ㅜㅜ...
 * 
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] A;
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        N = ni();
        A = new int[N][3];
        for (int i=0; i<N; ++i) A[i] = new int[] {i, ni(), ni()};
        
        StringBuilder sb = new StringBuilder();
        for (int[] cur: A) {
        	int p = 1;
        	for (int[] dest: A) {
        		if (comp(cur, dest) > 0) ++p;
        	}
        	sb.append(p).append(' ');
        }
        System.out.println(sb);
    }
    
    static int comp(int[] a, int[] b) {
		int wc = Integer.compare(a[1], b[1]);
		int hc = Integer.compare(a[2], b[2]);
		if (wc != hc) return 0;
		return -wc;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
