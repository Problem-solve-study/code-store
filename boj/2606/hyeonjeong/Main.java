// 11452kb 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static int[] idx;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        idx = new int[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }

        for (int i = 0; i < m; i++) {
            String[] edge = br.readLine().split(" ");
            union(Integer.parseInt(edge[0])-1, Integer.parseInt(edge[1])-1);
        }

        System.out.println(Arrays.toString(idx));

        int rootZero = find(0);
        int count = 0;
        for (int i = 1; i < n; i++) {
            if (find(i) == rootZero) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static int find(int x) {
        while (x != idx[x]) {
            x = idx[x];
        }

        return x;
    }

    public static void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        idx[yRoot] = xRoot;
    }
}