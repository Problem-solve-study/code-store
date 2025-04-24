//제출번호: 93514218
//메모리:   15632 KB
//실행시간: 172 ms
import java.io.*;

//처음에 문제를 잘못 읽어서 1틀
//보니까 N명을 모두 만나기 위해 학교에 머물러야 하는 최소 시간이길래
//가장 빨리 떠나는 팬과 가장 나중에 학교에 방문하는 팬만 알면 구할 수 있을 것 같았음
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int sMax = 0, eMin = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            sMax = Math.max(sMax, nextInt()); //가장 나중에 오는 팬의 시간을 구함
            eMin = Math.min(eMin, nextInt()); //가장 먼저 떠나는 팬의 시간을 구함
        }

		//두 시간의 차이를 구할 때, 음수가 나오지 않게 함
        System.out.print(Math.max(0, sMax - eMin));
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}