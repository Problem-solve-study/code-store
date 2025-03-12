
//제출번호: 91188047
//메모리:   15812 KB
//실행시간: 176 ms
import java.io.*;

//아이디어를 떠오르기만 하면 풀 수 있는 그리디? 애드혹? 문제
//가장 많이 이용할 수 있는 놀이기구(K번)를 선택하면 K번을 연속으로 이용하지 않게 만들기 위해서
//나머지 놀이기구들로 K-1 개를 채워야 한다. (K번의 사이사이 빈칸의 개수가 K-1개)
//이 때 나머지 놀이기구들로 K-1개를 채우지 못 한다면, 나머지 놀이기구들의 개수의 합(N번(을 일렬로 나열하고
//양 끝을 포함해 가장 많이 이용할 수 있는 놀이기구를 사이사이에 넣어 연속되지 않도록 만들 수 있다. (빈칸의 개수가 N + 1개)
//이 때 정답은 (N + N + 1)이 된다.
//만약에 나머지 놀이기구들로 K-1개를 채울 수 있다면, K-1개를 적절하게 배치해 K번이 연속되지 않게 만들고
//남은 놀이기구 이용 횟수는 항상 연속되지 않게 임의로 배치할 수 있으므로
//정답은 놀이기구의 이용 횟수의 총합이 된다.(K+N)
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        long sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int a = nextInt();
            sum += a;
            max = Math.max(max, a);
        }

        sum -= max;
        if (sum < max) {
            System.out.print((sum << 1) + 1);
        } else {
            System.out.print(sum + max);
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}

/* 최적화 버전
//제출번호: 91188280
//메모리:   12176 KB
//실행시간: 100 ms
import java.io.*;
import java.util.*;

//*2를 << 1로, + 1을  | 1로 구현했음
// 항상 * 2 이후 1을 더하기 때문에 0번째 비트는 OR 하기 전에 항상 0임
public class Main {
    public static void main(String[] args) throws IOException {
        int n = nextInt();

        long sum = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int a = nextInt();
            sum += a;
            max = Math.max(max, a);
        }

        if (sum < (max << 1)) {
            System.out.print((sum - max << 1) | 1);
        } else {
            System.out.print(sum);
        }
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15;
        int c = System.in.read();
        
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }

        return n;
    }
}
*/