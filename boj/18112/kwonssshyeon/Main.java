// 141232KB	488ms
import java.io.*;
import java.util.*;

/**
 * 시작 이진수에서 타겟 이진수로 이동하는데 필요한 최소 횟수를 찾는 문제
 * 처음에는 dp 배열을 만들어 최소값을 memoization할까 했는데, 배열의 크기를 잡기가 애매해서 queue를 이용하기로 한다.
 * 방문체크도 배열을 사용하기 애매해서 HashSet으로 한다.
 * 
 * 주의할 점은 String 으로 연산을 시도하는 순간 시간초과가 발생한다.
 */
public class Main {
    static String l,k;
    static HashSet<Integer> visited = new HashSet<>();
    static Queue<Node> queue = new LinkedList<>();
    static class Node {
        int num, cnt;
        Node (int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int start = Integer.parseInt(br.readLine(), 2);
        int target = Integer.parseInt(br.readLine(), 2);

        queue.add(new Node(start, 0));
        visited.add(start);    

        while (!queue.isEmpty() && queue.peek().num != target) {
            Node node = queue.poll();
            for (int next : getNextElement(node.num)) {
                if (!visited.contains(next)) {
                    queue.add(new Node(next, node.cnt+1));
                    visited.add(next);
                }
            }
        }
        
        System.out.println(queue.peek().cnt);
    }

    /** 입력 num에서 다음으로 도달할 수 있는 수들을 반환한다. */
    static ArrayList<Integer> getNextElement(int num) {
        ArrayList<Integer> result = new ArrayList<>();
        int temp = num;
        int length = 0;
        result.add(num+1); // 두번째 연산(더하기 1)
        result.add(num-1); // 세번째 연산(빼기 1)
        /**
         * 첫번째 연산(보수)
         * length 는 탐색할 비트의 위치를 나타낸다.
         * num & (1<<length) 이 0이면 num 의 해당 위치가 0이라는 뜻이므로 해당 위치를 1로 바꾼다.
         * 1로 바꾸는 연산은 1<<length 를 num 에 더하는 것으로 할 수 있다.
         * 1 -> 0 으로 바꾸는 것도 마찬가지
         */
        while (temp > 1) {
            if ((num & (1 << length)) == 0) result.add(num + (1<<length)); 
            else result.add(num - (1<<length));
            temp /= 2;
            length++;
        }
        return result;
    }
}