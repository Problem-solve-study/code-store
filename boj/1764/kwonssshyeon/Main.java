
//	28744KB	320ms
import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        TreeSet<String> set1 = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            set1.add(br.readLine());
        }
        TreeSet<String> set2 = new TreeSet<>();
        for (int i = 0; i < m; i++) {
            set2.add(br.readLine());
        }
        StringBuilder sb = new StringBuilder();
        set1.retainAll(set2);
        sb.append(set1.size()).append("\n");
        for (String name : set1) {
            sb.append(name).append("\n");
        }
        System.out.print(sb);
    }
}