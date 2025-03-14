//제출번호: 91357523
//메모리:   39244 KB
//실행시간: 196 ms
import java.io.*;
import java.util.*;

//가장 먼저 떠오는 풀이가 BFS여서 BFS로 해결했음
//1부터 BFS 탐색해서 N까지 가는 방법을 계산함
//이 때 어느 숫자에서 어느 숫자로 갔는 지 기록해서
//역추적이 가능하도록 만듦
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static Queue<Integer> queue = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        int[] d = new int[n + 1];
        int[] prevNum = new int[n + 1];

        queue.add(1);
        while (!queue.isEmpty()) {
            int num = queue.poll();

            int pos;
            //num을 3배 한 값이 n보다 작거나 같으면서 아직 방문하지 않았다면
            if ((pos = num * 3) <= n && d[pos] == 0) {
                d[pos] = d[num] + 1;
                prevNum[pos] = num;
                queue.add(pos);
            }

            //num을 2배 한 값이 n보다 작거나 같으면서 아직 방문하지 않았다면
            if ((pos = num << 1) <= n && d[pos] == 0) {
                d[pos] = d[num] + 1;
                prevNum[pos] = num;
                queue.add(pos);
            }

            //num에 1 더한 값이 n보다 작거나 같으면서 아직 방문하지 않았다면
            if ((pos = num + 1) <= n && d[pos] == 0) {
                d[pos] = d[num] + 1;
                prevNum[pos] = num;
                queue.add(pos);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(d[n]).append('\n');
        
        int pos = n;
        //n이 1이 될 때까지 경로를 추적함
        while (pos != 0) {
            sb.append(pos).append(' ');
            pos = prevNum[pos];
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}