/**
 * 15644KB	144ms
 * 
 * [사고흐름]
 * 1,2,3으로 만드는 가능한 경우의 수에 최소 스왑 시뮬레이션?
 * 5틀하고 머리 깨졌습니다. 코드 읽지 말아주세요... ㅜㅜㅜ
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A, C;
    static int[][] d = {
    		{0,1,2},
    		{1,2,0},
    		{2,0,1},
    		{1,0,2},
    		{0,2,1},
    		{2,1,0},
    };

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        A = new int[N];
        C = new int[3];
        for (int i=0; i<N; ++i) {
        	int c = ni()-1;
        	A[i] = c;
        	++C[c];
        }
        int res = Integer.MAX_VALUE;
        for (int[] cd: d) {
            int cs = 0;
        	int[][] dcnt = new int[3][3];
        	for (int cc: cd) {
            	int[] cnt = new int[3];
        		for (int i=0; i<C[cc]; ++i) {
        			if (A[cs+i] != cc) {
        				++cnt[A[cs+i]];
        				--cnt[cc];
        			}
        		}
        		cs+=C[cc];
        		dcnt[cc] = cnt;
        	}
        	
        	int cRes = 0;
        	boolean run = true;
        	while (run) {
        		run = false;
        		for (int c=0; c<3; ++c) {
        			if (dcnt[c][c]<0) {
        				run = true;
        				int o1 = (c+1)%3;
        				int o2 = (c+2)%3;
            			if (dcnt[o1][c] > 0) {
        					int possiblePay = Math.min(dcnt[o1][c], dcnt[c][o1]);
        					dcnt[o1][o1] += possiblePay;
        					dcnt[c][c] += possiblePay;
        					dcnt[o1][c] -= possiblePay;
        					dcnt[c][o1] -= possiblePay;
        					cRes += possiblePay;

                			if (dcnt[o1][c] > 0) {
            					int possiblePay2 = Math.min(dcnt[o1][c], dcnt[c][o2]);
            					dcnt[o1][o2] += possiblePay2;
            					dcnt[c][c] += possiblePay2;
            					dcnt[o1][c] -= possiblePay2;
            					dcnt[c][o2] -= possiblePay2;
            					cRes += possiblePay2;
                			}
            			}
            			if (dcnt[o2][c] > 0) {
        					int possiblePay = Math.min(dcnt[o2][c], dcnt[c][o2]);
        					dcnt[o2][o2] += possiblePay;
        					dcnt[c][c] += possiblePay;
        					dcnt[o2][c] -= possiblePay;
        					dcnt[c][o2] -= possiblePay;
        					cRes += possiblePay;

                			if (dcnt[o2][c] > 0) {
            					int possiblePay2 = Math.min(dcnt[o2][c], dcnt[c][o1]);
            					dcnt[o2][o1] += possiblePay2;
            					dcnt[c][c] += possiblePay2;
            					dcnt[o2][c] -= possiblePay2;
            					dcnt[c][o1] -= possiblePay2;
            					cRes += possiblePay2;
                			}
            			}
        			}
        		}
        	}
        	
        	res = Math.min(res, cRes);        	
        }
        System.out.println(res);
    }
    
    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}