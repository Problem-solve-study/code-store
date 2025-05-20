
/*
 * 정수 N, M이 주어지면 N이상 M 이하의 정수를 숫자 하나씩 읽었을 때를 기준으로 사전순으로 정렬해서 출력
 * eight, five, four, nine, one, seven, six, three, two, zero 순서
 * 
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static final int GAP = 1_000_000;

    public static void main(String[] args) throws IOException {
        int[] nums = { 8, 5, 4, 9, 1, 7, 6, 3, 2, 0 };
        int N = readInt(), M = readInt(), num, cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            num = nums[i];
            if (N <= num && num <= M) {
                sb.append(num).append(' ');
                if (++cnt == 10) {
                    sb.append('\n');
                    cnt = 0;
                }
            }
            for (int j : nums) {
                num = (nums[i] << 3) + (nums[i] << 1) + j;
                if (num < N || num > M)
                    continue;
                sb.append(num).append(' ');
                if (++cnt == 10) {
                    sb.append('\n');
                    cnt = 0;
                }
            }
        }
        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        boolean negative = c == 45;
        if (negative)
            c = System.in.read();
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return negative ? -n : n;

    }
}