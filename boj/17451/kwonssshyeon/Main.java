
// 	16928KB	224ms
import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n, map[];

    public static void main(String[] args) throws IOException {
        n = nextInt();
        map = new int[n];
        for (int i = 0; i < n; i++) {
            map[i] = nextInt();
        }

        long answer = 0L;
        for (int i = n - 1; i >= 0; i--) {
            // 이전 행성이 더 큰 경우 속도를 줄여서 오면 됨
            if (answer < map[i]) {
                answer = map[i];
            }
            // 이전 행성이 더 작은 경우 배수관계면 그냥 갈 수 있지만
            // 배수 관계가 아니면 이전행성의 배수중 현재 행성보다 큰 속도로 와야함.
            else if (answer > map[i] && (answer % map[i] != 0)) {
                answer = (answer / map[i] + 1) * map[i];
            }
        }
        System.out.println(answer);
    }

    // fast i/o
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
