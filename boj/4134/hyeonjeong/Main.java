// 12752KB 312ms

import java.util.*;
import java.io.*;

class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int t = (int) next();

        StringBuilder sb = new StringBuilder();
        for (; t > 0; t--) {
            long prime = findNextPrime(next());

            sb.append(prime).append('\n');
        }

        System.out.println(sb);
    }

    static long findNextPrime(long n) {
        if (n < 2) {
            n = 2;
        }

        while (true) {
            if (checkPrime(n)) {
                return n;
            }

            n++;
        }
    }

    static boolean checkPrime(long n) {
        for (long i = 2L; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    static long next() throws IOException {
        st.nextToken();
        return (long) st.nval;
    }
}
