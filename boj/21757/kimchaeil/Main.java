//문제: 21757번 나누기
//메모리: 13356 KB
//시간: 104 ms

/*
 * 구간합에 대한 문제라서 누적합이라고 판단
 * 문제의 조건에 맞게 구간을 정하면 구간의 끝 인덱스의 누적합의 값은 순서대로 x,2x,3x,4x가 되어야한다.
 * 마지막 구간의 끝 인덱스는 무조건 끝에 있어야한다.
 * 즉, 마지막 누적합의 값이 0을 포함한 4의 배수여야 조건에 맞게 나눌 수 있으며
 * 위 조건을 만족했을 때, 구간합은 마지막 누적합의 값을 4로 나눈 몫(이하, ps)이 된다.
 * 1) 마지막 누적합의 값이 0이라면:
 *  마지막을 제외한 누적합이 0인 지점의 개수(cnt)를 센다.
 *  조건에 맞게 나눌수 있는 경우는 {cnt}C{3}이 된다. (마지막을 제외한 지점들중 3개를 중복 없이 순서 상관 없이 선택하는 경우이므로)
 * 2) 마지막 누적합의 값이 0이 아니라면:
 *  마지막을 제외하고 경우의 수를 ps, 2*ps, 3*ps인 지점에 대해 dp로 구해준다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++)
            sum[i] = sum[i - 1] + nextInt();

        long[] dp = new long[4];
        dp[0] = 1;
        if (sum[n] == 0) {
            int cnt = -2;
            for (int i = 1; i < n; i++)
                if (sum[i] == 0)
                    cnt++;
            while (cnt-- > 0)
                for (int j = 1; j < 4; j++)
                    dp[j] += dp[j - 1];
        } else if ((sum[n] & 3) == 0) {
            int ps = sum[n] >> 2, idx;
            for (int i = 1; i < n; i++)
                if (sum[i] % ps == 0)
                    if ((idx=sum[i]/ps) > 0 && (idx>>2) == 0)
                        dp[idx] += dp[idx - 1];
        }
        System.out.println(dp[3]);
    }

    static int nextInt() throws Exception {
        int n, c;
        boolean flag = false;
        while ((c = System.in.read()) < 48) flag = c == 45;
        n = c & 15;
        while ((c = System.in.read()) > 47) n = (n << 3) + (n << 1) + (c & 15);
        return flag ? -n : n;
    }
}
