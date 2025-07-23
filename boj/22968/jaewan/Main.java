
// 11460 KB, 64 ms
/*
 * 양의 정수 V가 주어지면, 최대 V개의 정점을 사용해서 만들 수 있는 AVL Tree의 최대 높이를 출력
 * 
 * dp[i] = 높이 i 인 AVL 트리를 만들기 위해 필요한 최소 정점 개수
 * dp[i] = dp[i - 1] + dp[i - 2] + 1
 * 
 * 왼쪽 서브트리 + 오른쪽 서브트리 + 루트 노드
 * 모두 높이 차이가 1 이하여야 함.
 * 모든 정점에 대해 왼쪽 서브트리와 오른쪽 서브 트리도 각각 AVL 트리여야 함.
 * 루트 노드 기준으로 왼쪽 서브트리와 오른쪽 서브트리의 높이 차이가 1이고,
 * 각각 해당 높이의 최소 개수 AVL 트리일 때, 해당 높이 최소 정점 AVL 트리에 필요한 정점 개수임.
 * 
 * 이진탐색 vs 순차탐색, 43 이라서 크게 차이 안남.
 * 테케 수가 많으면 다르겠지만 테케 수도 1000 개라서.
 */
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        int[] dp = new int[44];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i < 44; i++)
            dp[i] = dp[i - 1] + dp[i - 2] + 1;

        StringBuilder sb = new StringBuilder();
        int T = readInt();
        for (int i = 0; i < T; i++) {
            int t = readInt();
            for (int j = 2; j <= 43; j++) {
                if (dp[j] > t) {
                    sb.append(j - 1).append('\n');
                    break;
                }
            }
        }

        System.out.println(sb);
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}