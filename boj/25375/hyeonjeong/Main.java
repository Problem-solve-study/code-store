// 46268KB 360ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// x와 y의 최대공약수가 a
// x = k(a의 배수) & y = a -> x = k의 배수 + a & y = k -> ...
// => x + y는 2*a가 넘는 a의 배수
class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int q = Integer.parseInt(br.readLine());
        for (; q > 0; q--) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());

            if (b < 2 * a || b % a != 0) {
                sb.append("0\n");
                continue;
            }
            sb.append("1\n");
        }

        System.out.println(sb);
    }
}
