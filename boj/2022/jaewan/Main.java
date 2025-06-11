import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

        double a = nextDouble(st), b = nextDouble(st), c = nextDouble(st);
        double left = 0, right = Math.min(a, b), mid, h1, h2, x;

        while (right - left >= 0.0001) {
            mid = (left + right) / 2;
            h1 = Math.sqrt(Math.pow(a, 2) - Math.pow(mid, 2));
            h2 = Math.sqrt(Math.pow(b, 2) - Math.pow(mid, 2));
            x = (h1 * h2) / (h1 + h2);

            if (x > c)
                left = mid;
            else
                right = mid;
        }

        System.out.printf("%.3f", left);
    }

    static double nextDouble(StreamTokenizer st) throws IOException {
        st.nextToken();
        return st.nval;
    }
}