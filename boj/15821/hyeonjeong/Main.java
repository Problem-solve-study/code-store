// 13560KB 80ms

import java.util.*;
import java.io.*;

/**
 * 주어진 다각형에서 가장 거리가 먼 점을 포함 -> 그 다각형을 모두 포함
 * 최대 10만개의 점을 정렬해야 하는데 PQ 썼습니다. 
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = next();
        int k = next();
        
        int pointCount = next();
        PriorityQueue<Long> points = new PriorityQueue<>();
        while (pointCount != 0) {
            long maxDist = 0L;
            for (int i = 0; i < pointCount; i++) {
                long x = next();
                long y = next();
                long dist = x * x + y * y;

                if (dist > maxDist) {
                    maxDist = dist;
                }
            }

            points.add(maxDist);
            
            pointCount = next();
        }

        long curr = 0;
        for (int i = 0; i < k; i++) {
            curr = points.poll();
        }

        System.out.printf("%.2f", (double) curr);
    }

    static int next() throws IOException {
        int type = st.nextToken();
        if (type == st.TT_EOF) {
            return 0;
        }

        return (int) st.nval;
    }
}
