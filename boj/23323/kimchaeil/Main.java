//문제: 23323번 황소 다마고치
//메모리: 11720 KB
//시간: 72 ms

/*
 * 매일 밤, 황소의 체력이 절반이 된다.
 * -> 체력이 많을수록 체력의 손실이 크다.
 * -> 최소한의 체력으로 밤을 지나게 한다.
 * -> 다음날 체력이 0이 되지 않는 최소한의 체력은 2다.
 * -> 황소에게 먹이를 주지 않고 밤을 지내 체력이 1이 되게 한다.
 * -> 체력이 1이 된 날부터 먹이를 1씩 준다.
 * -> 먹이의 수만큼 생존 일이 늘어난다.
 * -> 먹이를 주지 않고 체력이 1이 될 때까지 최초 체력의 (비트수)일이다.
 * -> 정답은 (n의 비트수 + m)
 * -> n의 비트 수는 log_{2}(n)+1이고 이는 log(n*2)/log(2)로 표현된다.
 * -> 즉, log(n*2)/log(2) + m
 */


public class Main {
    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        for (int T = (int) nextLong(); T > 0; T--)
            sb.append((long) (Math.log(nextLong() << 1) / Math.log(2)) + nextLong()).append('\n');
        System.out.println(sb);
    }

    static long nextLong() throws Exception {
        long n, c;
        while ((c = System.in.read()) < 48) ;
        n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
