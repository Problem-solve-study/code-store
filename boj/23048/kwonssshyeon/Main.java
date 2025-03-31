
// 	29128KB	248ms
import java.io.*;
import java.util.*;

public class Main {
    static int n, num[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = new int[n + 1];
        int count = 2;
        num[1] = 1;
        for (int i = 2; i <= n; i++) {
            if (num[i] == 0) {
                int cursor = i;
                while (cursor <= n) {
                    num[cursor] = count;
                    cursor += i;
                }
                count++;
            }
        }
        System.out.println(count - 1);
        print();
    }

    static void print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(num[i]).append(" ");
        }
        System.out.print(sb);
    }
}
