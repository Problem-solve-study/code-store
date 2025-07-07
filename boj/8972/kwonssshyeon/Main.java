import java.io.*;
import java.util.*;

/**
 * 구현 문제
 * 2중 반복문에서 가장 안쪽 break를 밖으로 전파해주지 않아서 3틀...
 */
public class Main {
    static int n, m, y, x;
    static int[] dx = {0,-1,0,1,-1,0,1,-1,0,1};
    static int[] dy = {0,1,1,1,0,0,0,-1,-1,-1};
    static Queue<Node> crazy = new ArrayDeque<>(); // (y좌표, x좌표, 턴 수)
    static HashSet<Node> set = new HashSet<>();
    static HashSet<Node> dup = new HashSet<>();
    static int turn = 0;
    static class Node {
        int y, x;
        Node (int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override
        public boolean equals(Object o) {
            if (o instanceof Node) {
                Node node = (Node) o;
                return node.y == this.y && node.x == this.x;
            }
            return false;
        }
        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }

    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        char[][] map = new char[n][m];
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<m; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == 'I') {
                    y = i; x = j;
                }
                else if (map[i][j] == 'R') {
                    crazy.add(new Node(i, j));
                }
            }
        }
        
        String command = br.readLine();
        for (int i=0; i<command.length(); i++) {
            dup.clear();
            set.clear();
            int op = command.charAt(i) - '0';
            // 종수 아두이노 이동
            y += dy[op];
            x += dx[op];
            for (Node r : crazy) {
                if (r.y == y && r.x == x) {
                    turn = i+1;
                    break;
                }
            }
            // 미친 아두이노 이동
            while (!crazy.isEmpty()) {
                Node now = crazy.poll();
                int[] minPos = calculateCrazyPos(now.y, now.x);
                if (minPos[0] == y && minPos[1] == x) {
                    turn = i+1;
                    break;
                }
                Node next = new Node(minPos[0], minPos[1]);
                if (set.contains(next)) dup.add(next);
                else set.add(next);
            }

            if (turn != 0) break;
            // 다음 crazy 삽입
            set.removeAll(dup);
            for (Node r : set) {
                crazy.add(r);
            }
        }

        if (turn != 0) {
            System.out.println("kraj "+turn);
        } else {
            System.out.print(mapToString());
        }
    }

    static int[] calculateCrazyPos(int cy, int cx) {
        int minDist = 201;
        int[] minPos = new int[2];
        for (int i=1; i<=9; i++) {
            int ny = cy + dy[i];
            int nx = cx + dx[i];
            if (ny>=0 && ny<n && nx>=0 && nx<m) {
                int temp = Math.abs(y - ny) + Math.abs(x - nx);
                if (temp < minDist) {
                    minDist = temp;
                    minPos[0] = ny; minPos[1] = nx;
                }
            }
        }
        return minPos;
    }

    static String mapToString() {
        StringBuilder sb = new StringBuilder();
        char[][] printMap = new char[n][m];
        while (!crazy.isEmpty()) {
            Node r = crazy.poll();
            printMap[r.y][r.x] = 'R';
        }
        printMap[y][x] = 'I';
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (printMap[i][j] == '\u0000') {
                    printMap[i][j] = '.';
                }
            }
        }
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                sb.append(printMap[i][j]);
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}