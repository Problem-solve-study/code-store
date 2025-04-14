
// 39020 KB, 192 ms
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int c = readInt();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < c; i++) {
            int d = readInt(), n = readInt(), sum = 0, res = 0;
            int[] mod = new int[d];
            mod[sum]++;
            for (int j = 0; j < n; j++) {
                sum = (sum + readInt()) % d;
                res += mod[sum]++;
            }
            sb.append(res).append('\n');
        }
        System.out.println(sb);
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;

    }
}