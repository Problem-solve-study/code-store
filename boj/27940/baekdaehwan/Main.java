/**
 * 	16184KB	416ms
 * 
 * 모든 층의 임계값이 동일하며, 항상 비가 내리는 곳에 1층이 포함되기 때문에 쉬운문제.
 * 만약 각 층의 임계값이 다르고, 비가 임의 구간에 내린다면 P3-P4급 문제로 진화합니다. 
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        K = ni();
        int sum = 0;
        for (int i=1; i<=M; ++i) {
            ni();
            if (K < (sum+=ni())) {
                System.out.println(i + " 1");
                System.exit(0);
            }
        }
        System.out.println(-1);
    }
    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}