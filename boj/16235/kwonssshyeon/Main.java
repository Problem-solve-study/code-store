//	295160KB	1048ms
import java.io.*;
import java.util.*;

public class Main {
    static int n,m,k;
    static class Node implements Comparable<Node> {
        int y, x, age;
        Node (int y, int x, int age) {
            this.y = y;
            this.x = x;
            this.age = age;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.age, o.age);
        }
    }
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    static Queue<Node> dead = new ArrayDeque<>();
    static ArrayList<Node> child = new ArrayList<>();
    static int[][] plus, food;
    static int[] dx = new int[] {0,1,1,1,0,-1,-1,-1};
    static int[] dy = new int[] {-1,-1,0,1,1,1,0,-1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        food = new int[n][n];
        for (int i=0; i<n; i++) {
            Arrays.fill(food[i], 5);
        }
        plus = new int[n][n];
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                plus[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            queue.add(new Node(x-1, y-1, z));
        }

        for (int i=0; i<k; i++) {
            spring();
            summer();
            autunm();
            winter();
        }

        System.out.print(queue.size());
    }

    static void spring() {
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if (food[node.y][node.x] < node.age) {
                dead.add(node);
            }
            else {
                food[node.y][node.x] -= node.age;
                child.add(new Node(node.y, node.x, node.age + 1));
            }
        }
        queue.addAll(child);
        child.clear();
    }

    static void summer() {
        while(!dead.isEmpty()) {
            Node node = dead.poll();
            food[node.y][node.x] += node.age / 2;
        }
    }

    static void autunm() {
        for (Node node : queue) {
            if (node.age > 0 && node.age % 5 == 0) {
                for (int i=0; i<8; i++) {
                    int ny = node.y + dy[i];
                    int nx = node.x + dx[i];
                    if (ny>=0 && ny<n && nx>=0 && nx<n) {
                        child.add(new Node(ny, nx, 1));
                    }
                }
            }
        }
        queue.addAll(child);
        child.clear();
    }

    static void winter() {
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                food[i][j] += plus[i][j];
            }
        }
    }
}