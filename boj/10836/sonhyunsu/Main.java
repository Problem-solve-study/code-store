//제출번호: 92891440
//메모리:   99756 KB
//실행시간: 3916 ms
import java.io.*;

//입력 한 줄당 모든 위치를 시뮬레이션 돌리면 700 x 700 x 1000000으로 시간초과임
//그림판으로 2~3개 경우에 대해서 그림을 그려봤는데
//주어진 입력을 인덱스별로 더해놓고, 한 번만 시뮬레이션을 돌려도 값이 똑같이 나오는 것 같아서
//한 번 제출해봤는데 통과됨
//문제 조건에서 항상 증가하는 입력으로 줘서 가능한 듯 싶음
//2차원 배열을 쓰면 시간이 많이 늘어났음. 2차원 배열을 조금만 덜 써도 실행시간이 줄어듦
public class Main {

	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int m = nextInt();
		int n = nextInt();

		int[][] map = new int[m][m];
		for (int i = 0; i < n; i++) {
			int[] cnts = new int[]{nextInt(), nextInt(), nextInt()};

			int[] pos = new int[]{m - 1, 0};
            //주어진 숫자 개수를 각 인덱스에 맞게 더함
			for (int num = 0; num < 3; num++) {
				for (int cnt = 0; cnt < cnts[num]; cnt++) {
					map[pos[0]][pos[1]] += num;
					nextPos(pos);
				}
			}
		}

        //시뮬레이션
		for (int i = 1; i < m; i++) {
			for (int j = 1; j < m; j++) {
				map[i][j] = Math.max(map[i-1][j-1], Math.max(map[i-1][j], map[i][j-1])); 
			}
		}

        //원래 애벌레는 1의 값을 가지고 있었으니까 +1
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				sb.append(map[i][j] + 1).append(' ');
			}
			sb.append('\n');
		}

		System.out.print(sb);
	}

    //인덱스의 다음 위치를 계산함
	static void nextPos(int[] pos) {
		if (pos[0] == 0) {
			pos[1]++;
		} else {
			pos[0]--;
		}
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}