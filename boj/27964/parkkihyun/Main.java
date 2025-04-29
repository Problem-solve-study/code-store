import java.io.*;
import java.util.*;

class Main {

    // 끝이 Cheese 로 끝나야 함

    public static void main(String[] args) throws Exception {
        int N = ni();
        if (N < 4) {
            System.out.println("sad");
            return;
        }

        HashSet<String> cheeses = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String topping = ns();

            if (isCheese(topping))
                cheeses.add(topping);
        }

        String ans = cheeses.size() >= 4 ? "yummy" : "sad";
        System.out.println(ans);
    }

    static boolean isCheese(String topping) {
        if (topping.length() < 6)
            return false;

        int len = topping.length();

        return topping.charAt(len - 1) == 'e' && topping.charAt(len - 2) == 's' && topping.charAt(len - 3) == 'e'
                && topping.charAt(len - 4) == 'e' && topping.charAt(len - 5) == 'h' && topping.charAt(len - 6) == 'C';
    }

    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tks;

    static String ns() throws Exception {
        if (tks == null || !tks.hasMoreTokens())
            tks = new StringTokenizer(br.readLine());
        return tks.nextToken();
    }

    static int ni() throws Exception {
        return Integer.parseInt(ns());
    }
}
