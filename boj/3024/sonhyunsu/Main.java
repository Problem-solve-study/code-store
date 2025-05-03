//제출번호: 93859412
//메모리:   11508 KB
//실행시간: 64 ms
import java.io.*;

//모든 보드를 살펴보면서 승리한 사람이 있는지만 확인하면 됨
//현재 위치를 좌/상단으로 보고 우/하단으로 가면서 같은 문자가 3개 있는지 확인
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int n = Integer.parseInt(br.readLine());
	    char[][] map = new char[n][];
	    for (int i = 0; i < n; i++) {
	        map[i] = br.readLine().toCharArray();
	    }
	    
	    char win = '.';
	    int[] dx = {0, 1, 1, 1}, dy = {1, 0, 1, -1}; //좌->우, 상->하, 좌상->우하, 우상->좌하
	    
	    find:
	    for (int x = 0; x < n; x++) {
	        for (int y = 0; y < n; y++) {
				//현재 위치에 적은 플레이어가 있다면
	            if (map[x][y] != '.') {
	                char player = map[x][y];
	                
					//4방향을 탐색하면서 우승했는 지 확인
	                for (int i = 0; i < 4; i++) {
	                    int cnt = 1;

						//주변 2개까지 확인하면서
	                    for (int j = 0, nx = x + dx[i], ny = y + dy[i]; j < 2; j++, nx += dx[i], ny += dy[i]) {
							//맵 안에 있고, 그곳에 적힌 문자가 플레이어 것이라면
	                        if (0 <= nx && nx < n && 0 <= ny && ny < n && map[nx][ny] == player) {
	                            cnt++; //cnt에 1을 더함
	                        } else {
	                            break; //아니라면 더 이상 볼 필요 없음
	                        }
	                    }
	                    
						//만약 cnt가 3이라면 연속으로 같은 문자가 3개 있는 것이므로 승자를 찾은 것
	                    if (cnt == 3) {
	                        win = player;
	                        break find;
	                    }
	                }
	            }
	        }
	    }
	    
		//승자를 찾았다면 승자의 문자를, 못 찾았다면 ongoing 출력
	    System.out.print(win == '.' ? "ongoing" : win);
	}
}