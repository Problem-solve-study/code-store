// 	11520KB	64ms

import java.io.*;
import java.util.*;


public class Main {
    static int N;
    static char[] F;
    static int[] S = new int[3];
    static int t = -1;
    static int max = Integer.MIN_VALUE;
    static int maxOpr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        F = br.readLine().toCharArray();
        maxOpr = N/2+1;

        for (int i=1; i<N; i+=2) swap(i, i+1);
        solve(2, true);
        solve(2, false);

        System.out.println(max);
    }

    static void solve(int i, boolean ls) {
        if (i>=maxOpr) {
            max = Math.max(max, calc());
            return;
        }
        if (ls) {
            solve(i+1, false);
        }
        else {
            int idx = 2*i;
            swap(idx-2, idx-1);
            swap(idx-1, idx);
            solve(i+1, true);

            swap(idx-1, idx);
            swap(idx-2, idx-1);
            solve(i+1, false);
        }
    }
    
    static int calc() {
        for (char c: F) {
            if ('0'<=c && c<='9') push(c-'0');
            else {
                if (c=='+') push(pop()+pop());
                else if (c=='-') push(-pop()+pop());
                else push(pop()*pop());
            }
        }
        return pop();
    }

    static void swap(int i, int j) {
        char tmp = F[i];
        F[i] = F[j];
        F[j] = tmp;
    }

    static void push(int d) {
        S[++t] = d;
    }
    static int pop() {
        return S[t--];
    }
}
