// 26760KB	220ms
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int max = 0, num = 0;
        long sum = 0;
        for (int i=0; i<n; i++) {
            num = Integer.parseInt(st.nextToken());
            sum += num;
            max = Math.max(max, num);
        }
        sum -= max;
        if (sum + 1 >= max) {
            sum += max;
        } else {
            sum = 2 * sum + 1;
        }
        System.out.print(sum);
    }
}