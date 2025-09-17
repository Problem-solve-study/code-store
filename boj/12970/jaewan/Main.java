
/*
 * 앞에 나온 A 개수만큼, 해당 자리에 B일 때마다 (i, j) 쌍이 추가됨.
 * A: 숫자 증가, B: 더하기
 * 1+1 = 2
 * 0 BB
 * 2 2 2 2 2 2
 * BBAABBBBBB
 * 3 3 3 3
 * 4 4 4
 * 6 6
 * AAABBBB
 * AAAABBB
 * AAAAAABB
 * A 는 사용한 숫자 max 만큼
 * B 는 숫자 개수만큼.
 * A+B가 N 이하로, K 만들면 가능
 * 1 + 1
 * A - 1, B -2= 2
 * 5 8
 * 332,
 * 임의의 수 X 면, K / X + X 가 A,B 필요한 갯수
 * K/X == X 일때가 최소임. 결국 SQRT(K) 일때가 최소다.
 * 만들 수 있으면 AB 문자열 만들고, 남은 개수만큼 앞에 B를 붙여 문자열 S 만들기.
 * 불가능하면 -1 출력
 * 
 * 11이면, 4+4+3, A:4,B:3
 * AAABABB
 * 고, N이 7 이상이면 가능
 * 
 * K가 2면,
 * 2, A:2, B:1
 * AAB
 * 
 * K가 3이면,
 * 2+1, A:2, B:2
 */
import java.io.IOException;

public class Main {
    static int N, K;

    public static void main(String[] args) throws IOException {
        N = readInt();
        K = readInt();

        StringBuilder sb = new StringBuilder();

        // 총 a의 갯수
        int a = (int) Math.ceil(Math.sqrt(K));
        // 그 a 로 만들 b 개수
        int b = a > 0 ? K / a : 0;
        // 남은 숫자, 해당 수 만큼 먼저 A를 하고, B를 한다.
        int remain = K - a * b;
        if (remain > 0) {
            for (int i = 0; i < remain; i++) {
                sb.append('A');
                a--;
                N--;
            }
            sb.append('B');
            N--;
        }
        while (a-- > 0) {
            sb.append('A');
            N--;
        }
        while (b-- > 0) {
            sb.append('B');
            N--;
        }

        if (N < 0)
            System.out.println(-1);
        else {
            while (N-- > 0)
                System.out.print("B");
            System.out.println(sb);
        }
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}