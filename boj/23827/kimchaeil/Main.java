//문제: 23827번 수열 (Easy)
//메모리: 12088 KB
//시간: 124 ms

/*
 * 1 <= i < j <=N 인 모든 정수쌍 (i,j)
 * n이 5일때, 나열해보면
 * (1,2), (1,3), (1,4), (1,5), (2,3), (2,4), (2,5), ..., (3,4), (3,5), (4,5)
 * Ai * Aj의 합은
 * A1*(A2+A3+A4+A5) + A2*(A3+A4+A5) + ... + A4*A5
 * 로 표현할 수 있다.
 * 이를 이용해 문제를 풀었다.
 *
 * refactor)
 * A1*A2 + (A1+A2)*A3 + ...
 * 처럼 반대로 묶는 방법으로 수정
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        long a = 0, b = nextInt(), result = 0;
        while (n-- > 1) result = (result + (a += b) * (b = nextInt())) % 1_000_000_007;
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
