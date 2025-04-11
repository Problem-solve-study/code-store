//제출번호: 92951159
//메모리:   17224 KB
//실행시간: 228 ms
import java.io.*;

//속도를 행성의 배수로 만들어야 하는데 앞에서 구하기는 어렵기 때문에 뒤에서부터 구해보기로 함
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
		int n = nextInt();

		int[] d = new int[n];
		for (int i = 0; i < n; i++) {
			d[i] = nextInt();
		}

        //도착점부터 거꾸로 가면서 속도를 계산한다.
		long speed = d[n - 1];
		for (int i = n - 2; i >= 0; i--) {
            //현재 속도가 이전 행성의 속도보다 작거나 같으면
            //이전 행성 속도 기준으로 그대로 오면 되기 때문애
            //그대로 저장한다.
			if (speed <= d[i]) {
				speed = d[i];
			} else {
                //현재 속도가 이전 행성의 속도보다 크다면
                //현재 속도를 올려서 이전 행성의 속도의 배수로 만들어야 한다.
				long speedMin = speed / d[i] * d[i];
				speed = speedMin + (speed != speedMin ? d[i] : 0);
			}
		}

        //계산된 속도 출력
		System.out.print(speed);
	}

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}