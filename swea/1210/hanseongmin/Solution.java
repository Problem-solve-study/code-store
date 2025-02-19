import java.io.*;
import java.util.*;

/*
29696KB, 113ms

크게 복잡하지는 않은 구현, 시뮬레이션 문제
시간을 엄청 넉넉하게 줘서 제대로만 구현했다면 맞추게 하겠다는 의도로 보임
 */

public class Solution {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int[][] map;
    
    public static void main(String[] args) throws Exception {
    	StringBuilder sb = new StringBuilder();
    	for (int T = 1; T <= 10; T++) {
    		//이 문제는 다른 SWEA 문제와 다르게 테케의 번호를 먼저 줌
    		int t = nextInt();
    		map = new int[100][100];
    		//맵 입력
    		for (int i = 0; i < 100; i++) {
    			for (int j = 0; j < 100; j++) {
    				map[i][j] = nextInt();
    			}
    		}
    		
    		//모든 출발점을 확인해보며 도달 가능하다면 해당 출발점 출력
    		for (int i = 0; i < 100; i++) {
    			if (map[0][i] != 0 && simulation(i)) {
    				sb.append(String.format("#%d %d\n", t, i));
    				break;
    			}
    		}
    	}
    	
    	bw.write(sb.toString());
    	bw.flush();
    }
    
    static boolean simulation(int i) {
    	//(0, i)를 출발점으로 삼았을 때 목표 지점까지 도착 가능 여부를 출력
    	//같은 위치에 다시 방문하지 않기 위한 visited 배열
    	boolean[][] visited = new boolean[100][100];
    	int r = 0;
    	int c = i;
    	visited[r][c] = true;
    	
    	while (true) {
    		if (map[r][c] == 2) {
    			return true;
    		}
    		
    		if (c - 1 >= 0 && 
    				!visited[r][c - 1] && 
    				map[r][c - 1] != 0) {
    			//왼쪽으로 갈 수 있으면 왼쪽으로 감
    			c--;
    		} else if (c + 1 < 100 && 
    				!visited[r][c + 1] &&
    				map[r][c + 1] != 0) {
    			//오른쪽으로 갈 수 있으면 오른쪽으로 감
    			c++;
    		} else {
    			//그게 아니면 밑으로 이동
    			r++;
    			if (r == 100) {
    				//끝에 도달하면 종료
    				return false;
    			}
    		}
    		visited[r][c] = true;
    	}
    }
    
    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}