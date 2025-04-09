//제출번호: 92830271
//메모리:   330012 KB
//실행시간: 1248 ms
import java.io.*;
import java.util.*;

//그냥 방향을 포함한 3차원 bfs로 구현
//dnm = 4 * 2000 * 2000으로 시간초과는 나지 않을 것이라고 생각함
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int r = nextInt();
		int c = nextInt();

		int[][] map = new int[r][c];
		boolean[][][] visited = new boolean[4][r][c];
		boolean[][] calced = new boolean[r][c];
		Queue<int[]> q = new ArrayDeque<>();
		int res = 0;

		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				int num = map[i][j] = nextInt();
                //에어컨이면 큐에 4방향 모두 넣어줌
				if (num == 9) {
					for (int d = 0; d < 4; d++) {
						q.add(new int[]{d, i, j});
						visited[d][i][j] = true;
					}

                    //에어컨 위치는 정답에 더했다는 정보를 남김
					calced[i][j] = true;
					res++;
				}
			}
		}

		int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};
        //각 물건에 대해서 바뀌는 방향을 미리 저장함
		int[][] outDir = {{0, 1, 2, 3}, {0, 3, 2, 1}, {2, 1, 0, 3}, {3, 2, 1, 0}, {1, 0, 3, 2}};

		while (!q.isEmpty()) {
			int[] item = q.poll();

			int d = item[0];
			int x = item[1];
			int y = item[2];

            //다음 위치를 계산해서
			int nx = x + dx[d];
			int ny = y + dy[d];

            //맵 안이면서 에어컨이 아닌지 확인한다.
			if (0 <= nx && nx < r && 0 <= ny && ny < c && map[nx][ny] != 9) {
                //그 위치로 갔을 때 바람의 바뀌는 방향을 찾고
				int nd = outDir[map[nx][ny]][d];

                //이미 탐색했는지 확인한다.
				if (!visited[nd][nx][ny]) {

                    //아직 가지 않았다면 큐에 넣음
					visited[nd][nx][ny] = true;
					q.add(new int[]{nd, nx, ny});

                    //새로운 장소면 왔다는 기록을 남가고 res++
					if (!calced[nx][ny]) {
						calced[nx][ny] = true;
						res++;
					}
				}
			}
		}

        //총 방문한 위치의 개수 출력
		System.out.print(res);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}