//제출번호: 95067618
//메모리:   11324 KB
//실행시간: 64 ms

//리프노드에서 루트까지 최단거리로 이동할 때 지나가는 분기점의 개수을 x라고 할 때, x의 최댓값을 max라고 하자
//2^max만큼의 원숭이들이 나무 꼭대기 까지 갈 수 있다는 것을 볼 수 있다
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    StringBuilder sb = new StringBuilder();
	    int t = Integer.parseInt(br.readLine());
	    while (t-- > 0) {
	        int max = 0, top = 0;
	        for (char c : br.readLine().toCharArray()) {
	            top += c == '[' ? 1 : -1;
	            max = Math.max(max, top);
	        }
	        
	        sb.append(1 << max).append('\n');
	    }
	    
	    System.out.print(sb);
	}
}