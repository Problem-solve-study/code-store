//제출번호: 90319662
//메모리:   31980 KB
//실행시간: 140 ms
import java.io.*;
import java.util.*;

//쉬운 bfs 문제, up과 down 시 범위 벗어나지 않도록 주의
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 32768);
    static StreamTokenizer st = new StreamTokenizer(br);

    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        int f = nextInt();
        int s = nextInt();
        int g = nextInt();
        int u = nextInt();
        int d = nextInt();
        int data[] = new int[f + 1];

        data[s] = 1;
        queue.add(s);
        while (!queue.isEmpty()) {
            int floor = queue.poll();

            if (floor + u <= f && data[floor + u] == 0) {
                data[floor + u] = data[floor] + 1;
                queue.add(floor + u);
            }

            if (floor - d > 0 && data[floor - d] == 0) {
                data[floor - d] = data[floor] + 1;
                queue.add(floor - d);
            }
        }

        System.out.print(data[g] == 0 ? "use the stairs" : data[g] - 1);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}