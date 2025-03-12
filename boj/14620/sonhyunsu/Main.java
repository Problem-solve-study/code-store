//제출번호: 91189554
//메모리:   13328 KB
//실행시간: 80 ms
import java.io.*;

//꽃이 겹치면 안 되고, 씨앗 3개를 심으니까, 그것을 만족하는 값만 찾으면 되는 문제
//꽃을 심기 위해 필요한 땅을 딱 한 번만 구하기 위해 sum을 이용해서 저장
//최대 64C3만으로 최소값을 구할 수 있음, 겹치지 않게만 고르면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[][] selected = new int[3][2];
    static int n, sn;
    static int[][] sum;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        int[][] ground = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ground[i][j] = nextInt();
            }
        }

        //sum에 십자의 합을 저장해둠
        sum = new int[n][n];
        sn = n - 1;
        for (int i = 1; i < sn; i++) {
            for (int j = 1; j < sn; j++) {
                sum[i][j] = ground[i][j] + ground[i - 1][j] + ground[i + 1][j] + ground[i][j - 1] + ground[i][j + 1];
            }
        }

        //3개를 골라봄
        select(0, n + 1);
        System.out.print(min);
    }

    static void select(int cnt, int pos) {
        if (cnt == 3) {
            int val = 0;
            for (int i = 0; i < 3; i++) {
                val += sum[selected[i][0]][selected[i][1]];
            }

            min = Math.min(min, val);
            return;
        }

        //x,y를 1차원으로 압축하고, pos의 끝을 계산함.
        int end = n * (n - 1) - 1 - (2 - cnt);
        comb:
        for (int nPos = pos; nPos < end; nPos++) {
            int x = nPos / n;
            int y = nPos % n;

            //모서리는 꽃을 심을 수 없으므로 바로 건너뜀
            if (y == 0 || y == sn) {
                continue;
            }

            //만약 지금 선택한 위치가 꽃이 겹치는 위치라면 새로운 위치를 고름
            //중심을 기준으로 3이상 떨어져 있으면 반드시 겹치지 않음
            for (int i = 0; i < cnt; i++) {
                if (Math.abs(selected[i][0] - x) + Math.abs(selected[i][1] - y) < 3) {
                    continue comb;
                }
            }

            //지금 선택한 위치를 다음 위치를 선택했을 때 겹치는 지 판단하기 위해서 저장함
            selected[cnt][0] = x;
            selected[cnt][1] = y;

            select(cnt + 1, nPos + 1);
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}