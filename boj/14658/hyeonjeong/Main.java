// 90088KB 284ms

import java.io.*;
import java.util.*;

// 좌표 압축 + 누적합
// 누적합 size = 200
// 누적합 탐색(200*200) * L 범위의 J 탐색(200) = 8,000,000
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int L;
    static Map<Integer, Integer> toOrigin = new HashMap<>();
    static Map<Integer, Integer> toCompress = new HashMap<>();
    static int[][] map;   // 압축한 맵
    static int size;      // 압축한 좌표계 크기

    public static void main(String[] args) throws IOException {
        next();
        next();
        L = next();
        int k = next();

        List<int[]> stars = new ArrayList<>();
        List<Integer> points = new ArrayList<>();
        for (; k > 0; k--) {
            int i = next();
            int j = next();
            stars.add(new int[]{i, j});
            points.add(i);
            points.add(j);
        }

        Collections.sort(points);

        // N*M 좌표를 size*size 압축
        size = 0;
        for (Integer point : points) {
            toCompress.put(point, size);
            toOrigin.put(size, point);
            size++;
        }

        // size*size map에 별 심기
        map = new int[size][size];
        for (int[] star : stars) {
            int compI = toCompress.get(star[0]);
            int compJ = toCompress.get(star[1]);

            map[compI][compJ] = 1;
        }

        // 누적합
        for (int i = 1; i < size; i++) {
            map[i][0] += map[i - 1][0];
        }
        for (int j = 1; j < size; j++) {
            map[0][j] += map[0][j - 1];
        }
        for (int i = 1; i < size; i++) {
            for (int j = 1; j < size; j++) {
                map[i][j] += map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1];
            }
        }

        // map을 순회하면서 가려지는 별 탐색
        int maxCount = 0;     // 트램펄린에 가려지는 별의 최대 개수
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int count = countAt(i, j);

                if (count > maxCount) {
                    maxCount = count;
                }
            }
        }

        System.out.println(stars.size() - maxCount);
    }

    static int countAt(int si, int sj) {
        int originStartI = toOrigin.get(si);
        int originStartJ = toOrigin.get(sj);

        int ei = si;
        while (ei < size && toOrigin.get(ei) - originStartI <= L) {
            ei++;
        }
        ei--;

        int ej = sj;
        while (ej < size && toOrigin.get(ej) - originStartJ <= L) {
            ej++;
        }
        ej--;

        if (si == 0 && sj == 0) {
            return map[ei][ej];
        }

        if (si == 0) {
            return map[ei][ej] - map[ei][sj - 1];
        }

        if (sj == 0) {
            return map[ei][ej] - map[si - 1][ej];
        }

        return map[ei][ej] - map[si - 1][ej] - map[ei][sj - 1] + map[si - 1][sj - 1];
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
