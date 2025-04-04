
// 11644KB	68ms
import java.io.*;
import java.util.*;

/**
 * 방문 체크를 위해 boolean배열 -> HashSet 으로 바꿨는데
 * 메모리 뿐만 아니라 실행시간이 612ms -> 68ms 로 확 줄음
 * 1GB 짜리 배열에 랜덤 엑세스가 계속 발생해서 그런게 아닐까 ,,
 * 풀기는 BFS 로 품.
 */
public class Main {
    static int start, target;
    static HashSet<Integer> visited = new HashSet<>();

    static class Node {
        int num;
        String operator;

        Node(int num, String operator) {
            this.num = num;
            this.operator = operator;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        if (start == target) {
            System.out.print(0);
        } else {
            boolean flag = false;
            Queue<Node> queue = new ArrayDeque<>();
            queue.add(new Node(start, ""));
            visited.add(start);

            while (!queue.isEmpty()) {
                Node now = queue.poll();

                if (now.num == target) {
                    flag = true;
                    System.out.print(now.operator);
                    break;
                }

                long next = 1L * now.num * now.num;
                int nextInt = (int) next;
                if (next <= target && !visited.contains(nextInt)) {
                    queue.add(new Node(nextInt, now.operator + "*"));
                    visited.add(nextInt);
                }
                next = 0L + now.num + now.num;
                nextInt = (int) next;
                if (next <= target && !visited.contains(nextInt)) {
                    queue.add(new Node(nextInt, now.operator + "+"));
                    visited.add(nextInt);
                }
                if (now.num != 0 && !visited.contains(1)) {
                    queue.add(new Node(1, now.operator + "/"));
                    visited.add(1);
                }
            }
            if (flag == false) {
                System.out.print(-1);
            }
        }
    }
}