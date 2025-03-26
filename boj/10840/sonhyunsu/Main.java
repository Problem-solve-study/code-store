import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static Set<int[]> keywords = new TreeSet<>((k1, k2) -> {
        if (k1[0] == k2[0]) {
            if (k1[1] == k2[1]) {
                return Integer.compare(k1[2], k2[2]);
            }
            return Integer.compare(k1[1], k2[1]);
        }
        return Integer.compare(k1[0], k2[0]);
    });

    public static void main(String[] args) throws IOException {
        int[] prime = {2, 3, 5, 7, 11, 13, 17, 19, 23, 27, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
        int mod1 = (int) 1e6 + 3;
        int mod2 = (int) 1e6 + 33;
        String a = br.readLine();
        String b = br.readLine();

        int aLen = a.length();
        for (int i = 0; i < aLen; i++) {
            int hash1 = 1;
            int hash2 = 1;

            for (int j = i; j < aLen; j++) {
                int idx = a.charAt(j) - 'a';
                hash1 = (hash1 * prime[idx]) % mod1;
                hash2 = (hash2 * prime[idx]) % mod2;

                keywords.add(new int[]{hash1, hash2, j - i + 1});
            }
        }

        int res = 0;
        int bLen = b.length();
        int[] arr = new int[3];
        for (int i = 0; i < bLen; i++) {
            int hash1 = 1;
            int hash2 = 1;

            for (int j = i; j < bLen; j++) {
                int idx = b.charAt(j) - 'a';
                hash1 = (hash1 * prime[idx]) % mod1;
                hash2 = (hash2 * prime[idx]) % mod2;

                arr[0] = hash1;
                arr[1] = hash2;
                arr[2] = j - i + 1;

                if (keywords.contains(arr)) {
                    res = Math.max(res, arr[2]);
                }
            }
        }

        System.out.print(res);
    }
}