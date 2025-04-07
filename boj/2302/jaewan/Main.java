// 11380 KB, 64 ms
/*
 * 좌석번호, 입장권 번호.
 * 입장권 번호 +-1 까지는 앉을 수 있음.
 * 
 * VIP 회원은 반드시 자기 좌석에만 앉아야 함.
 * 가능한 방법의 가짓수.
 * 
 * N은 40이하, 고정석 개수 M 은 N 이하.
 * 
 * 방법의 가짓수는 2,000,000,000을 넘지 않음.
 * 
 * 옆 자리랑 서로 바꿔야 함.
 * 2자리 연속이면 2가지, 3자리 연속은 3가지, 4자리 연속은? 5가지. 5자리 연속은 8가지.
 * 뭔가 피보나치 같은데. 맞네.
 * 새로 추가된 수 n 바꾸는 경우는 f(n-2) 에서 n-1 수와 n 을 바꾸는 경우를 더한 경우와 같다.
 * 
 * 고정석이 아닌 변동석이 연속된 수를 구해서, 곱해서 경우의 수를 구하자.
 */

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        // 피보나치 구하기
        int[] fibo = new int[N + 1];
        fibo[0] = 1;
        fibo[1] = 1;
        for (int i = 2; i <= N; i++)
            fibo[i] = fibo[i - 1] + fibo[i - 2];

        // 고정석 위치를 입력받으면서 경우의 수를 곱한다.
        int M = readInt(), res = 1, prev = 1, cur;
        for (int i = 0; i < M; i++) {
            cur = readInt();
            res *= fibo[cur - prev];
            prev = cur + 1;
        }
        // 마지막은 N+1번째가 고정석인 것과 동일
        res *= fibo[N - prev + 1];
        System.out.println(res);
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}