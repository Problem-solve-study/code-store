//제출번호: 94274582
//메모리:   13964 KB
//실행시간: 172 ms
import java.io.*;

//x = x1a, y = y1a로 두었을 때 x1과 y1은 서로소여야 한다
//x + y = (x1 + y1)a = b 이므로
//x1 + y1 = b / a이고, b / a는 정수여야 한다
//서로소를 만들기 위해서 x1을 1로 고정하면 y1 = b / a - 1이라는 식이 성립한다
//y1이 0이면 안 되는 데 체크하지 않아서 1틀
//y1이 0이면 a == b 이기 때문에 a, b 비교만 해주면 됨
public class Main {
    public static void main(String[] args) throws IOException {
        long q = nextLong();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            long a = nextLong();
            long b = nextLong();

            //b / a가 정수이면서 y1이 0이 아니라면 항상 조건을 만족하는 자연수 (x, y) 쌍이 존재함
            sb.append(b % a == 0 && b != a ? 1 : 0).append('\n');
        }
        System.out.print(sb);
    }

    static long nextLong() throws IOException {
        long n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}