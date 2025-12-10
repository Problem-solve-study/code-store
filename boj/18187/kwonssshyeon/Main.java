import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      int n = Integer.parseInt(br.readLine());
      
      int[] lines = new int[3];
        int area = 1;

        for (int i = 0; i < n; i++) {
            area += 1 + lines[0] + lines[1] + lines[2] - lines[i % 3]++;
        }

        System.out.println(area);
    }
}