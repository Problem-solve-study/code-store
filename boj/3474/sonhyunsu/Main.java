//제출번호: 91565714
//메모리:   21684
//실행시간: 224
import java.io.*;

//주어진 N!에서 곱해지는 2의 개수와 곱해지는 5의 개수 중 더 작은 값을 고르면 됨
//하지만 곱해지는 5의 개수가 항상 더 작으므로 5의 개수만 세면 충분함
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int T = nextInt();
        StringBuilder sb = new StringBuilder();

        for (int t = 0; t < T; t++) {
            int n = nextInt();
            int five = 0;
            
            //N!을 소인수분해 했을 때 5가 몇 개 있는 지 세어봄
            while (n > 0) {
                five += n /= 5;
            }

            sb.append(five).append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}