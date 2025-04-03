// 11520 KB, 72 ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] nums = {
            0b1110111, // 0
            0b0100100, // 1
            0b1011101, // 2
            0b1101101, // 3
            0b0101110, // 4
            0b1101011, // 5
            0b1111011, // 6
            0b0100101, // 7
            0b1111111, // 8
            0b1101111 // 9
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int size = Integer.parseInt(input[0]);
        int h = 2 * size + 3;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < input[1].length(); j++) {
                // 가로선 출력
                int num = input[1].charAt(j) - '0';
                if (i == 0 || i == size + 1 || i == 2 * size + 2) {
                    sb.append(' ');
                    char c = ' ';
                    if (i == 0 && ((nums[num] & 1) != 0))
                        c = '-';
                    else if (i == size + 1 && ((nums[num] & 0b1000) != 0))
                        c = '-';
                    else if (i == 2 * size + 2 && ((nums[num] & 0b1000000) != 0))
                        c = '-';
                    for (int k = 0; k < size; k++)
                        sb.append(c);
                    sb.append("  ");
                }

                // 세로선 출력
                else {
                    char c = ' ';
                    if ((1 <= i && i <= size) && (nums[num] & 0b10) != 0)
                        c = '|';
                    if ((size + 2 <= i && i <= 2 * size + 1) && (nums[num] & 0b10000) != 0)
                        c = '|';
                    sb.append(c);
                    for (int k = 0; k < size; k++)
                        sb.append(' ');
                    c = ' ';
                    if ((1 <= i && i <= size) && (nums[num] & 0b100) != 0)
                        c = '|';
                    if ((size + 2 <= i && i <= 2 * size + 1) && (nums[num] & 0b100000) != 0)
                        c = '|';
                    sb.append(c).append(' ');
                }

            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }
}