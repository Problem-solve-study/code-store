/**
 * 	20628KB	316ms
 * 
 * [사고흐름]
 * 백트래킹 + DP인가? 2^N이 되므로 시간초과가 날것이라고 생각했습니다.
 * 순서 등에 강제사항이 없으므로, 그리디라고 생각했습니다. 단순하게 생각하니까 보이네요.
 * 
 * [알고리즘 설명]
 * 코드 자체가 간단하니 생략합니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, X;
    static Node[] arr;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        X = ni();
        arr = new Node[N];

        int total = 0;
        int cnt = (X-1000*N)/4000;
        for (int i=0; i<N; ++i) {
            arr[i] = new Node(ni(), ni());
            total += arr[i].c1000;
        }

        Arrays.sort(arr);
        for (int i=0; i<cnt; ++i) {
            if (arr[i].diff < 0) break;
            total += arr[i].diff;
        }
        System.out.println(total);
    }

    static class Node implements Comparable<Node> {
        int c5000, c1000, diff;
        Node(int c5000, int c1000) {
            this.c5000 = c5000;
            this.c1000 = c1000;
            diff = c5000 - c1000;
        }

        @Override
        public int compareTo(Node o) {
            return o.diff - diff;
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
