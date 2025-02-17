//제출번호: 90177070
//메모리:   59288 KB
//실행시간: 988 ms
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

//복잡한 시뮬레이션, n이 10억으로 크기 때문에 패턴을 찾아야 한다.
public class Main {

	static Queue<Pos> queue = new ArrayDeque<>();
	static int dx[] = {1, -1, 0, 0}, dy[] = {0, 0, 1, -1};
	
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		int r = scan.nextInt();
		int c = scan.nextInt();
		int n = scan.nextInt();
		scan.nextLine();
		
		int board[][] = new int[r][c];
		
		for (int i = 0; i < r; i++) {
			String line = scan.nextLine();
			for (int j = 0; j < line.length(); j++) {
				board[i][j] = line.charAt(j) == 'O' ? 0 : -1; //지뢰라면 현재 시각을, 아니면 -1을 저장한다.
				if (board[i][j] == 0) {
					queue.add(new Pos(i, j)); //지뢰가 설치되면 그 좌표를 기록한다.
				}
			}
		}
		
		//실제로 돌려 보면 4가지 패턴이 반복되는 것을 알 수 있음
		//그래서 n을 4로 나눈 나머지를 이용함
		int state = n % 4;
		if (state == 0) { //0번 상태는 4번 상태와 같지 않으므로 4번 상태를 만들기 위해 업데이트 한다.
			state = 4;
		}
		
        //1번 상태와 5번 상태는 다른 경우가 있을 수 있으므로 5번 상태까지 계산한다.
		if (n % 4 == 1 && n > 4) {
			state = 5;
		}
		
		int mineCount = 0;
		for (int i = 2; i <= state; i++) {
			if (i%2 == 0) {
				mineCount = queue.size(); //3초 후에 터질 지뢰의 개수를 미리 세어둔다
				for (int x = 0; x < r; x++) {
					for (int y = 0; y < c; y++) {
						//만약에 지뢰가 설치되어 있지 않다면
						//지뢰를 설치하고 좌표를 기록한다.
						if (board[x][y] == -1) {
							board[x][y] = i;
							queue.add(new Pos(x, y));
						}
					}
				}
			} else {
				//진짜로 터져야 하는 지뢰의 개수만큼만 반복한다
				for (int j = 0; j < mineCount; j++) {
					Pos p = queue.poll();
					
					//만약에 지금 있는 지뢰가 3초 전에 설치된 지뢰라면
					if (board[p.x][p.y] == i - 3){
						
						//나를 포함해 5개의 지뢰를 없앤다.
						for (int d = 0; d < 4; d++) {
							int nx = p.x + dx[d];
							int ny = p.y + dy[d];
							
							if (isInRange(r, c, nx, ny)) {
								//이 때 지금 삭제할 지뢰가 3초전 지뢰라면 삭제하지 않고 (터트리기 위해)
								//1초전 지뢰일 때만 없앤다.
								if (board[nx][ny] != i - 3) {
									board[nx][ny] = -1;
								}
							}
						}
						board[p.x][p.y] = -1; 
					}
				}
			}
		}
		
		//n초 후의 상태를 출력한다.
		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				System.out.printf("%c", board[x][y] >= 0 ? 'O' : '.');
			}
			System.out.println();
		}
		
		scan.close();
	}
	
	static class Pos {
		int x;
		int y;
		
		Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static boolean isInRange(int r, int c, int x, int y) {
		return 0 <= x && x < r && 0 <= y && y < c;
	}

}
