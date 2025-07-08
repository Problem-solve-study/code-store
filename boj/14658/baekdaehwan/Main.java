/** 14708KB	108ms
 * 
 * [사고흐름]
 * 좌표압축+누적합+전수탐색? 근데 왜 태그가 브루트포스지..?
 * 일단 될거같아서 처음 생각난 방법대로 풀었습니다.
 * 
 * [알고리즘 설명]
 * 좌표에 사용된 모든 값들을 모아 좌표압축하고, 
 * 압축된 좌표를 바탕으로 맵에 별을 찍어 누적합 한 후,
 * L사이즈의 사각형이 만들어질 수 있는 모든 경우의 수를 탐색했습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N, M, L, K;
    static Node[] node;
    static Map<Integer, Integer> toCmp, toOrg;
    static int[][] map;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        M = ni();
        L = ni();
        K = ni();
        Set<Integer> ts = new TreeSet<>();
        node = new Node[K];
        toCmp = new HashMap<>();
        toOrg = new HashMap<>();
        for (int i=0; i<K; i++) {
            node[i] = new Node(ni(), ni());
            ts.add(node[i].x);
            ts.add(node[i].y);
        }
        ts.add(Integer.MIN_VALUE);
        int v = 0;
        for (int k: ts) {
            toCmp.put(k, v);
            toOrg.put(v, k);
            ++v;
        }

        map = new int[v][v];
        for (Node n: node) {
            n.x = toCmp.get(n.x);
            n.y = toCmp.get(n.y);
            map[n.x][n.y] = 1;
        }
        for (int x=1; x<v; ++x) {
            for (int y=1; y<v; ++y) map[x][y] += map[x-1][y] + map[x][y-1] - map[x-1][y-1];
        }

        int xh = 1;
        int res = 0;
        for (int xl=1; xl<v; ++xl) {
            while (xh<v && toOrg.get(xh)-toOrg.get(xl)<=L) ++xh;
            int yh = 1;
            for (int yl=1; yl<v; ++yl) {
                while (yh<v && toOrg.get(yh)-toOrg.get(yl)<=L) ++yh;
                res = Math.max(res, map[xh-1][yh-1] + map[xl-1][yl-1] - map[xh-1][yl-1] - map[xl-1][yh-1]);
            }
        }
        System.out.println(K-res);
    }

    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
