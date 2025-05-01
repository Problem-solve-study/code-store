// 13200 KB, 88 ms
/*
 * 
 * 에라토스테네스 채를 먼저 돌리고 팰린드롬인지 판별 ?
 * 아니면, 팰린드롬을 구해서 소수인지 판별?
 * 100,000,000 이하
 * 99,999,999
 * 
 * 11
 * 22
 * 33
 * ..
 * 99
 * 
 * 111
 * 121
 * ..
 * 313
 * 323
 * ..
 * 393
 * ..
 * 515
 * ..
 * 595
 * 
 * 1111
 * 1221
 * 1331
 * ..
 * 11111
 * 11211
 * 12121
 * 12921
 * ..
 * 13131
 * 13931
 * ..
 * 14141
 * 14941
 * ..
 * 19191
 * 19991
 * ..
 * 총 몇개?
 * 11,105개에 대해 소수 판별
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    static int[] nums = new int[8];
    static int len, cnt, A, B;
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        A = nextInt();
        B = nextInt();
        if (A <= 2)
            sb.append(2).append('\n');
        if (A <= 3)
            sb.append(3).append('\n');
        if (A <= 5)
            sb.append(5).append('\n');
        if (A <= 7)
            sb.append(7).append('\n');
        for (int i = 1; i < 8; i++) {
            len = i + 1;
            makeNum(0, i);
        }
        sb.append(-1);
        System.out.print(sb);
    }

    static boolean isPrimeNumber(int n) {
        if (n == 1)
            return false;
        int root = (int) Math.sqrt(n);
        for (int i = 2; i <= root; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    static boolean makeNum(int l, int r) {
        if (l > r) {
            int num = nums[0];
            for (int i = 1; i < len; i++)
                num = (num << 3) + (num << 1) + nums[i];
            if (isPrimeNumber(num)) {
                if (A <= num && num <= B)
                    sb.append(num).append('\n');
            }
            return num <= B;
        }
        for (int i = 0; i < 10; i++) {
            if (l == 0 && (i & 1) == 0)
                continue;
            nums[l] = nums[r] = i;
            if (!makeNum(l + 1, r - 1))
                return false;
        }
        return true;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;

    }
}