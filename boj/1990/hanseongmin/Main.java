import java.io.*;
import java.math.BigInteger;

/*
260692KB, 812ms

순수하게 다 보는 수밖에 없다고 생각했음.
나이브하게 브루트포스로 구해보니 마지막 소수 팰린드롬이 9989899까지밖에 없다.
따라서 반복을 9989899까지만 수행하며(나름대로 런타임 전의 전처리?) 소수인 팰린드롬을 구해줬다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int a = ni(); int b = ni();
        StringBuilder sb = new StringBuilder();
        //반복은 9989899까지
        for (int i = a; i <= Math.min(b, 9989899); i++) {
            //짝수인 경우에는 절대 소수일 수 없으므로 넘어감
            if (i % 2 == 0) continue;
            //팰린드롬이면서 소수라면 추가
            if (isPal(i) && BigInteger.valueOf(i).isProbablePrime(10)) {
                sb.append(i).append('\n');
            }
        }
        sb.append(-1);
        System.out.print(sb);
    }

    static boolean isPal(int a) {
        String s = String.valueOf(a);
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
