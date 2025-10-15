//문제: 23827번 수열 (Easy)
//메모리: 16004 KB
//시간: 144 ms

/*
 * 1 <= i < j <=N 인 모든 정수쌍 (i,j)
 * n이 5일때, 나열해보면
 * (1,2), (1,3), (1,4), (1,5), (2,3), (2,4), (2,5), ..., (3,4), (3,5), (4,5)
 * Ai * Aj의 합은
 * A1*(A2+A3+A4+A5) + A2*(A3+A4+A5) + ... + A4*A5
 * 로 표현할 수 있다.
 * 이를 이용해 문제를 풀었다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[] arr = new int[n + 1], sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = nextInt();
            sum[i] = (sum[i - 1] + arr[i]) % 1_000_000_007;
        }
        long result = 0, temp = sum[n];
        for (int i = 1; i < n; i++) {
            result += ((temp - sum[i]) * arr[i]) % 1_000_000_007;
            result %= 1_000_000_007;
        }
        System.out.println(result);
    }

    static int nextInt() throws Exception {
        int n, c;
        while ((c = System.in.read()) < 48) ;
        n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
