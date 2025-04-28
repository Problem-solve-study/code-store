// 	12804KB	136ms
import java.io.*;

/**
 * n에 도달하기 위해 전체 hp 와, 공격력을 누적 계산
 * 이때, 고릴라를 잡으면 공격을 받지 않기 때문에 공격횟수 == 공격당한횟수 + 1 이 되도록 조작
 */
public class Main {
    public static void main(String[] args) throws IOException {
        int n = readNum();
        long attack = readNum();

        long hp = 0, maxHP = 0;

        for (int i = 0; i < n; i++) {
            long t = readNum();
            long a = readNum();
            long h = readNum();

            if (t == 1) {
                long cnt = h / attack;
                if (h % attack != 0) {
                    cnt++;
                }
                hp += (cnt - 1) * a;
                maxHP = Math.max(maxHP, hp);
            } else {
                attack += a;
                hp = Math.max(0, hp - h);
            }
        }

        System.out.print(maxHP + 1);
    }

    static int readNum() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return n;
    }
}
