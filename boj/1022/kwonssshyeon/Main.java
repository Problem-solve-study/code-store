// 	12108KB	84ms
import java.io.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int y1 = nextInt();
        int x1 = nextInt();
        int y2 = nextInt();
        int x2 = nextInt();
        StringBuilder sb = new StringBuilder();
        int max = 0;
        for (int i : new int[] {y1, y2}) {
            for (int j : new int[] {x1, x2}) {
                max = Math.max(max, calc(i, j));
            }
        }
        String length = "%"+String.valueOf(max).length()+"d";
        for (int i=y1; i<=y2; i++) {
            for (int j=x1; j<=x2; j++) {
                sb.append(String.format(length, calc(i, j))).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.print(sb);
    }

    static int calc(int i, int j) {
        int n = Math.max(Math.abs(i), Math.abs(j));
        int pow = (2 * n - 1) * (2 * n - 1);
        if (n == i) {
            return pow + 7 * n + j;
        }
        else if (n == -i) {
            return pow + 3 * n - j;
        } 
        else if (n == j) {
            return pow + n - i;
        }
        else {
            return pow + 5 * n + i;
        }
    }


    static int nextInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}