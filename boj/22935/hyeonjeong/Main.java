// 12900KB 88ms

import java.util.*;
import java.io.*;

class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int t = next();

        String[] answers = new String[30];
        for (int i = 0; i < 16; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 3; j >= 0; j--) {
                if ((i & (1 << j)) > 0) {
                    sb.append("딸기");
                } else {
                    sb.append("V");
                }
            }
            answers[i] = sb.toString();
        }
        for (int i = 16; i < 30; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 3; j >= 0; j--) {
                if (((15 - (i - 15)) & (1 << j)) > 0) {
                    sb.append("딸기");
                } else {
                    sb.append("V");
                }
            }
            answers[i] = sb.toString();
        }

        // System.out.println(Arrays.toString(answers));
        
        StringBuilder sb = new StringBuilder();
        for (; t > 0; t--) {
            int n = next();

            int i = n;
            while (i > 29) {
                i = i - 29 + 1;
            }
            sb.append(answers[i]).append('\n');
        }
        System.out.println(sb);
    }

    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
