// 121700KB 820ms

import java.io.*;
import java.util.*;

/**
 * 그리디: 가중치가 가장 적은 선분부터 제거한다.
 * 
 * 가중치 w1 > w2일 때, w1인 선분 1개에 w2인 선분 여러 개가 각각 교차하면
 * w2를 먼저 제거하는 내공 = (w1를 먼저 제거하는 내공) + (w1 * (w2인 선분의 개수)) - (w2 * (w2인 선분의 개수))
 * 이때 w1 > w2이기 때문에 + (w1 * (w2인 선분의 개수)) - (w2 * (w2인 선분의 개수))은 반드시 음수
 * => 가중치가 낮은 걸 먼저 제거하는 게 항상 적음
 * 
 * 가중치가 같은 선분끼리, 선분을 제거하는 순서의 변경으로 인한 교차한 선분의 개수의 변화량은 총합이 항상 0 
 * => 가중치만 고려해서 삭제하면 됨
 */
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    static final int X1 = 0;
    static final int Y1 = 1;
    static final int X2 = 2;
    static final int Y2 = 3;

    public static void main(String[] args) throws IOException {
        final int ID = 0;
        final int WEIGHT = 1;

        // 입력
        int n = next();
        int[][] lines = new int[n][4];      // (x1, y1, x2, y2)
        int[][] lineInfo = new int[n][2];   // (id, weight)의 배열
        List<Set<Integer>> crossLines = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            lines[i][X1] = next();
            lines[i][Y1] = next();
            lines[i][X2] = next();
            lines[i][Y2] = next();

            lineInfo[i][ID] = i;
            lineInfo[i][WEIGHT] = next();

            crossLines.add(new HashSet<>());
        }
        // 가중치로 정렬
        Arrays.sort(lineInfo, (l1, l2) -> l1[WEIGHT] - l2[WEIGHT]);


        // 선분 교차 판정
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isCross(lines[i], lines[j])) {
                    crossLines.get(i).add(j);
                    crossLines.get(j).add(i);
                }
            }
        }

        // 베기
        long sum = 0;
        for (int i = 0; i < n; i++) {
            sum += (1L + crossLines.get(lineInfo[i][ID]).size()) * lineInfo[i][WEIGHT];

            for (int neighbor : crossLines.get(lineInfo[i][ID])) {
                crossLines.get(neighbor).remove(lineInfo[i][ID]);
            }
        }

        System.out.println(sum);
    }

    static boolean isCross(int[] a, int[] b) {
        int ccw1 = ccw(a[X1], a[Y1], a[X2], a[Y2], b[X1], b[Y1]);
        int ccw2 = ccw(a[X1], a[Y1], a[X2], a[Y2], b[X2], b[Y2]);
        int ccw3 = ccw(b[X1], b[Y1], b[X2], b[Y2], a[X1], a[Y1]);
        int ccw4 = ccw(b[X1], b[Y1], b[X2], b[Y2], a[X2], a[Y2]);

        return ccw1 * ccw2 < 0 && ccw3 * ccw4 < 0;
    }

    static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        long value = (long)(x2 - x1) * (y3 - y1) - (long)(y2 - y1) * (x3 - x1);
        return Long.compare(value, 0);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
