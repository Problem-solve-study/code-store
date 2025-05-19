//제출번호: 94181494
//메모리:   11784 KB
//실행시간: 68 ms
import java.io.*;

//그냥 포켓몬을 진화할 수 없을 때까지 반복하면서 문제에서 요구하는 내용을 계산하면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int res = 0, max = 0;
        String maxPokemon = null;
        for (int i = 0; i < n; i++) {
            //문제의 입력을 받음
            String name = nextString();
            int k = nextInt(), m = nextInt(), cnt = 0;

            //포켓몬을 더 이상 진화할 수 없을 때까지 반복
            while (m >= k) {
                m -= k;
                cnt++;
                m += 2;
            }

            //진화한 포켓몬의 수가 최대가 되면 갱신
            if (cnt > max) {
                max = cnt;
                maxPokemon = name;
            }

            res += cnt;
        }

        //총 진화한 포켓몬의 수와 가장 많이 진화한 포켓몬의 이름 출력
        System.out.printf("%d%n%s", res, maxPokemon);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}