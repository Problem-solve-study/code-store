//제출번호:	93129333
//메모리:	11532 KB
//실행시간:	68 ms
import java.io.*;

//관찰을 통해서 dp식을 찾음
//그림판에 올려놓고 시계방향으로 회전하면서 인덱스를 매겨본 뒤
//d[i] = d[i-1] + d[i-5]인 것을 찾음
//일반항이 안 되는 항은 초기값을 넣어주고, dp식으로 나머지 값을 찾음
//100번째 수열 값은 int 범위 넘어감을 주의 (1틀함)
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        long[] d = new long[101];

		//초기 값을 주고
        d[1] = 1;
        d[2] = 1;
        d[3] = 1;
        d[4] = 2;

		//파도반 수열을 구함
        for (int i = 5; i < 101; i++) {
            d[i] = d[i - 1] + d[i - 5];
        }

        int T = nextInt();
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; t++) {
            sb.append(d[nextInt()]).append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}