
// 13240 KB, 88ms
/*
 * 1부터 N까지의 수로 이루어짐.
 * 문자열의 길이로 N을 알 수 있음.
 * N length
 * 1 1
 * 2 2
 * ...
 * 9 9
 * 10 11
 * 11 13
 * 12 15
 * ...
 * 50 91
 * 
 * 숫자 나왔을때, 한자리수, 또는 두자리 숫자로 처리할 수 있음.
 * 숫자
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int len, N;
    static String inStr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        inStr = br.readLine();
        len = inStr.length();
        N = len < 10 ? len : (len + 9) >> 1;
        func(new StringBuilder(), 0, 0);
    }

    static boolean func(StringBuilder sb, long select, int idx) {
        if (idx == len) {
            System.out.println(sb.toString());
            return true;
        }

        int num = inStr.charAt(idx) & 15;
        if (num == 0)
            return false;
        // 한자리 수로 처리
        if (num <= N && (select & ((long) 1 << num)) == 0) {
            sb.append(num).append(' ');
            if (func(sb, select ^ ((long) 1 << num), idx + 1))
                return true;
            sb.setLength(sb.length() - 2);
        }

        // 두자리 숫자로 처리
        if (++idx >= len)
            return false;
        num = (num << 3) + (num << 1) + (inStr.charAt(idx) & 15);

        if (num <= N && (select & ((long) 1 << num)) == 0) {
            sb.append(num).append(' ');
            if (func(sb, select ^ ((long) 1 << num), idx + 1))
                return true;
            sb.setLength(sb.length() - 3);
        }
        return false;
    }
}