//제출번호: 92085078
//메모리:   27000 KB
//실행시간: 188 ms
import java.io.*;

//처음 보자마자 소수는 다른 색깔로 색칠하고,
//합성수는 인수분해 했을 때 나오는 소수의 색깔 중 아무거나 색칠하면 되겠다고 생각함
//에라토스테네스의 체를 이용해서 소수를 구하고, 합성수에 색칠하는 과정까지 한 번에 처리
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();

        int[] colors = new int[n + 1];
        int colorCnt = 0;
        int res = n; //서로 다른 색깔의 개수
        
        //1은 소수도, 합성수도 아니지만 색칠해야 하기 때문에 저장
        colors[1] = ++colorCnt;
        
        //에라토스테네스의 체 소수 & 합성수 판정은 i * i <= n 까지만 해도 충분
        for (int i = 2; i * i <= n; i++) {
            //만약 i가 소수라면
            if (colors[i] == 0) {
                //새로운 색을 색칠함
                colors[i] = ++colorCnt;

                //합성수를 탐색
                for (int j = i * i; j <= n; j += i) {
                    //j가 i로 나누어 떨어지는 합성수이면서 아직 색칠되지 않았다면
                    if (colors[j] == 0) {
                        //i의 색깔로 색칠하고 res에 1을 뺌 (같은 색깔로 칠했기 때문)
                        colors[j] = colors[i];
                        res--;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(res).append('\n');
        for (int i = 1; i <= n; i++) {
            //아직 색칠되지 않았다면 소수이므로 새로운 색깔을 색칠함
            if (colors[i] == 0) {
                colors[i] = ++colorCnt;
            }
            sb.append(colors[i]).append(' ');
        }
        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}