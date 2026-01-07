// 27672KB 140ms

import java.util.*;
import java.io.*;

/**
 * 완전 탐색
 */
class Main {
    static final int[][] directions = {{1, 0}, { 0, 1 }, { -1, 0 }, { 0, -1 }};
    static final int EMPTY = 0;
    static final int WALL = 6;
    static final int VISIBLE = -1;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[][] map;
    static int n, m;
    static List<int[]> cams;    // 카메라 위치
    static int minEmpty;        // 사각지대 개수 최소값

    public static void main(String[] args) throws IOException {
        n = next();
        m = next();

        map = new int[n][m];
        cams = new ArrayList<>();
        int emptyCount = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = next();

                if (map[i][j] == EMPTY) {
                    emptyCount++;
                    continue;
                }

                if (map[i][j] != WALL) {
                    cams.add(new int[]{ i, j });
                }
            }
        }

        minEmpty = emptyCount;
        search(0, emptyCount);

        System.out.print(minEmpty);
    }

    static void search(int idx, int emptyCount) {
        // 모든 카메라를 탐색했으면 종료
        if (idx == cams.size()) {
            minEmpty = Math.min(emptyCount, minEmpty);
            return;
        }

        int[] spot = cams.get(idx);
        int id = map[spot[0]][spot[1]];

        // 2번 카메라와 5번 카메라를 제외하면, 모두 자기 방향에서 4방향으로 순회해야 함
        if (id != 2 && id != 5) {
            if (id != 1) {
                id--;
            }
            
            // 4방향 회전
            for (int di = 0; di < 4; di++) {
                // 각 방향 탐색
                List<int[]> visited = new ArrayList<>();    // 방문 체크

                // 한 번에 1번 카메라는 1개, 3-4번 카메라는 id-1개만큼의 방향을 볼 수 있음
                for (int size = 0; size < id; size++) {
                    int[] dir = directions[(di + size) % 4];
    
                    int ni = spot[0] + dir[0];
                    int nj = spot[1] + dir[1];
    
                    while (ni >= 0 && ni < n && nj >= 0 && nj < m && map[ni][nj] != WALL) {
                        if (map[ni][nj] == EMPTY) {
                            map[ni][nj] = VISIBLE;
                            visited.add(new int[]{ ni, nj });
                        }

                        ni += dir[0];
                        nj += dir[1];
                    }
                }

                search(idx + 1, emptyCount - visited.size());

                for (int[] v : visited) {
                    map[v[0]][v[1]] = EMPTY;
                }
            }
        }

        // 5번 카메라는 4방향 모두 보고 종료
        else if (id == 5) {
            List<int[]> visited = new ArrayList<>();

            for (int di = 0; di < 4; di++) {
                int ni = spot[0] + directions[di][0];
                int nj = spot[1] + directions[di][1];

                while (ni >= 0 && ni < n && nj >= 0 && nj < m && map[ni][nj] != WALL) {
                    if (map[ni][nj] == EMPTY) {
                        map[ni][nj] = VISIBLE;
                        visited.add(new int[]{ ni, nj });
                    }

                    ni += directions[di][0];
                    nj += directions[di][1];
                }
            }

            search(idx + 1, emptyCount - visited.size());

            for (int[] v : visited) {
                map[v[0]][v[1]] = EMPTY;
            }
        }

        // 2번 카메라는 수평 방향으로 2번 보고 종료
        else {
            for (int di = 0; di < 2; di++) {
                List<int[]> visited = new ArrayList<>();

                for (int size = 0; size <= 2; size += 2) {
                    int[] dir = directions[(di + size) % 4];
    
                    int ni = spot[0] + dir[0];
                    int nj = spot[1] + dir[1];
    
                    while (ni >= 0 && ni < n && nj >= 0 && nj < m && map[ni][nj] != WALL) {
                        if (map[ni][nj] == EMPTY) {
                            map[ni][nj] = VISIBLE;
                            visited.add(new int[]{ ni, nj });
                        }

                        ni += dir[0];
                        nj += dir[1];
                    }
                }

                search(idx + 1, emptyCount - visited.size());

                for (int[] v : visited) {
                    map[v[0]][v[1]] = EMPTY;
                }
            }
        }
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
