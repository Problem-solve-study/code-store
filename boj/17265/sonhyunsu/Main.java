//제출번호: 92671773
//메모리:   11788 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//N이 매우 작기 때문에 완탐하면 풀 수 있음
//각 위치마다 숫자 또는 연산자 밖에 없기 때문에 다음을 탐색할 때 정보만 잘 넘기면 됨
//방향이 항상 오른쪽 아니면 아래쪽이기 때문에 금방 구할 수 있음
//맵에 0이 들어갈 수 있는데 처리를 제대로 안 해줬다가 1틀
public class Main {
    
    static int n;
    static int[][] map;
    static int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    n = Integer.parseInt(br.readLine());
	    map = new int[n][n];
	    
	    StringTokenizer st;
	    for (int i = 0; i < n; i++) {
	        st = new StringTokenizer(br.readLine());
	        for (int j = 0; j < n; j++) {
                //i j를 더했을 때 홀수면 연산자, 짝수면 숫자가 들어가 있음 
	            if ((i+j & 1) == 0) {
	                map[i][j] = Integer.parseInt(st.nextToken());
	            } else {
                    //연산자는 절대 들어올 수 없는 음수로 표현함
	                switch(st.nextToken().charAt(0)) {
	                    case '+': map[i][j] = -1; break;
	                    case '-': map[i][j] = -2; break;
	                    case '*': map[i][j] = -3; break;
 	                }
	            }
	        }
	    }
	    
        //dfs로 최대 최소를 계산하고 
	    dfs(0, 0, -1, 0);
	    
        //출력함
	    System.out.printf("%d %d", max, min);
	}
	
	static void dfs(int x, int y, int oper, int res) {
        //만약 맵에 들어가 있는 정보가 숫자라면
	    if (map[x][y] >= 0) {
            //직전에 들어온 연산자에 맞게 계산함
	        switch(oper) {
	            case -1: res += map[x][y]; break;
	            case -2: res -= map[x][y]; break;
	            case -3: res *= map[x][y]; break;
	        }
	    } else {
            //맵에 들어가 있는 정보가 연산자라면 연산자부분에 저장함
	        oper = map[x][y];
	    }
	    
        //그 후 맵 끝에 도달했다면 최대 최소를 갱신함
	    if (x == n - 1 && y == n - 1) {
	        max = Math.max(max, res);
	        min = Math.min(min, res);
	        return;
	    }
	    
        //아래쪽으로 갈 수 있다면 아래쪽으로 감
	    if (x < n - 1) {
	        dfs(x + 1, y, oper, res);
	    }
	    
        //오른쪽으로 갈 수 있다면 오른쪽으로 감
	    if (y < n - 1) {
	        dfs(x, y + 1, oper, res);
	    }
	}
}