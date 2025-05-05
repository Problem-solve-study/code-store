/**
 * 	20660KB	120ms
 * 
 * 재완이형한테 풀이를 듣고 풀었습니다.
 * 등장하는 x, y만이 최소 횟수로 모일 수 있는 지점이라는 것을 증명하는게 어려운것 같습니다. 
 * 풀이를 시작할때부터 그럴것 같다는 직관적인 생각만 들고, 저는 실제로 그렇다는 것을 증명하지는 못했습니다.
 */

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static Set<Integer> X, Y;
    static Point[] point;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        X = new HashSet<>();
        Y = new HashSet<>();
        point = new Point[N];

        for (int i = 0; i < N; i++) {
            point[i] = new Point(ni(), ni());
            X.add(point[i].x);
            Y.add(point[i].y);
        }

        int[] res = new int[N];
        int[] dist = new int[N];
        Arrays.fill(res, Integer.MAX_VALUE);
        for (int x: X) {
            for (int y: Y) {
                for (int i=0; i<N; ++i) dist[i] = point[i].dist(x, y);
                Arrays.sort(dist);
                int sum = 0;
                for (int i=0; i<N; ++i) res[i] = Math.min(res[i], sum+=dist[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r: res) sb.append(r).append(' ');
        System.out.println(sb);
    }

    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int dist(int x, int y) {
            return Math.abs(this.x-x) + Math.abs(this.y-y);
        }
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}