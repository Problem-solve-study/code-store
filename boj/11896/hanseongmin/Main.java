import java.io.*;

/*
11524KB, 68ms

열심히 그림판으로 그려봤는데 N이 짝수일 때만 가능한거 같고 홀수일때는 아무리 해봐도 안되는 것 같았다.
1, 2각형은 존재하지 않고, 문제의 조건을 만족하려면 오목다각형이여야할텐데 삼각형은 오목다각형이 불가능하므로
4부터 시작하여 짝수들의 합을 구하면 되겠다 싶었다.

1 1, 1 2와 같은 꼴의 입력이 들어왔을 때 0이 아니라 음수가 나와버려서 1틀
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        long a = nextInt();
        long b = nextInt();
        long res = getSum(b % 2 == 0 ? b : b - 1) - getSum(a % 2 == 0 ? a - 2 : a - 1);
        if (a <= 2) res -= 2;
        System.out.print(Math.max(res, 0));
    }

    static long getSum(long end) {
        long n = (end / 2) + 1;
        return n * end / 2;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
