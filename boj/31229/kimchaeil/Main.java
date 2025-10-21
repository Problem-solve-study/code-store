//문제: 31229번 또 수열 문제야
//메모리: 11704 KB
//시간: 68 ms

/*
 * 홀수+홀수=짝수
 * 홀수*홀수=홀수
 * 홀수의 약수는 홀수만 존재하므로
 * 홀수*홀수의 약수가 홀수+홀수일 수 없다.
 * 즉, 서로 다른 홀수로 이루어진 수열을 출력하면 된다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        StringBuilder sb = new StringBuilder();
        while (n-- > 0)
            sb.append((n << 1) | 1).append(' ');
        System.out.println(sb);
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
