import java.io.*;

/*
12220KB, 128ms

수를 쭉 나열해봐도 딱히 규칙성이 안보임 -> 상수를 깎는 브루트포스로 해보자. 될까?
수를 나열해봤을 때 생각보다 안수빈수가 빠르게 나오는 것을 확인 -> 브루트포스로 되나?
이때 몇몇 수는 꽤나 큰 수를 곱해야해서 적당히 n부터 곱하도록 하고 브루트포스를 돌림
-> 뚫림 -> ..?
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < t; i++) {
            long n = Long.parseLong(br.readLine());
            for (long j = n; true; j++) {
                if (is안수빈수(n * j)) {
                    sb.append(n * j).append('\n');
                    break;
                }
            }
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static boolean is안수빈수(long n) {
        long result = 0;
        while(n > 0) {
            result += n % 10;
            n /= 10;
        }

        return (result & 1) == 1;
    }
}