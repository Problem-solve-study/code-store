//문제: 28419번 더하기
//메모리: 12696 KB
//시간: 328 ms

/*
 *  연속된 3개의 값을 1 증가시킨다면
 *  2가지 경우가 있다.
 *  홀,짝,홀을 증가시키는 경우: 홀수번째 합 1 증가
 *  짝,홀,짝을 증가시키는 경우: 짝수번째 합 1 증가
 *  그렇다면 홀수번째 합과 짝수번째 합의 차이만큼 연산하면 된다.
 *  즉, 홀수번째 합과 짝수번째 합의 차가 출력값이다.
 *
 *  하지만 n이 3일때,
 *  홀,짝,홀을 증가시킬수 밖에 없으므로
 *  짝수번째 합이 홀수번째 합보다 작다면
 *  문제에서 요구하는 조건을 만족할 수 없기에 -1을 출력하면된다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        long result = 0;
        for (int i = 0; i < n; i++)
            result += (i & 1) == 0 ? nextInt() : -nextInt();
        System.out.println(result > 0 ? (n == 3 ? -1 : result) : -result);
    }

    static int nextInt() throws Exception {
        int n, c;
        boolean flag = false;
        while ((c = System.in.read()) < 48)
            flag = c == 45;
        n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return flag ? -n : n;
    }
}
