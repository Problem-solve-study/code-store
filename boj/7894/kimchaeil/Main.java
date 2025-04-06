//문제: BOJ 7894번 큰 수
//메모리: 89664 KB
//시간: 380 ms

/*
 * 임의의 수 n의 자리수는 floor(log_{10}(n))+1
 * n! = n*(n-1)*(n-2)*...*2*1
 * log(n!) = log(n)+log(n-1)+...+log(2)
 * log(n!)의 값을 저장하는 배열을 만든다
 * 입력으로 주어지는 n들의 최댓값을 미리 알지 못하니
 * 문제에서 주어진 1e7까지 미리 계산한다
 * 입력을 받으면서 미리 계산한 값을 floor하여 출력한다
 */

import java.io.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        int test_case = nextInt();
        double[] arr = new double[(int) 1e7 + 1];
        arr[1] = 1;
        for (int i = 2; i <= 1e7; i++)
            arr[i] = arr[i - 1] + Math.log10(i);

        while (test_case-- > 0)
            sb.append((int) arr[nextInt()]).append('\n');

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
