import java.io.*;
import java.util.*;

/*
26720KB 192ms

맨 처음에는 탈옥 지점이 될 후보군들을 추린 후 해당 지점으로부터 죄수1까지 최단 경로를 구한 후
구해진 최단 경로를 역추적해서 문을 열어버리고 이후 죄소2까지의 최단 경로를 구하는 걸로 생각을 했었는데
죄수 - 탈옥 지점에서 가능한 모든 최단 경로를 고려한게 아니라서 반례가 있었음
이후 다익스트라 3번 쓰면 된다는 대환이 말에 힌트를 얻고 풀었다.

이 문제의 답이 그리는 궤적을 생각해봤을 때 가능한 경우는 2가지가 있을 수 있는데
첫 번째는 죄수1과 죄수2가 다른 경로를 이용하는 경우이다.
두 번째는 죄수1과 죄수2가 같은 경로를 공유하는 경우

첫 번째의 경우를 생각해보면 단순히 죄수1과 죄수2의 시점에서 최단 경로를 구하여 두 값을 더해주면 될 것 같아 보인다.
하지만 이 문제에서 정의하는 거리는 이동하는 횟수가 아니라 문을 연 횟수가 되므로 두 죄수가 다른 경로를 이용하는 것이
최단 거리가 될 때가 존재하지 않는다. 죄수1이 문을 열어주면 죄수2의 입장에서는 그 문을 이용하는 것이 최선이기 때문이다.
따라서 모든 경우는 두 번째 경우가 된다는 점을 알 수 있다.

두 번째 경우에서 답이 되는 궤적을 생각해보자.
죄수1과 죄수2은 반드시 어느 지점에서 만나게 될 것이고 그 만나는 지점(접점이라고 하자)에서 시작하여 탈옥 지점까지 갈 것이다.
그리고 답이 되는 궤적의 총 거리는 죄수1~접점 거리 + 죄수2~접점 거리 + 탈옥 지점~접점 거리이다.
이때 이 궤적은 답이 되는, 즉 거리인 경우이기 때문에 저 세 값의 합이 최소이다.

따라서 죄수1, 죄수2, 탈옥 지점에서 최단 경로를 구한 후 모든 점에 대해 세 값의 합이 최소인 경우를 찾아주면 된다.
태그에 0-1 BFS가 있길래 오랜만에 0-1 BFS로 풀이
 */

public class Main {
    static int h, w;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[] pos1, pos2;
    static ArrayDeque<int[]> d = new ArrayDeque<>();
    static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            pos1 = pos2 = null;
            //탈옥 지점에서의 거리를 편하게 구하기 위해 맵의 크기를 +2로 하여
            //실제 맵을 둘러싸도록 구성
            //이러면 0, 0에서 0-1 BFS를 돌리는 것으로 탈옥 지점을 거쳐 최단 거리들을 구할 수 있게 된다.
            map = new char[h + 2][w + 2];
            for (int i = 1; i <= h; i++) {
                String str = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = str.charAt(j - 1);
                    if (map[i][j] == '$') {
                        if (pos1 == null) {
                            pos1 = new int[] {i, j, 0};
                        } else {
                            pos2 = new int[] {i, j, 0};
                        }
                    }
                }
            }

            //최단 거리를 3번 구함
            int[][] dist1 = bfs01(pos1);
            int[][] dist2 = bfs01(pos2);
            int[][] dist3 = bfs01(new int[] {0, 0, 0});
            int res = Integer.MAX_VALUE;
            for (int i = 1; i <= h; i++) {
                for (int j = 1; j <= w; j++) {
                    if (map[i][j] == '*') continue;
                    //해당 지점이 문인 경우에는 2번 중복하여 카운팅하므로 2를 빼준다.
                    if (map[i][j] == '#') {
                        res = Math.min(res, dist1[i][j] + dist2[i][j] + dist3[i][j] - 2);
                    } else {
                        res = Math.min(res, dist1[i][j] + dist2[i][j] + dist3[i][j]);
                    }
                }
            }
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }

    static int[][] bfs01(int[] pos) {
        d.clear();
        int[][] dist = new int[h + 2][w + 2];
        for (int i = 0; i <= h + 1; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        d.add(pos);
        dist[pos[0]][pos[1]] = 0;
        while (!d.isEmpty()) {
            int[] arr = d.removeFirst();
            int r = arr[0]; int c = arr[1]; int cost = arr[2];
            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if (!bc(nr, nc) || map[nr][nc] == '*') continue;
                int nCost = map[nr][nc] == '#' ? 1 : 0;

                if (cost + nCost < dist[nr][nc]) {
                    dist[nr][nc] = cost + nCost;
                    if (nCost == 0) { //0-1 BFS는 현재 코스트가 0이면 앞에 넣음
                        d.addFirst(new int[] {nr, nc, cost + nCost});
                    } else { //그게 아니면 뒤에 넣는다.
                        d.addLast(new int[] {nr, nc, cost + nCost});
                    }
                }
            }
        }

        return dist;
    }

    static boolean bc(int r, int c) {
        return (0 <= r && r <= h + 1) && (0 <= c && c <= w + 1);
    }
}