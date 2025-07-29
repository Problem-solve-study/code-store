// 	30828KB 344ms

import java.io.*;
import java.util.*;

// 다익스트라
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int[][] deltas = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        final int TREE = -1;
        int[] start = new int[2];
        int[] end = new int[2];
        
        // 지도 입력
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] map = new int[n][m];
        int[][] treeDist = new int[n][m];
        Queue<int[]> trees = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            for (int j = 0; j < m ; j++) {
                if (line[j] == '+') {
                    map[i][j] = TREE;
                    treeDist[i][j] = 0;
                    trees.add(new int[]{i, j, 0});
                    continue;
                }
                if (line[j] == 'V') {
                    start[0] = i;
                    start[1] = j;
                    continue;
                }
                if (line[j] == 'J') {
                    end[0] = i;
                    end[1] = j;
                    continue;
                }
            }
        }

        // 나무와의 거리 기록
        while (!trees.isEmpty()) {
            int[] tree = trees.poll();
            for (int[] delta : deltas) {
                int ni = tree[0] + delta[0];
                int nj = tree[1] + delta[1];
                if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                    continue;
                }

                if (map[ni][nj] == TREE || treeDist[ni][nj] != 0 && treeDist[ni][nj] <= tree[2] + 1) {
                    continue;
                }

                treeDist[ni][nj] = tree[2] + 1;
                trees.add(new int[]{ni, nj, treeDist[ni][nj]});
            }
        }

        // 방문 체크를 위해 맵의 모든 영역을 -1로 설정
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                map[i][j] = -1;
            }
        }
      
        // 다익스트라
        PriorityQueue<Node> heap = new PriorityQueue<>();
        heap.add(new Node(start[0], start[1], treeDist[start[0]][start[1]]));
        while (!heap.isEmpty()) {
            Node node = heap.poll();

            if (node.i == end[0] && node.j == end[1]) {
                System.out.println(node.minDist);
                return;
            }

            for (int[] delta : deltas) {
                int ni = node.i + delta[0];
                int nj = node.j + delta[1];

                if (ni < 0 || ni >= n || nj < 0 || nj >= m) {
                    continue;
                }

                int thisMinDist = Math.min(node.minDist, treeDist[ni][nj]);
                if (map[ni][nj] >= thisMinDist) {
                    continue;
                }

                map[ni][nj] = thisMinDist;
                heap.add(new Node(ni, nj, thisMinDist));
            }
        }
    }
}

class Node implements Comparable<Node> {
    int i;
    int j;
    int minDist;

    public Node(int i, int j, int minDist) {
        this.i = i;
        this.j = j;
        this.minDist = minDist;
    }

    @Override
    public int compareTo(Node other) {
        return other.minDist - this.minDist;
    }

    @Override
    public String toString() {
        return String.format("%d, %d - %d", i, j, minDist);
    }
}
