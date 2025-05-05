//제출번호: 93926603
//메모리:   12960 KB
//실행시간: 72 ms
import java.io.*;

//처음에는 정렬해서 인접한 구슬의 차이의 합 + 양 끝의 구슬 차이의 합이 정답이라고 생각했음
//그런데 다시 보니까 가장 큰 구슬과 가장 작은 구슬의 차이를 2배한 값이랑 위의 값이랑 동일한 것을 확인함
//잘 보면 줄의 길이는 구슬의 차이로 구해지는데, 가장 큰 구슬과 가장 작은 구슬로 팔찌를 만들고, 나머지 구슬을 적당히 쏙쏙 넣으면
//조건에 맞는, 줄의 최소 길이를 가진 팔찌를 만들 수 있음
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int max = 0;
	    int min = (int) 1e9;
	    
	    for (int i = 0; i < n; i++) {
	        int bead = nextInt();
	        max = max < bead ? bead : max; //최대
	        min = min > bead ? bead : min; //최소
	    }
	    
	    System.out.print((max - min) << 1); //(최대 - 최소) * 2 출력
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}