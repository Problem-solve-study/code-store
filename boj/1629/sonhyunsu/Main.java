//제출번호: 92770974
//메모리:   11512 KB
//실행시간: 68 ms
import java.io.*;

//분할정복을 이용해서 a^b %c를 잘 구하면 됨
//재귀함수 만들기 귀찮아서 비재귀로 작성함
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		long a = nextInt();
		long b = nextInt();
		long c = nextInt();

		long mem = a % c;
		long res = 1;

		while (b > 0) {
            //0번째 bit가 1이면 곱해야 하는 위치
			if ((b & 1) == 1) {
                //res에 곱한 결과 저장
				res = res * mem % c;
			}
            //mem은 제곱함 2^0 -> 2^1 -> 2^2 -> 2^3 ... 제곱한 효과가 나옴
			mem = mem * mem % c;
            //b는 / 2해서 1번째 bit를 0번째 bit로 옮김 
			b >>>= 1; 
		}

		System.out.print(res);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}