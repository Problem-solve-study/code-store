
/*
 * 36진법 수.
 * 2 <= A <= 36, 2 <= B <= 36, A != B
 * 두 수를 보여주면, X, A, B를 계산.
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    static int lenA, lenB, resA, resB;
    static long res;
    static int[] numA, numB;
    static BigInteger max = BigInteger.valueOf((1L << 63) - 1);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        lenA = input[0].length();
        lenB = input[1].length();
        numA = new int[lenA];
        numB = new int[lenB];

        int maxA = 0, maxB = 0;

        for (int i = 0; i < lenA; i++) {
            char t = input[0].charAt(i);
            numA[i] = t >= 'a' ? t - 'a' + 10 : t - '0';
            maxA = Math.max(maxA, numA[i]);
        }
        for (int i = 0; i < lenB; i++) {
            char t = input[1].charAt(i);
            numB[i] = t >= 'a' ? t - 'a' + 10 : t - '0';
            maxB = Math.max(maxB, numB[i]);
        }

        int cnt = 0;
        for (int i = 36; i > maxA; i--)
            for (int j = 36; j > maxB; j--) {
                if (i == j)
                    continue;
                if (func(i, j))
                    cnt++;
            }

        switch (cnt) {
            case 0:
                System.out.println("Impossible");
                break;
            case 1:
                System.out.println(res + " " + resA + " " + resB);
                break;
            default:
                System.out.println("Multiple");
                break;
        }
    }

    // A진법인 numA 를 10진법, B진법인 numB를 10진법으로 변환해 일치하는지 검사
    static boolean func(int A, int B) {
        BigInteger a = BigInteger.valueOf(numA[0]);
        for (int i = 1; i < lenA; i++) {
            a = a.multiply(BigInteger.valueOf(A));
            a = a.add(BigInteger.valueOf(numA[i]));
        }
        if (a.compareTo(max) > 0)
            return false;

        BigInteger b = BigInteger.valueOf(numB[0]);
        for (int i = 1; i < lenB; i++) {
            b = b.multiply(BigInteger.valueOf(B));
            b = b.add(BigInteger.valueOf(numB[i]));
        }
        if (b.compareTo(max) > 0)
            return false;

        if (a.compareTo(b) == 0) {
            resA = A;
            resB = B;
            res = a.longValue();
            return true;
        }
        return false;
    }
}
