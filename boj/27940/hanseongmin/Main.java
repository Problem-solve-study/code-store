import java.io.*;
import java.util.*;

/*
 * 15884KB, 420ms
 * 
 * 모든 칸이 버틸 수 있는 빗물의 양이 동일하고 1번째 칸은 어떤 경우에도 빗물을 받으므로
 * 최초로 무너지는 경우 반드시 1번은 무너지게 된다.
 * 입력을 받으며 1번이 무너지는 것을 확인하고 무너질 때 시간만 구하면 됨
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int M = nextInt();
        int K = nextInt();
        
        int time = 0;
        int sum = 0;
        for (int i = 1; i <= M; i++) {
            nextInt();
            sum += nextInt();
            if (sum > K) {
                time = i;
                break;
            }
        }
        
        if (sum > K) {
            System.out.print(time + " " + 1);
        } else {
            System.out.print(-1);
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}