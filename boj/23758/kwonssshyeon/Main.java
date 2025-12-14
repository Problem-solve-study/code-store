import java.io.*;
import java.util.*;

public class Main {
    static int[] num;

    public static void main(String[] args) throws Exception {
        int n = ni();
        num = new int[n];
        for (int i=0; i<n; i++) {
            num[i] = ni();
        }
        Arrays.sort(num);

        int size = (n + 1) / 2 - 1;
        
        int answer = 1;
        for (int i=0; i<=size; i++) {
            int temp = 63 - Long.numberOfLeadingZeros(num[i]);
            answer += temp;
        }
        System.out.print(answer);
    }

    static int ni() throws Exception {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
