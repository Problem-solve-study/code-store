// 26052KB	240ms
import java.io.*;
import java.util.*;
/*
 * 처음 구현할 때 문제를 잘못 이해했어서 시간이 거의 10배 걸림.(근데 왜 맞춘거지)
 * 숫자를 차례로 받으면서 stack에다가 넣는데 매번 숫자와 stack의 peek 값을 비교해서 peek값보다 작으면 false
 * 같으면 pop, 크면 추가하는 형식으로 정직하게 구현하면 됨.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int n = Integer.parseInt(br.readLine());
        int[] nums = new int[n];
        
        for(int i = 0; i < n; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int current = 1;
        boolean possible = true;

        for (int num : nums) {
            while (current <= num) {
                stack.push(current++);
                sb.append("+\n");
            }

            if (stack.peek() == num) {
                stack.pop();
                sb.append("-\n");
            } else {
                possible = false;
                break;
            }
        }

        if (possible) {
            System.out.print(sb);
        } else {
            System.out.println("NO");
        }
    }
}
