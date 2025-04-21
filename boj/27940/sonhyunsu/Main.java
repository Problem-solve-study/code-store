//제출번호: 93397347
//메모리:   16284 KB
//실행시간: 416 ms
import java.io.*;

//잘 보면 1층은 항상 비를 맞는 것을 알 수 있음
//그래서 1층에 쌓이는 빗물의 양을 확인하고, 임계점을 넘으면 1층이 무너짐
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        nextInt(); //총 몇 층인지 상관 없음. 1층은 항상 있고, 1층만 계산할 것임
        int m = nextInt();
        int k = nextInt();

        int sum = 0;
        for (int i = 1; i <= m; i++) {
            nextInt(); //몇 층인지는 상관 없음. 우리는 1층만 볼거임
            int b = nextInt();

            sum += b;
			//만약 빗물의 합이 임계점을 넘는다면
            if (sum > k) {
				//1층이 무너짐
                System.out.printf("%d %d", i, 1);
                break;
            }
        }

		//모든 빗물을 받아도 임계점을 넘지 않으면
		//아무 층도 무너지지 않음
        if (sum <= k) {
            System.out.print(-1);
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}