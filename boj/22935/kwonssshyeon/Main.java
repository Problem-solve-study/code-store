import java.io.*;

public class Main {
    static String ZERO = "V";
    static String ONE = "딸기";

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int n = Integer.parseInt(br.readLine());
            int k = (n - 1) % 28;
            int value;
            if (k < 15) {
                value = k + 1;
            } else {
                value = 29 - k;
            }
            simulate(sb, toBinaryString(value));
            sb.append('\n');
        }
        System.out.print(sb);
    }

    static void simulate(StringBuilder sb, String str) {
        for (char c : str.toCharArray()) {
            sb.append(c == '0' ? ZERO : ONE);
        }
    }

    static String toBinaryString(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(num % 2);
            num /= 2;
        }
        while (sb.length() < 4) {
            sb.append("0");
        }
        return sb.reverse().toString();
    }
}
