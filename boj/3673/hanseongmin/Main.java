import java.io.*;

/*
42528KB, 284ms

누적합의 모듈러 값이 몇 번 나왔는지를 담고있는 배열을 선언하고 입력값을 받으며 카운팅
현재 모듈러 누적합의 값이 2라면 그 전에 2가 나온 경우를 빼주면 나눌 수 있는 부분 수열이 된다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int c = nextInt();
        StringBuilder sb = new StringBuilder();
        while (c --> 0) {
            int d = nextInt(); int n = nextInt();
            int[] arr = new int[d]; arr[0] = 1;
            int sum = 0;
            long res = 0;
            for (int i = 0; i < n; i++) {
                sum = (sum + nextInt()) % d;
                res += arr[sum]++;
            }
            sb.append(res).append('\n');
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
