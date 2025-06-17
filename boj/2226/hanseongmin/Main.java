import java.io.*;
import java.math.BigInteger;

/*
11892KB, 72ms

문제에서 주어진 예제에서 딱 하나만 더 적어봄. 그럼 아래와 같은 꼴이 되는데
1
01
1001
01101001
1001011001101001

그냥 여기서 바로 규칙이 보임. 이전의 값을 a라고 하고 a의 값에서 비트 반전시킨 값을 a'이라고 했을 때
현재의 값은 a'과 a를 이어붙인 수열임

즉 현재의 연속된 0의 그룹은 이전값에서 가지고 있던 연속된 0의 그룹을 그대로 가져올 것이고
비트가 반전된 값이 그 앞에 따라붙으니 자연스럽게 연속된 0의 그룹 뿐만 아니라 연속된 1의 그룹 또한 관리해줘야 함
(왜냐면 이전 값에서 연속된 1의 그룹은 비트가 반전되면서 현재 값에서 연속된 0의 그룹이 될테니)

그럼 이제 a'과 a가 붙여지는 순간에서 0과 0이 붙어 00의 꼴로 나타내지는 경우만 체크해주면 되는데
각 값에서 나타나지는 MSB는 스왑되는 형태이니 i % 2의 값을 적절히 살펴보고 1을 빼주면 됨

연속된 1의 그룹의 경우 a의 LSB가 항상 1로 고정되므로 a'의 값은 항상 0으로 고정되게 되고
11의 꼴이 나오는 경우가 없기 때문에 1을 빼줄 필요가 없다.

값이 굉장히 크게 나오기 때문에 BigInteger 사용
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        BigInteger[][] dp = new BigInteger[1001][2];
        dp[1][0] = BigInteger.ZERO; dp[1][1] = BigInteger.ONE;
        dp[2][0] = dp[2][1] = BigInteger.ONE;
        for (int i = 3; i <= N; i++) {
            dp[i][0] = dp[i][1] = dp[i - 1][0].add(dp[i - 1][1]);
            if (i % 2 == 1) dp[i][0] = dp[i][0].subtract(BigInteger.ONE);
        }
        System.out.print(dp[N][0]);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
