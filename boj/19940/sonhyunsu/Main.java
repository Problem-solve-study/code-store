//제출번호: 92373609
//메모리:   11644 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//1 ~ 1천만까지 bfs를 돌리고, 들어오는 값마다 역추적으로 계산하니 시간 초과
//중간 중간의 배열을 HashMap으로 저장해서 재사용 하려고 하니 메모리 초과
//1 ~ 60까지만 bfs를 돌리고, 60 넘는 숫자는 60 이하가 될 때까지 ADDH만 누를 것이라 보고 계산함
//60 이하 수는 역추적으로 값을 계산했음 
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static byte[] type = new byte[61];
    static byte[] reverse = {0, 1, -1, 10, -10, -60};
    
	public static void main(String[] args) throws IOException {
	    Queue<Integer> q = new ArrayDeque<>();
	    
        //1 ~ 60까지는 BFS를 이용해서 최소 버튼 횟수를 구함
        //type은 누른 버튼의 종류임
	    q.add(0);
	    type[0] = 1;
	    while (!q.isEmpty()) {
	        int num = q.poll();
	        
	        if (num > 0 && type[num - 1] == 0) {
	            type[num - 1] = 1;
	            q.add(num - 1);
	        }
	        
	        if (num < 60 && type[num + 1] == 0) {
	            type[num + 1] = 2;
	            q.add(num + 1);
	        }
	        
	        if (num > 10 && type[num - 10] == 0) {
	            type[num - 10] = 3;
	            q.add(num - 10);
	        }
	        
	        if (num < 51 && type[num + 10] == 0) {
	            type[num + 10] = 4;
	            q.add(num + 10);
	        }
	        
	        if (num < 1 && type[num + 60] == 0) {
	            type[num + 60] = 5;
	            q.add(num + 60);
	        }
	    }
	    
        //재사용할 공간 생성
        int [] dp = new int[6];
	    int T = nextInt();
	    StringBuilder sb = new StringBuilder();
	    for (int t = 0; t < T; t++) {
	        int n = nextInt();
	        
            //공간 초기화
            for (int i = 5; i > 0; i--) {
                dp[i] = 0;
            }

            //만약 60이 넘는다면 ADDH를 넘는 만큼 누름
            if (n > 60) {
                dp[5] += n / 60;
                n %= 60;
            }

            //그리고 0분이 될 때까지 적절한 버튼을 누름
            while (n != 0) {
                dp[type[n]]++;
                n += reverse[type[n]];
            }

            //누른 버튼을 출력
	        for (int i = 5; i > 0; i--) {
	            sb.append(dp[i]).append(' ');
	        }
	        sb.append('\n');
	    }
	    
	    System.out.print(sb);
	}

	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}