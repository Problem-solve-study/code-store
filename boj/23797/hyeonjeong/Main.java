// 22680KB 156ms

import java.util.*;
import java.io.*;

// 최소 개구리의 수: K 이전에 P로 끝난, P 이전에 K로 끝난 개구리가 더이상 없을 때
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String sing = br.readLine();

        int count = 0;
        int k = 0;
        int p = 0;

        for (char type : sing.toCharArray()) {
            if (type == 'K') {
                k++;
                p--;
                if (p < 0) {
                    p++;
                    count++;
                }
                continue;
            }

            k--;
            p++;
            if (k < 0) {
                k++;
                count++;
            }
        }

        System.out.print(count)
    }
}
