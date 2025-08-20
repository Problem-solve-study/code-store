// 48416KB 648ms
import java.io.*;
import java.util.*;

class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static final int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static final int THIRD_VIRUS = -2;
    static int n;
    static int m;
    static int[][] map;
    static int[] count = new int[3];
    static int split = 100_000;    // 3차원 맵 사용 대신 큰 수를 더해 사용했습니다.

    public static void main(String[] args) throws IOException {
        n = nextInt();
        m = nextInt();
        map = new int[n][m];

        Queue<int[]> firstVirus = new ArrayDeque<>();
        Queue<int[]> secondVirus = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int value = nextInt();
                if (value == 1) {
                    firstVirus.add(new int[]{i, j});
                    map[i][j] = 1;
                    count[0]++;
                    continue;
                }
                if (value == 2) {
                    secondVirus.add(new int[]{i, j});
                    map[i][j] = 1;
                    count[1]++;
                    continue;
                }
                map[i][j] = value;
            }
        }

        int time = 2;
        while (!(firstVirus.isEmpty() && secondVirus.isEmpty())) {
            int firstVirusSize = firstVirus.size();
            for (int i = 0; i < firstVirusSize; i++) {
                int[] position = firstVirus.poll();

                // 이전에 2번 바이러스를 만나 3번 바이러스가 되었으면 무시 
                if (map[position[0]][position[1]] == THIRD_VIRUS) {
                    continue;
                }

                for (int[] delta : deltas) {
                    int ni = position[0] + delta[0];
                    int nj = position[1] + delta[1];

                    if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                        continue;
                    }

                    if (map[ni][nj] != 0) {
                        continue;
                    }

                    map[ni][nj] = split + time;
                    firstVirus.add(new int[]{ni, nj});
                    count[0]++;
                }
            }

            int secondVirusSize = secondVirus.size();
            for (int i = 0; i < secondVirusSize; i++) {
                int[] position = secondVirus.poll();
                for (int[] delta : deltas) {
                    int ni = position[0] + delta[0];
                    int nj = position[1] + delta[1];

                    if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                        continue;
                    }

                    // 치료제와 3번 바이러스 무시시
                    if (map[ni][nj] < 0) {
                        continue;
                    }

                    // 1번 바이러스이고 시간이 같으면 3번 바이러스로 변화
                    if ((map[ni][nj] / split == 1) && (map[ni][nj] % split == time)) {
                        count[0]--;
                        count[2]++;
                        map[ni][nj] = THIRD_VIRUS;
                        continue;
                    }

                    if (map[ni][nj] != 0) {
                        continue;
                    }

                    map[ni][nj] = split * 2 + time;
                    secondVirus.add(new int[]{ni, nj});
                    count[1]++;
                }
            }

            time++;
        }

        System.out.printf("%d %d %d", count[0], count[1], count[2]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
