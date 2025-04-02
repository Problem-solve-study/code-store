//제출번호: 92427120
//메모리:   11528 KB
//실행시간: 64 ms
import java.io.*;

//n이 매우 작기 때문에 그냥 n^2으로 돌면서 나보다 덩치가 큰 사람의 수를 세면 됨
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int[][] users = new int[2][n];
		for (int i = 0; i < n; i++) {
			users[0][i] = nextInt();
			users[1][i] = nextInt();
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			int order = 1;
			for (int j = 0; j < n; j++) {
                //나 자신은 패스
				if (i == j) {
					continue;
				}

                //나보다 덩치가 큰 사람이 있다면 내 순서가 밀림
				if (users[0][j] > users[0][i] && users[1][j] > users[1][i]) {
					order++;
				}
			}

			sb.append(order).append(' ');
		}

		System.out.print(sb);
	}

	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}