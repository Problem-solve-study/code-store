import java.io.*;
import java.util.*;

/*
 * 65256KB, 848ms
 * 
 * 행렬의 거듭제곱 식의 꼴이 플로이드 워셜의 꼴로 나타나니,
 * 주어진 그래프를 인접 행렬로 받고 그걸 K제곱 하게 되면 모든 정점 쌍을 거쳐 특정 정점으로 갈 수 있는지 여부가 나온다.
 * 갈 수 있냐 없냐만 판단하면 되니 int로 할 필요없이 boolean[][]을 써도 됨
 * 
 * 이걸 어떻게 바로 떠올릴까요...
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N, K, M;
	static boolean[][] matrix;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		N = nextInt(); K = nextInt(); M = nextInt();
		matrix = new boolean[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			matrix[i][nextInt()] = matrix[i][nextInt()] = true;
		}


		//분할 정복을 이용한 거듭제곱 + 행렬 곱셈
		boolean[][] res = pow(matrix, K);
		for (int i = 0; i < M; i++) {
			if (res[nextInt()][nextInt()]) {
				sb.append("death");
			} else {
				sb.append("life");
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
	
	static boolean[][] pow(boolean[][] mat, int num) {
		if (num == 1) {
			return matrix;
		}
		
		boolean[][] m = pow(mat, num / 2);
		if (num % 2 == 0) {
			return mul(m, m);
		} else {
			return mul(mul(m, m), matrix);
		}
	}
	
	static boolean[][] mul(boolean[][] mat1, boolean[][] mat2) {
		boolean[][] res = new boolean[N + 1][N + 1];
		
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (int k = 1; k <= N; k++) {
					res[i][j] |= (mat1[i][k] & mat2[k][j]);
				}
			}
		}
		
		return res;
	}

	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
