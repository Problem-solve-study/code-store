// 11828KB	808ms
import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static double answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = Integer.parseInt(br.readLine());
        for (int t=0; t<tc; t++) {
            n = Integer.parseInt(br.readLine());
            answer = 0;
            for (int i=1; i<=n; i++) {
                answer += Math.log10(i);
            }
            sb.append((int)answer+1).append("\n");
        }
        System.out.print(sb);
    }
}