/**
 * 25752KB	292ms
 * 
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> set = new HashSet<>();
        List<String> res = new ArrayList<>();

        for (int i=0; i<N; ++i) {
            set.add(br.readLine());
        }
        for (int i=0; i<M; ++i) {
            String cur = br.readLine();
            if (set.contains(cur)) res.add(cur);
        }
        Collections.sort(res);
        System.out.println(res.size());
        for (String s: res) System.out.println(s);
    }
}