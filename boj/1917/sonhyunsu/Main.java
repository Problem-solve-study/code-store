//제출번호: 93371040
//메모리:   11548 KB
//실행시간: 64 ms
import java.io.*;

//전개도를 직접 접어보는 깡구현을 요구하길래 진짜로 전개도를 접어보면서 구현함
//전개도는 아래와 같이 고정하고, 각 숫자의 상하좌우에 어떤 눈이 어느 방향을 가리키고 있는 지 저장했음
/*
5 1 6
  2
  3
  4
*/
//방향 기록 하나 잘못해서 1틀 

public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[][] map = new int[6][6];
	static boolean[] isFilled = new boolean[7];
    static int[] dx = {-1, 0, 1, 0}, dy = {0, -1, 0, 1}; //상 좌 하 우

	//1번부터 6번까지 상좌하우에 어떤 눈이 있고,
	//현재 눈이 상을 가리킨다고 가정할 때, 인접한 눈의 머리는 어느 방향을 바라보고 있는 지 기록했음 (상대 방향을 기록)
	//5번 눈, 6번 눈을 기록할 때 머리 깨지는 줄...
    static int[][][] diceDir = {
        {},
        { {4, 0}, {5, 0}, {2, 0}, {6, 0} }, //1
        { {1, 0}, {5, 1}, {3, 0}, {6, 3} }, //2
        { {2, 0}, {5, 2}, {4, 0}, {6, 2} }, //3
        { {3, 0}, {5, 3}, {1, 0}, {6, 1} }, //4
        { {4, 1}, {3, 2}, {2, 3}, {1, 0} }, //5
        { {4, 3}, {1, 0}, {2, 1}, {3, 2} }  //6
    };
    
    
	public static void main(String[] args) throws IOException {
	    StringBuilder sb = new StringBuilder();
	    for (int t = 0; t < 3; t++) {
	        for (int i = 0; i < 6; i++) {
	            for (int j = 0; j < 6; j++) {
	                map[i][j] = nextInt() - 1; //빈칸은 -1, 주사위 블록은 0이 되도록 만듦
	            }
	        }
	        
			//1번 눈부터 6번 눈까지 사용하지 않았다고 설정
	        for (int i = 1; i < 7; i++) {
	            isFilled[i] = false;
	        }
	        
	        boolean res = false;
	        find:
	        for (int i = 0; i < 6; i++) {
	            for (int j = 0; j < 6; j++) {
					//가장 처음 만난 주사위 블록에 1번 눈을 위쪽을 바라보도록 배치한 후 나머지 눈을 dfs로 배치해봄
	                if (map[i][j] == 0) {
	                    res = check(i, j, 1, 0);
	                    break find;
	                }    
	            }
	        }
	        
			//만약 모든 눈을 배치할 수 있다면 yes, 없다면 no
	        sb.append(res ? "yes" : "no").append('\n');
	    }
	    
	    System.out.print(sb);
	}
	
	//num번 눈이 절대 방향으로 dir 방향을 가리길 때 다음 눈을 배치하는 dfs
	//절대 방향은 맵을 내가 봤을 때 가리키는 방향(맵 기준 상좌하우)을 의미함
	static boolean check(int x, int y, int num, int dir) {
		//만약에 이번에 배치해야 하는 숫자가 이미 다른 곳에 배치된 적이 있다면 불가능한 전개도임
	    if (isFilled[num]) {
	        return false;
	    }
	    
		//이번 위치에 num번 눈을 저장하고, num번 눈을 사용했다고 기록함
	    map[x][y] = num;
	    isFilled[num] = true;
	    
	    boolean res = true;
	    
		// (i + dir) % 4는 절대 방향을 계산하는 코드
		// i 는 상대 방향
	    for (int i = 0; i < 4; i++) {
			//상대 방향 기준으로 현재 눈의 상 좌 하 우 순으로 탐색
			//좌표는 절대 방향 기준으로 계산
	        int nx = x + dx[(i + dir) % 4];
	        int ny = y + dy[(i + dir) % 4];
	        
			//다음 좌표가 맵 안에 있고, 아직 눈이 배치되지 주사위 블록이라면 
	        if (0 <= nx && nx < 6 && 0 <= ny && ny < 6 && map[nx][ny] == 0) {
				//num 눈을 기준으로 i 방향에 와야할 눈과 그 눈이 바라보는 상대 방향을 구함
	            int[] next = diceDir[num][i];
				
				//다음 눈을 배치할 때 불가능하면 res에 반영
				//다음 눈과 그 눈의 절대 방향을 다음 dfs에 넘겨줌
	            res &= check(nx, ny, next[0], (next[1] + dir) % 4);
	        }
	    }
	    
	    return res;
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}