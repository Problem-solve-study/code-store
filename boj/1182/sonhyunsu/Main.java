//제출번호: 91304346
//메모리:   11536 KB
//실행시간: 76 ms
import java.io.*;

//부분집합을 이용해서 합이 S인 경우의 수를 세어주면 됨
//부분수열이 연속해야 하는줄 알고 짰다가 1번 틀림
//아무 것도 고르지 않았을 때 0이 나올 수 있으므로 이거 배제해야 함
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[] d;
    static int n, s, res;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        s = nextInt();
        d = new int[n];

        for (int i = 0; i < n; i++) {
            d[i] = nextInt();
        }

        subset(0, 0, false);

        System.out.print(res);
    }

    static void subset(int idx, int sum, boolean isLeastOneSelected) {
        if (idx == n) {
            //합이 s이면서 적어도 1개 이상 골랐다면 정답임
            if (sum == s && isLeastOneSelected) {
                res++;
            }

            return;
        }

        // 현재 idx의 값을 선택하거나 선택하지 않는 경우를 계산함
        subset(idx + 1, sum + d[idx], true);
        subset(idx + 1, sum, isLeastOneSelected);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}