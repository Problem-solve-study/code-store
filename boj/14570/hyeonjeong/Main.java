// 80516KB 600ms

import java.io.*;
import java.util.*;

// 홀수 = 왼쪽으로, 짝수 = 오른쪽으로
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken()) - 1;

            if (u >= 0) {
                nodes[i].left = nodes[u];
            }

            if (v >= 0) {
                nodes[i].right = nodes[v];
            }
        }

        long k = Long.parseLong(br.readLine());

        Node node = findLast(nodes[0], k);

        System.out.println(node.id + 1);
    }

    static Node findLast(Node parent, long amount) {
        // 자식이 없으면 종료
        if (parent.left == null && parent.right == null) {
            return parent;
        }

        // 자식이 하나면 해당 서브트리에서 탐색
        if (parent.left == null) {
            return findLast(parent.right, amount);
        }
        
        if (parent.right == null) {
            return findLast(parent.left, amount);
        }

        // 자식이 모두 있으면, 홀짝에 맞춰 해당 서브트리에서 탐색
        long half = amount / 2;
        if (amount % 2 == 0) {
            return findLast(parent.right, half);
        }
        return findLast(parent.left, half + 1);
    }

    static class Node {
        int id;
        Node left = null;
        Node right = null;

        Node(int value) {
            this.id = value;
        }

        @Override
        public String toString() {
            return String.format(
                "Node [id=%d, left=%d, right=%d]",
                this.id,
                this.left == null ? -1 : this.left.id,
                this.right == null ? -1 : this.right.id
            );
        }
    }
}
