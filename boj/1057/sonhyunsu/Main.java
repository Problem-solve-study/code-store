//제출번호: 93977988
//메모리:   11528 KB
//실행시간: 68 ms
import java.io.*;

//토너먼트가 진행될 때마다 번호가 절반씩 줄어드는 것을 확인할 수 있음
//절반씩 줄이다가 두 번호가 같아지는 순간이 서로 대결하는 순간이 됨 
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        nextInt();
        int a = nextInt()-1;
        int b = nextInt()-1;

        int res = 0;
        while (a != b) {
            a >>= 1;
            b >>= 1;
            res++;
        }

        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}