//메모리: 78556 KB
//시간: 792 ms

/*
1,2,3,4번 연산은 부분행렬들에 대한 반전,회전을 시켜주기만 하면 되어서 노가다입니다.
위 4개의 연산을 구현할 때, 참조하는 인덱스를 신경써서 해야합니다.
5,6,7,8번 연산은 1,2,3,4번 연산을 이용하여 해결가능합니다.
즉, 1,2,3,4번 연산만 구현하면 끝나는 문제입니다.
*/
import java.io.*;
import java.util.*;

public class Main {
	static int size;
	static int[][] matrix;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		size = 1 << Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());

		matrix = new int[size][size];
		for (int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < size; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		int input, l;
		for (int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			input = Integer.parseInt(st.nextToken());
			l = 1 << Integer.parseInt(st.nextToken());
			switch (input) {
			case 1:
				firstOper(l);
				break;
			case 2:
				secondOper(l);
				break;
			case 3:
				thirdOper(l);
				break;
			case 4:
				fourthOper(l);
				break;
			case 5:
				fifthOper(l);
				break;
			case 6:
				sixthOper(l);
				break;
			case 7:
				seventhOper(l);
				break;
			case 8:
				eighthOper(l);
				break;
			}
		}

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.printf("%d ", matrix[i][j]);
			}
			System.out.println();
		}
	}

	public static void firstOper(int l) {
		if (l == 1) {
			return;
		}
		int[] temp;
		for (int i = 0; i < size; i += l) {
			for (int y = i; y < i + l / 2; y++) {
				temp = matrix[y];
				matrix[y] = matrix[(i + l) - (y - i) - 1];
				matrix[(i + l) - (y - i) - 1] = temp;
			}

		}

	}

	public static void secondOper(int l) {
		if (l == 1) {
			return;
		}
		int temp;
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j += l) {
				for (int x = j; x < j + l / 2; x++) {
					temp = matrix[i][x];
					matrix[i][x] = matrix[i][(j + l) - (x - j) - 1];
					matrix[i][(j + l) - (x - j) - 1] = temp;
				}

			}
		}

	}

	public static void thirdOper(int l) {
		if (l == 1) {
			return;
		}
		int[][] temp = new int[size][size];
		for (int i = 0; i < size; i += l) {
			for (int j = 0; j < size; j += l) {
				for (int x = j; x < j + l; x++) {
					for (int y = i + l - 1; y >= i; y--) {
						temp[i + x - j][j + l - 1 - (y - i)] = matrix[y][x];
					}
				}
			}
		}
		matrix = temp;
	}

	public static void fourthOper(int l) {
		if (l == 1) {
			return;
		}
		int[][] temp = new int[size][size];
		for (int i = 0; i < size; i += l) {
			for (int j = 0; j < size; j += l) {
				for (int x = j + l - 1; x >= j; x--) {
					for (int y = i; y < i + l; y++) {
						temp[i + l - 1 - (x - j)][j + y - i] = matrix[y][x];
					}
				}
			}
		}
		matrix = temp;
	}

	public static void fifthOper(int l) {
		firstOper(size);
		firstOper(l);
	}

	public static void sixthOper(int l) {
		secondOper(size);
		secondOper(l);
	}

	public static void seventhOper(int l) {
		thirdOper(size);
		fourthOper(l);
	}

	public static void eighthOper(int l) {
		fourthOper(size);
		thirdOper(l);
	}

}
