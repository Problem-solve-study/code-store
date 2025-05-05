//제출번호: 93927980
//메모리:   11604 KB
//실행시간: 68 ms
import java.io.*;

//단순 구현 문제인데 시간복잡도가 10^9인 점을 생각하지도 않아서 1틀 - 덕분에 재귀 코드 갈아 엎었음
//반드시 꺼져야 하는 전구들만 확인하면 만들 수 있는 숫자를 알 수 있음 
public class Main {
    
    static char[][] apart;
    static long[] sum;
    static int[] cnts;
    static int n;

    //숫자를 만들기 위해서 반드시 꺼져야 하는 전구의 위치
    //{1, 1}과 {3, 1}은 모든 숫자가 꺼저 있어야 하므로 따로 처리했음
    static int[][][] needOff = {
      {{2, 1}},
      {{0, 0}, {0, 1}, {1, 0}, {2, 0}, {2, 1}, {3, 0}, {4, 0}, {4, 1}},
      {{1, 0}, {3, 2}},
      {{1, 0}, {3, 0}},
      {{0, 1}, {3, 0}, {4, 0}, {4, 1}},
      {{1, 2}, {3, 0}},
      {{1, 2}},
      {{1, 0}, {2, 0}, {2, 1}, {3, 0}, {4, 0}, {4, 1}},
      {},
      {{3, 0}}
    };
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    n = Integer.parseInt(br.readLine());
	    apart = new char[5][];
	    for (int i = 0; i < 5; i++) {
	        apart[i] = br.readLine().toCharArray();
	    }
	    
	    sum = new long[n + 1];
	    cnts = new int[n + 1];
	    
        //가장 왼쪽에 있는 안내판을 계산하기 위해서 10^(n-1)을 계산함
	    long mul = 1;
	    for (int i = 1; i < n; i++) {
	        mul *= 10;
	    }
	    
	    find(0, mul);

	    if (cnts[0] == 0) {
            //만약 만들 수 있는 숫자가 하나도 없으면 -1
	        System.out.print(-1);
	    } else {
            //만들 수 있는 숫자가 있다면 계산한 결과 출력
	        System.out.print(1.0 * sum[0] / cnts[0]);
	    }
	}
	
    //혹시 쓸까해서 int로 반환했는데 안 썼음..
	static int find(int idx, long mul) {
	    if (idx == n) {
	        return cnts[idx] = 1;
	    }
	    
        //자릿수의 시작 col을 계산
	    int col = idx << 2;

        //공통적으로 {1, 1}, {3, 1}에 전구가 켜져 있으면 아무런 숫자도 만들 수 없음
	    if (apart[1][col+1] == '#' || apart[3][col+1] == '#') {
	        return 0;
	    }
	    
        //다음 자릿수를 먼저 계산함
	    find(idx + 1, mul / 10);
	    
	    for (int i = 0; i < 10; i++) {
	        boolean canMake = true;
	        for (int[] relativePos : needOff[i]) {
	            canMake &= apart[relativePos[0]][col + relativePos[1]] != '#';
	        }
	        
            //i 숫자를 만들기 위해서 꺼져야 하는 전구들이 모두 꺼져 있다면 i 숫자를 만들 수 있음
	        if (canMake) {
	            sum[idx] += i * mul; //지금 자릿수로 만들어지는 실제 숫자를 sum에 더함
	            cnts[idx]++; //개수도 세어둠
	        }
	    }
	    
        //sum[idx]는 (다음 자릿수부터 마지막 자릿수를 이용해서 만들 수 있는 숫자의 개수)만큼 늘어나고,
        //sum[idx + 1]은 (지금 만들 수 있는 숫자의 개수)만큼 늘어남
	    sum[idx] = sum[idx] * cnts[idx + 1] + sum[idx + 1] * cnts[idx];

        //지금부터 마지막 자릿수를 이용해서 만들 수 있는 숫자의 개수는 아래 식으로 계산할 수 있음
        cnts[idx] *= cnts[idx + 1];
	    
	    return cnts[idx];
	}
}