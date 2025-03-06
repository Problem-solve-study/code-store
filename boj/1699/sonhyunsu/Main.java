
//제출번호: 90938788
//메모리:   12008 KB
//실행시간: 104 ms
import java.io.*;

//전형적인 dp문제, 가장 작은 제곱수부터 큰 제곱수까지, n을 만들 수 있는 최소횟수를 업데이트 함
//그리디로 접근하면 18 = 16 + 1 + 1이지만 실제로는 9 + 9임, 따라서 그리디로 풀 수 없음

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int[] d = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            // dp를 정답으로 나올 수 없는 최대값으로 초기화
            d[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i * i <= n; i++) {
            // 제곱수를 계산함
            int square = i * i;
            // 제곱수 위치는 항상 1이 가장 작음
            d[square] = 1;

            // 현재 제곱수 다음 값부터 n까지 현재 제곱수를 이용해서 만들 수 있는 최소 횟수를 업데이트 함
            for (int j = square + 1; j <= n; j++) {
                d[j] = Math.min(d[j], d[j - square] + 1);
            }
        }

        System.out.print(d[n]);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}