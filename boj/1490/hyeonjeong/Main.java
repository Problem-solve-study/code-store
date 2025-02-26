// 12648KB, 76ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    static boolean[] isContains;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Queue<Long> queue = new ArrayDeque<>();
        isContains = new boolean[9];
      
        String number = br.readLine();
        for (char ch : number.toCharArray()) {
            if (ch != '0')
                isContains[ch - '0' - 1] = true;
        }

        queue.add(Long.parseLong(number));
        while (!queue.isEmpty()) {
            long n = queue.poll();

            if (isDividable(n)) {
                System.out.println(n);
                break;
            }

            for (int last = 0; last < 10; last++) {
                queue.add(n * 10 + last);
            }
        }


    }

    static boolean isDividable(long n) {
        for (int digit = 1; digit < 10; digit++) {
            if (isContains[digit - 1] && n % digit != 0)
                return false;
        }

        return true;
    }
}
