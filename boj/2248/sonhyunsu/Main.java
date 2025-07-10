//제출번호: 96158905
//메모리:   11432 KB
//실행시간: 60 ms
import java.io.*;

//가장 왼쪽에 있는 비트를 0 또는 1로 설정했을 때
//남은 비트로 만들 수 있는 경우의 수를 확인
//가능한 이유는 가장 왼쪽에 있는 비트를 1로 설정하면
//가장 왼쪽 비트가 0이면서 나머지 오른쪽에서 무슨 수를 만들든 항상 작기 때문임

//i보다 만들 수 있는 경우의 수가 더 작다면 비트는 반드시 1이어야 하고
//i보다 만들 수 있는 경우의 수가 더 크다면 비트는 반드시 0이어야 함
//경우의 수는 조합으로 구함
public class Main {
    public static void main(String[] args) throws IOException {
        int n = (int) next(), l = (int) next();
        long i = next();

        int[][] comb = new int[32][32];
        for (int a = 0; a < 32; a++) {
            comb[a][0] = 1;
            for (int b = 1; b <= a; b++) {
                comb[a][b] = comb[a-1][b-1] + comb[a-1][b];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int a = n-1; a >= 0; a--) {
            int s = 0;
            for (int b = 0; b <= l; b++) {
                s += comb[a][b];
            }

            if (i > s) {
                i -= s;
                l--;
                sb.append(1);
            } else {
                sb.append(0);
            }
        }

        System.out.print(sb);
    }

    static long next() throws IOException {
        long n = System.in.read() & 15;
        int c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}