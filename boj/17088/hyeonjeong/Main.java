// 33692KB, 368ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] deltas = {0, 1, -1};

        int n = Integer.parseInt(br.readLine());
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        if (n < 3) {
            System.out.println(0);
            return;
        }

        boolean[] flags = {true, true, true, true, true, true, true, true, true};
        int[] count = {2, 1, 2, 1, 0, 1, 2, 1, 2};
        int[] gaps = {
                numbers[0] + 1 - (numbers[1] + 1),
                numbers[0] + 1 - numbers[1],
                numbers[0] + 1 - (numbers[1] - 1),
                numbers[0] - (numbers[1] + 1),
                numbers[0] - numbers[1],
                numbers[0] - (numbers[1] - 1),
                numbers[0] - 1 - (numbers[1] + 1),
                numbers[0] - 1 - numbers[1],
                numbers[0] - 1 - (numbers[1] - 1)
        };
        int[] lastNumbers = {
                numbers[1] + 1,
                numbers[1],
                numbers[1] - 1,
                numbers[1] + 1,
                numbers[1],
                numbers[1] - 1,
                numbers[1] + 1,
                numbers[1],
                numbers[1] - 1,
        };

        for (int i = 2; i < n; i++) {
            for (int j = 0; j < 9; j++) {
                if (flags[j]) {
                    boolean flag = false;
                    for (int delta : deltas) {
                        int ni = numbers[i] + delta;
                        if (lastNumbers[j] - ni == gaps[j]) {
                            lastNumbers[j] = ni;
                            if (delta != 0)
                                count[j]++;
                            flag = true;
                            break;
                        }
                    }

                    if (!flag) {
                        flags[j] = false;
                    }
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for (int j = 0; j < 9; j++) {
            if (flags[j] && count[j] < min) {
                min = count[j];
            }
        }

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);
    }
}
