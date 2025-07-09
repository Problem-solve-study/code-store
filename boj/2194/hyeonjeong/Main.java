// 16196KB 136ms

import java.io.*;
import java.util.*;

// BFS
class Main {
    static final StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static final int[][] deltas = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    
    static boolean[][] map;
    static int a, b;

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        a = nextInt();
        b = nextInt();
        int k = nextInt();

        map = new boolean[n][m];
        for (; k > 0; k--) {
            checkBlocked(nextInt() - 1, nextInt() - 1);
        }

        Node start = new Node(nextInt() - 1, nextInt() - 1);
        Node end = new Node(nextInt() - 1, nextInt() - 1);

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(start);
        map[start.i][start.j] = true;

        int count = 0;
        boolean isEnd = false;
        done:
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Node curr = queue.poll();
    
                if (curr.is(end)) {
                    isEnd = true;
                    break done;
                }

                for (int[] delta : deltas) {
                    int ni = curr.i + delta[0];
                    int nj = curr.j + delta[1];

                    if (ni < 0 || ni > n - a|| nj < 0 || nj > m - b) {
                        continue;
                    }

                    if (map[ni][nj]) {
                        continue;
                    }

                    queue.add(new Node(ni, nj));
                    map[ni][nj] = true;
                }
            }

            count++;
        }

        if (isEnd) {
            System.out.println(count);
            return;
        }

        System.out.println(-1);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static void checkBlocked(int si, int sj) {
        int boundI = Math.max(si - a, -1);
        int boundJ = Math.max(sj - b, -1);
        for (int i = si; i > boundI; i--) {
            for (int j = sj; j > boundJ; j--) {
                map[i][j] = true;
            }
        }
    }

    static class Node {
        int i;
        int j;
        
        Node(int i, int j) {
            this.i = i;
            this.j = j;
        }

        boolean is(Node target) {
            if (this.i == target.i && this.j == target.j) {
                return true;
            }

            return false;
        }
    }
}
