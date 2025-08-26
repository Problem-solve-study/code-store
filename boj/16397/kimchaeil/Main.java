//문제: 16397번 탈출
//메모리: 14636 KB
//시간: 92 ms

/*
 * 분기가 2개인 BFS다.
 * 가장 높은 자릿수 찾는 것 구현하고
 * 조건에 신경써서 처리해주면 된다.
 */

import java.util.ArrayDeque;

public class Main {
    static class Node {
        int value, depth;

        public Node(int value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }

    public static void main(String[] args) throws Exception {
        int n = nextInt(), t = nextInt(), g = nextInt();
        boolean[] visited = new boolean[100000];
        boolean isPossible = false;
        int result = 0;
        ArrayDeque<Node> pq = new ArrayDeque<>();
        pq.add(new Node(n, 0));
        visited[n] = true;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (now.depth > t) break;
            if (now.value == g) {
                isPossible = true;
                result = now.depth;
                break;
            }
            int value = now.value + 1;
            if (value <= 99999 && !visited[value]) {
                pq.add(new Node(value, now.depth + 1));
                visited[value] = true;
            }
            value--;
            if ((value <<= 1) <= 99999) {
                int maxDigit = 1;
                for (int temp = value; temp > 0; maxDigit *= 10, temp /= 10) ;
                value -= maxDigit / 10;
                if (!visited[value]) {
                    pq.add(new Node(value, now.depth + 1));
                    visited[value] = true;
                }
            }
        }
        System.out.println(isPossible ? result : "ANG");
    }

    static int nextInt() throws Exception {
        int n, c;
        while ((c = System.in.read()) < 48) ;
        n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
