import java.io.*;

/*
11584KB, 832ms

BigInteger로 나이브하게 계산한 후 자릿수를 보면 되나 싶었는데 최댓값이 좀 커서 메모리나 시간이나 부족할 것 같았다.
상용로그를 이용하면 특정 수의 자릿수를 알 수 있으므로 상용 로그를 쓰는 방향으로 선회

log10(n!) = x라고 할 때 답은 floor(x) + 1.
이때 log10(n!) = log10(n) + log10(n - 1) + log10(n - 2) + ... + log10(2) + log10(1)이므로
이를 통해 값을 계산

중복 연산이 발생할 것 같아 먼저 모든 값을 계산한 후 저장해서 쓸랬는데 최대 값이 천만 -> 메모리 초과
그냥 바로 계산하도록 고친 후 자릿수 계산을 잘못해서 1틀
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int t = nextInt();
        StringBuilder sb = new StringBuilder();
        while (t --> 0) {
            int num = nextInt();
            double res = 0;
            for (int i = 1; i <= num; i++) {
                res += Math.log10(i);
            }
            sb.append((int) Math.floor(res) + 1).append('\n');
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}