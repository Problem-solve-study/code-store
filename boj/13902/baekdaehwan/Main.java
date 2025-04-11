/**
 * 12800KB	120ms
 * 
 * [사고흐름]
 * N^2인데 되나..? (120ms)
 * 
 * [알고리즘 설명]
 * 한 번에 요리가 가능한 경우를 전부 1로 초기화하고,
 * 배낭문제 풀듯이 DP 돌렸습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static final int INF = 10_000;
    static int N, M;
    static int[] A;
    static int[] DP;
    
    public static void main(String[] args) throws Exception {
        FastReader in = new FastReader();
    	N = in.ni();
    	M = in.ni();
    	A = new int[M];
    	DP = new int[N+1];
    	Arrays.fill(DP, INF);
    	for (int i=0; i<M; ++i) {
    		int c = in.ni();
    		A[i] = c;
    		DP[c] = 1;
    	}
    	DP[0] = 0;
    	for (int i=0; i<M; ++i) {
    		for (int j=i+1; j<M; ++j) {
    			if (A[i]+A[j]<=N) DP[A[i]+A[j]] = 1;
    		}
    	}
    	for (int i=1; i<=N; ++i) {
    		for (int j=1; j<i; ++j) {
    			DP[i] = Math.min(DP[i], DP[i-j]+DP[j]);
    		}
    	}
    	System.out.println(DP[N]==INF? -1:DP[N]);
    }
    
    static class FastReader {
        final int BUFFER_SIZE = 1 << 16;
        DataInputStream din;
        byte[] buffer;
        int bufferPointer, bytesRead;

        FastReader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        int ni() throws Exception {
            byte c;
            while ((c = read()) <= 32);
            int n = c & 15;
            while ((c = read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
            return n;
        }

        void fillBuffer() throws Exception {
            bytesRead = din.read(buffer, 0, BUFFER_SIZE);
            if (bytesRead == -1) buffer[0] = -1;
            bufferPointer = 0;
        }

        byte read() throws Exception {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }
    }
}
