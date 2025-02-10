// 11516KB	68ms
import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long[] f = new long[n+1];
        f[0] = 1;

        for (int i=1; i<=n; i++) {
            for (int j=0; j<i; j++) {
                f[i] += f[j]*f[i-j-1];
            }
        }
        System.out.print(f[n]);
    }
}