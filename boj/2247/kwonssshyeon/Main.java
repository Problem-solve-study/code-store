// 11684KB	576ms
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i=2; i<=n/2; i++) {
            int temp = ((n / i - 1) * i) % 1_000_000;
            answer = (answer + temp) % 1_000_000;
        }
        System.out.println(answer);
    }
}
