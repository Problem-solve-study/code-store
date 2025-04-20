// 11508 KB, 64 ms
/*
 * 전개도가 주어지면 올바른 전개도인지 확인하기.
 * 정육면체이려면, 모든 면이 4개의 면과 인접해야함.
 * 면의 각 변이 어떤 면과 만나는지 기록하자.
 * 
 * 기록해 보니 다음과 같은 관계가 보임.
 * 인접하는 두 면은, 공유하는 변과 연결된 두 면도 공유함.
 * 한 면의 반대쪽 변에서 만나는 면은, 다른 한 면에 평행하는 면임.
 * 즉, 평행하는 면 제외하고 나머지 네 면과 만남을 알 수 있음.
 * 
 * 하나씩 채워가면서, 모순이 생기면 올바른 전개도가 아님을 알 수 있다.
 * 구현 개빡세네..
 */

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;

    static int[][] adjMatrix;
    static ArrayList<Data> adjList;

    public static void main(String[] args) throws IOException {
        int T = 3;
        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int[][] map = new int[6][6];
            int cnt = 0;
            for (int i = 0; i < 6; i++)
                for (int j = 0; j < 6; j++) {
                    map[i][j] = readInt();
                    if (map[i][j] == 1)
                        map[i][j] = ++cnt;
                }

            // 면이 6개가 아닌 경우
            if (cnt != 6) {
                sb.append("no").append('\n');
                continue;
            }

            // 전개도 상 인접 관계 표시
            adjList = new ArrayList<>();
            adjMatrix = new int[7][4];
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    if (map[i][j] != 0) {
                        if (i - 1 >= 0 && map[i - 1][j] != 0) {
                            adjList.add(new Data(UP, map[i][j], map[i - 1][j]));
                            adjMatrix[map[i][j]][UP] = map[i - 1][j];
                        }
                        if (i + 1 < 6 && map[i + 1][j] != 0) {
                            adjList.add(new Data(DOWN, map[i][j], map[i + 1][j]));
                            adjMatrix[map[i][j]][DOWN] = map[i + 1][j];
                        }
                        if (j - 1 >= 0 && map[i][j - 1] != 0) {
                            adjList.add(new Data(LEFT, map[i][j], map[i][j - 1]));
                            adjMatrix[map[i][j]][LEFT] = map[i][j - 1];
                        }
                        if (j + 1 < 6 && map[i][j + 1] != 0) {
                            adjList.add(new Data(RIGHT, map[i][j], map[i][j + 1]));
                            adjMatrix[map[i][j]][RIGHT] = map[i][j + 1];
                        }
                    }
                }
            }

            boolean res = update() & isValid();
            sb.append(res ? "yes" : "no").append('\n');
        }
        System.out.print(sb);
    }

    // 더이상 갱신이 발생하지 않을 때까지 반복, 모순 발생하면 종료
    static boolean update() {
        // 갱신 발생 여부
        boolean flag;
        do {
            flag = false;
            for (Data d : adjList) {
                // 인접한 변으로 알 수 있는 면 나머지 복사
                switch (d.type) {
                    case UP:
                    case DOWN:
                        if (adjMatrix[d.a][LEFT] == 0 && adjMatrix[d.b][LEFT] != 0) {
                            flag = true;
                            adjMatrix[d.a][LEFT] = adjMatrix[d.b][LEFT];
                        }
                        if (adjMatrix[d.a][RIGHT] == 0 && adjMatrix[d.b][RIGHT] != 0) {
                            flag = true;
                            adjMatrix[d.a][RIGHT] = adjMatrix[d.b][RIGHT];
                        }
                        break;
                    case LEFT:
                    case RIGHT:
                        if (adjMatrix[d.a][UP] == 0 && adjMatrix[d.b][UP] != 0) {
                            flag = true;
                            adjMatrix[d.a][UP] = adjMatrix[d.b][UP];
                        }
                        if (adjMatrix[d.a][DOWN] == 0 && adjMatrix[d.b][DOWN] != 0) {
                            flag = true;
                            adjMatrix[d.a][DOWN] = adjMatrix[d.b][DOWN];
                        }
                        break;
                }
                // 나머지 다 차있고, 평행 면을 알 수 있는 경우
                int cnt = 0, idx = -1, sum = 0;
                for (int j = 0; j < 4; j++) {
                    sum += adjMatrix[d.a][j];
                    if (adjMatrix[d.a][j] == 0) {
                        cnt++;
                        idx = j;
                    }
                }
                if (cnt == 1 && adjMatrix[d.b][d.type] != 0) {
                    flag = true;
                    adjMatrix[d.a][idx] = 21 - d.a - sum - adjMatrix[d.b][d.type];
                    if (adjMatrix[d.a][idx] < 1 || adjMatrix[d.a][idx] > 6)
                        return false;
                }
            }
        } while (flag);
        return true;
    }

    // 최종 상태가 올바른지 확인
    static boolean isValid() {
        for (int i = 1; i <= 6; i++) {
            for (int j = 0; j < 4; j++) {
                if (adjMatrix[i][j] == 0)
                    return false;
                if (adjMatrix[i][j] < 1 || adjMatrix[i][j] > 6)
                    return false;
                boolean flag = false;
                for (int k = 0; k < 4; k++) {
                    if (adjMatrix[adjMatrix[i][j]][k] == i) {
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    return false;
            }
        }
        return true;
    }

    static class Data {
        int type, a, b;

        public Data(int type, int a, int b) {
            this.type = type;
            this.a = a;
            this.b = b;
        }

    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}