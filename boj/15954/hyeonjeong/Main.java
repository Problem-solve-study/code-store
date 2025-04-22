// 13952KB 148ms

import java.io.*;
import java.util.*;

/*
 * 그냥 완탐
 */
class Main {
    static final StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    static int n;
    static int k;
    static int[] count;

    static double min = 1_000_000.0;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        k = nextInt();

        count = new int[n];
        for (int i = 0; i < n; i++) {
            count[i] = nextInt();
        }

        for (int start = 0; start <= n - k; start++) {
            search(start);
        }

        System.out.print(min);
    }

    static void search(int start) {
        int sum = 0;
        for (int i = start; i < start + k - 1; i++) {
            sum += count[i];
        }

        for (int size = k; start + size <= n; size++) {
            sum += count[start + size - 1];
            double result = getStdDsv(start, size, sum);

            if (result < min) {
                min = result;
            }
        }

    }

    static double getStdDsv(int start, int size, int sum) {
        double avg = sum * 1.0 / size;

        double sumForDev = 0.0;
        for (int i = start; i < start + size; i++) {
            sumForDev += (count[i] - avg) * (count[i] - avg);
        }
        double v = sumForDev / size;

        return Math.sqrt(v);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
