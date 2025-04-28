// 	33236KB	428ms
import java.io.*;

public class Main {
    static int n, m, v, map[];
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        n = nextInt();
        m = nextInt();
        v = nextInt() - 1;
        map = new int[n];
        for (int i=0; i<n; i++) {
            map[i] = nextInt();
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<m; i++) {
            int k = nextInt();
            if (k < n) {
                sb.append(map[k]).append("\n");
            }
            else {
                // 사이클이 생기지 않는 부분을 제외하고 (k - v)
                // 남은 부분에 대해 사이클 크기 만큼 모듈러 연산 ((k - v) % (n - v))
                // 처음에 빼줬던 v 만큼 다시 더해줌 (+ v)
                int value = (k - v) % (n - v) + v; 
                sb.append(map[value]).append("\n");
            }
        }
        System.out.print(sb);
    }

    static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}