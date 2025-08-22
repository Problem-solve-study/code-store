// 47920KB 364ms

import java.io.*;
import java.util.*;

class Main {
    static final int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final int THIRD_VIRUS = -2;
    static int n;
    static int m;
    static int[][] map;
    static int[] ids;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = getDirectIndex(line.charAt(j));
            }

            // System.out.println(Arrays.toString(map[i]));
        }
        
        ids = new int[n * m];
        for (int i = 0; i < n * m; i++) {
            ids[i] = i;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // System.out.printf("check: %d, %d\n", i, j);
                search(i, j);
            }
        }

        Set<Integer> roots = new HashSet<>();
        for (int i = 0; i < n * m; i++) {
            roots.add(find(i));
        }

        // System.out.println(roots);
        System.out.println(roots.size());
    }

    static void search(int i, int j) {
        if (map[i][j] < 0) {
            return;
        }

        int[] delta = deltas[map[i][j]];
        int ni = i + delta[0];
        int nj = j + delta[1];

        map[i][j] = -1;
        union(i * m + j, ni * m + nj);
                // System.out.println(Arrays.toString(ids));
        search(ni, nj);
    }

    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        ids[rootX] = rootY;
    }

    static int find(int x) {
        if (x == ids[x]) {
            return x;
        }
        
        return ids[x] = find(ids[x]);
    }

    static int getDirectIndex(char value) {
        switch (value) {
            case 'N':
                return 3;
            case 'W':
                return 2;
            case 'E':
                return 0;
            case 'S':
                return 1;
        }

        return -1;
    }
}
