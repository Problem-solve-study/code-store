// 	109892KB	760ms
import java.io.*;
import java.util.*;

/**
 * 세 수가 전부 같아질때까지 완탐
 * 세 수의 조합으로 방문체크 (a,b,c 의 순서 상관없이 같은 조합이 반복될 수 있다.)
 */
public class Main {
    static class Node {
        int a,b,c;
        Node (int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Node) {
                Node node = (Node) o;
                return this.a == node.a && this.b == node.b && this.c == node.c;
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c);
        }
    }
    static HashSet<Node> visited = new HashSet<>();
    static int target = 0;
    static byte answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        target = (a + b + c);
        if ((target %= 3) != 0) {
            System.out.print(answer);
        } else {
            dfs(a, b, c);
            System.out.print(answer);
        }
    }

    static void dfs(int a, int b, int c) {
        int[] arr = {a, b, c};
        Arrays.sort(arr);
        a = arr[0]; b = arr[1]; c = arr[2];
    
        if (answer == 1) return;
        if (a == b && b == c) {
            answer = 1;
            return;
        }
    
        Node node = new Node(a, b, c);
        if (visited.contains(node)) return;
        visited.add(node);
    
        if (a < b) dfs(a + a, b - a, c);
        if (a < c) dfs(a + a, b, c - a);
        if (b < c) dfs(a, b + b, c - b);
    }
}