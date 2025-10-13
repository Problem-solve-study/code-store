/*
 * 0 1 0 1 ... idx & 1한거
 * 1 0 1 0 ... idx ^ 1 한거
 * 
 * 연속된 일치하는 구간 세서 그 길이를 저장.
 * 
 * 그리고 연속된 세 수를 더한 최댓값이 구하려는 정답
 * 
 * 구간 길이 하나 구하면 갱신
 * 구간을 전부 갖고 있는것? 100,000 개라서 별로 없을 것 같긴 한데.
 * 
 * 일단 이렇게 하고 나중에 비교해보자.
 */

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt(), num1 = 0, num2 = 0;
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int t = readInt();
            if (t == (i & 1)) {
                num1++;
                if (num2 > 0) {
                    list.add(num2);
                    num2 = 0;
                }
            } else {
                num2++;
                if (num1 > 0) {
                    list.add(num1);
                    num1 = 0;
                }
            }
        }

        if (num1 > 0)
            list.add(num1);
        else if (num2 > 0)
            list.add(num2);

        int max = 0;
        if (list.size() < 4)
            for (Integer t : list)
                max += t;
        else {
            int t = list.get(0) + list.get(1) + list.get(2);
            max = Math.max(max, t);
            for (int i = 3; i < list.size(); i++) {
                t += list.get(i);
                t -= list.get(i - 3);
                max = Math.max(max, t);
            }
        }
        System.out.println(max);
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