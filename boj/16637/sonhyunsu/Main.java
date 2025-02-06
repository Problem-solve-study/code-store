//제출번호: 89643604
//메모리:   14272 KB
//실행시간: 108 ms
import java.io.*;

public class Main {

    static int[] num = new int[10];
    static char[] oper = new char[9];
    static int np;
    static int op;
    static boolean[] doBracket = new boolean[9]; 
    static int res = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        br.readLine();
        for (char c : br.readLine().toCharArray()) {
            switch (c) {
                case '+': case '-': case '*': oper[op++] = c; break;
                default: num[np++] = c - '0';
            }
        }

        f(0);
        System.out.print(res);
    }
    public static int calc(int a, int b, char o) {
        switch(o) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
        };
        
        return 0;
    }

    public static void f(int k) {
        if (k >= op) {
            int r = num[0];
            for (int i = 0; i < op; i++) {
                if (i + 1 < op && doBracket[i + 1]) {
                    r = calc(r, calc(num[i+1], num[i+2], oper[i+1]), oper[i]);
                    i++;
                } else {
                    r = calc(r, num[i+1], oper[i]);
                }
            }

            res = Math.max(res, r);
            return;
        }

        f(op);
        for (int i = k; i < op; i++) {
            if (!doBracket[i]) {
                doBracket[i] = true;
                f(k + 2);
                doBracket[i] = false;
            }
        }
    }
}