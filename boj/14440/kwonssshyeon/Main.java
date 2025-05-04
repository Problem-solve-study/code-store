//	11776KB	72ms
import java.io.*;
import java.util.*;

/**
 * 일단, 입력 크기랑 출력 형식을 보고 연산할 때 long을 사용해야한다고 판단.
 * 상수가 같은 곱셈을 1억번 이상하기 때문에 분할정복을 이용한 거듭제곱을 고려
 * 일반항에서 상수가 x, y 2개라서 행렬을 이용
 * long 도 오버플로네요 ... 행렬 곱 연산하는 중간중간 %100 (1틀)
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int x,y,n;
    static int[] a = new int[2];

    public static void main(String[] args) throws IOException {
        x = nextInt();
        y = nextInt();
        a[0] = nextInt();
        a[1] = nextInt();
        n = nextInt();

        long[][] result = powerMatrix();
        long answer = a[1] * result[0][0] + a[0] * result[0][1];
        System.out.printf("%02d", answer % 100);
    }

    static long[][] powerMatrix() {
        long[][] base = new long[][] {{x, y}, {1, 0}};
        long[][] result = new long[][] {{1, 0}, {0, 1}};
        n -= 1;
        
        while (n > 0) {
            if (n % 2 == 1) {
                result = multMatrix(result, base);
            }
            base = multMatrix(base, base);
            n /= 2;
        }
        return result;
    }

    static long[][] multMatrix(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        for (int i=0; i<2; i++) {
            for (int j=0; j<2; j++) {
                long sum = 0L;
                for (int k=0; k<2; k++) {
                    sum += (a[i][k] * b[k][j]) % 100;
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
