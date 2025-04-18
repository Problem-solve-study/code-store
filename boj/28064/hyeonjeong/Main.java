// 26236KB 132ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/** 
 * 100 * 100 * 20x 정도? 완탐
 */
class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);
    
    public static void main(String[] args) throws IOException {
        int n = nextInt();
        boolean[][] matrix = new boolean[n][n];
        String[] names = new String[n];

        for (int i = 0; i < n; i++) {
            String name = nextString();
            names[i] = name;
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < names[j].length(); k++) {
                    String subName = names[j].substring(k);
                    if (subName.length() > names[i].length()) {
                        continue;
                    }
                    
                    if (names[i].startsWith(subName)) {
                        matrix[i][j] = true;
                        break;
                    }
                }

                if (matrix[i][j]) {
                    continue;
                }

                for (int k = 0; k < names[i].length(); k++) {
                    String subName = names[i].substring(k);
                    if (subName.length() > names[j].length()) {
                        continue;
                    }
                    
                    if (names[j].startsWith(subName)) {
                        matrix[j][i] = true;
                        break;
                    }
                }
            }
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (matrix[i][j] || matrix[j][i]) {
                    count++;
                }
            }
        }

        System.out.println(count);
    }

    static String nextString() throws IOException {
        return br.readLine();
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
