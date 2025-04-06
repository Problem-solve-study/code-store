//Memory 14456KB Time 108ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeSet;

public class Main {
    static class Operation {
        long value;
        String ops;

        Operation(long value, String ops) {
            this.value = value;
            this.ops = ops;
        }
    }

    static long s, t;

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        s = Long.parseLong(input[0]);
        t = Long.parseLong(input[1]);
    }

    static String solution() {
        if (s == t) return "0";
        if (s == 0) return "-1";

        Queue<Operation> queue = new LinkedList<>();
        TreeSet<Long> visited = new TreeSet<>();
        queue.offer(new Operation(s, ""));
        visited.add(s);

        while (!queue.isEmpty()) {
            Operation current = queue.poll();
            long value = current.value;
            String ops = current.ops;

            if (value == t) return ops;

            // 가능한 연산들 적용
            if (value * value <= 1e9 && !visited.contains(value * value)) {
                queue.offer(new Operation(value * value, ops + "*"));
                visited.add(value * value);
            }
            if (value + value <= 1e9 && !visited.contains(value + value)) {
                queue.offer(new Operation(value + value, ops + "+"));
                visited.add(value + value);
            }
            if (value - value >= 0 && !visited.contains(value - value)) {
                queue.offer(new Operation(value - value, ops + "-"));
                visited.add(value - value);
            }
            if (value != 0 && !visited.contains(value / value)) {
                queue.offer(new Operation(value / value, ops + "/"));
                visited.add(value / value);
            }
        }

        return "-1"; // 변환 불가능한 경우
    }

    public static void main(String[] args) throws IOException {
        input();
        System.out.println(solution());
    }
}
