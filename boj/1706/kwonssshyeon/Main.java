// 11568KB	68ms

import java.io.*;
import java.util.*;

public class Main {
    static int n,m;
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<m; j++) {
                map[i][j] = line.charAt(j);
            }
        }
        System.out.print(calculate());
    }

    static String calculate() {
        ArrayList<String> words = new ArrayList<>();
        for (int i=0; i<n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<m; j++) {
                if (map[i][j] != '#') {
                    sb.append(map[i][j]);
                }
                else {
                    if (sb.length() > 1) words.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
            if (sb.length() > 1) words.add(sb.toString());
        }
        for (int j=0; j<m; j++) {
            StringBuilder sb = new StringBuilder();
            for (int i=0; i<n; i++) {
                if (map[i][j] != '#') {
                    sb.append(map[i][j]);
                }
                else {
                    if (sb.length() > 1) words.add(sb.toString());
                    sb = new StringBuilder();
                }
            }
            if (sb.length() > 1) words.add(sb.toString());
        }

        Collections.sort(words);
        return words.get(0);
    }
}