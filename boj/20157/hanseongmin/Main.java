import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
    //기약 분수(풍선의 기울기)를 저장
    public static class Fraction {
        int numerator;
        int denominator;

        //x, y의 순서로 값이 들어오므로 인자는 반대로
        //부호를 그대로 저장해야만 n사분면에 위치함을 제대로 판별 가능
        public Fraction(int denominator, int numerator) {
            int gcd = BigInteger.valueOf(numerator).gcd(BigInteger.valueOf(denominator)).intValue();
            this.numerator = numerator / gcd;
            this.denominator = denominator / gcd;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Fraction fraction = (Fraction) o;
            return numerator == fraction.numerator && denominator == fraction.denominator;
        }

        @Override
        public int hashCode() {
            return Objects.hash(numerator, denominator);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int res = -1;
        int n = Integer.parseInt(br.readLine());
        HashMap<Fraction, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            Fraction fraction = new Fraction(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            map.put(fraction, map.getOrDefault(fraction, 0) + 1);
            res = Math.max(res, map.get(fraction));
        }

        bw.write(String.valueOf(res));
        bw.flush();
    }
}