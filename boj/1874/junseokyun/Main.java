
// 27452KB, 260ms
import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr;
    public static Stack<Integer> stack;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine()); // 숫자의 개수
        arr = new int[N]; // 수열을 저장할 배열
        boolean flag = true;
        stack = new Stack<>();

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int num = 1;
        for (int i = 0; i < N; i++) {
            int target = arr[i];
            while (num <= target) {
                stack.push(num++);
                sb.append("+").append('\n');
            }

            if (!stack.isEmpty() && stack.peek() == target) {
                stack.pop();
                sb.append("-").append('\n');
            } else {
                flag = false;
                break;
            }
        }
        if (flag) {
            System.out.println(sb.toString());
        } else {
            System.out.println("NO");
        }
    }
}
