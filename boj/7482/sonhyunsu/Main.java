//제출번호: 93947389
//메모리:   11692 KB
//실행시간: 64 ms
import java.io.*;

//알고리즘 태그로 미적분학을 볼 줄은 몰랐는데..
//(a-2b)*(a-2b)*b가 최대가 되는 b를 찾기
//위 식은 3차함수이므로 극댓값을 찾으면 됨
//전개하면 a²b - 4ab² + 4b³, 미분하면 a² - 8ab + 12b²
//근의 공식을 사용하면 (+8a +- sqrt(64a² - 48a²)) / 24 = (8a +- 4a) / 24 = a/6 or a/2
//따라서 b가 a/6 일 때 부피가 최대가 됨 (a/2 일 때 극솟값을 가짐)
//소수점 아래 10자리를 무조건 출력해야 하는 점을 주의할 것
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = (int) nextDouble();
	    
	    StringBuilder sb = new StringBuilder();
	    for (int i = 0; i < n; i++) {
	        sb.append(String.format("%.10f", nextDouble()/6)).append('\n');
	    }
	    System.out.print(sb);
	}
	
	static double nextDouble() throws IOException {
	    st.nextToken();
	    return st.nval;
	}
}