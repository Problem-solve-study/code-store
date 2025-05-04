//제출번호: 93900096
//메모리:   11556 KB
//실행시간: 68 ms
import java.io.*;

//우주선이 이동할 수 있는 방향은 3가지이므로 dp로 잘 풀 수 있음
//같은 방향으로 2번 연속 이동할 수 없으므로 dp 상태는 [이동방향][열]이 필요함
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int m = nextInt();
	    
	    int[][] prev = new int[3][m+2], cur = new int[3][m+2], swp;
		//if를 사용하지 않기 위해서 0과 m+1 col을 추가함
	    for (int i = 0; i < 3; i++) {
	        prev[i][0] = prev[i][m+1] = cur[i][0] = cur[i][m+1] = 1000;
	    }


	    for (int i = 0; i < n; i++) {
	        for (int j = 1; j <= m; j++) {
	            int cost = nextInt();
	            
				//3방향에 대해서
	            for (int k = 0, l = j-1; k < 3; k++, l++) {
					//서로 다른 방향에서 prev까지 온 결과 중 최솟값과 cost를 더한 값을 cur에 저장
	                cur[k][j] = Math.min(prev[(k+1)%3][l], prev[(k+2)%3][l]) + cost;
	            }
	        }
	        swp = cur; cur = prev; prev = swp;
	    }
	    
		//가장 마지막 행에 대해서 가장 작은 값을 찾아서 res에 저장 후 출력
	    int res = Integer.MAX_VALUE;
	    for (int j = 1; j <= m; j++) {
	        res = Math.min(Math.min(res, prev[0][j]), Math.min(prev[1][j], prev[2][j]));
	    }
	    
	    System.out.print(res);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}

/* n과 m이 매우 작아서 완탐으로도 풀어봄 - 신기하게 실행시간이 더 작음 (dp 코드 입출력 최적화 실행시간: 68ms)
//제출번호:	93900568
//메모리:	11448 KB
//실행시간:	64 ms
import java.io.*;

public class Main {
    
    static int[][] map;
    static int n, m;
    
	public static void main(String[] args) throws IOException {
	    n = nextInt();
	    m = nextInt();
	    
	    map = new int[n][m];
	    for (int i = 0; i < n; i++) {
	        for (int j = 0; j < m; j++) {
	            map[i][j] = nextInt();
	        }
	    }
	    
	    int res = 1000;
	    for (int i = 0; i < m; i++) {
	        res = Math.min(res, move(0, i, 2));
	    }
	    
	    System.out.print(res);
	}
	
	static int move(int row, int col, int dir) {
	    if (row == n) {
	        return 0;
	    }
	    
	    if (col < 0 || m <= col) {
	        return 1000;
	    }
	    
	    int res = 1000;
	    for (int nDir = -1; nDir < 2; nDir++) {
	        if (nDir != dir) {
	            res = Math.min(res, move(row + 1, col + nDir, nDir));
	        }
	    }
	    
	    return res + map[row][col];
	}
	
	static int nextInt() throws IOException {
        int n = System.in.read() & 15;
        int c = System.in.read();
        
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        
        return n;
	}
}
 */