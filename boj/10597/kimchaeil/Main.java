//문제: BOJ 10597번
//메모리: 12116 KB
//시간: 68 ms
/*
 * 백트래킹
 */
import java.io.*;

public class Main {
	static int[] arr;
	static int[] result;
	static long visited;

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();

		char[] input = new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray();
		if (input.length < 10) { //길이가 10 미만이면 9이하의 수로만 이루어져 있으므로 한글자씩 출력
			for (char c : input)
				sb.append(c).append(' ');
		} else {
			int n = input.length;
			arr = new int[n];
			for (int i = 0; i < n; i++)
				arr[i] = input[i] & 15;

			n += 9; n >>= 1; //n은 (input길이+9)/2
			result = new int[n];

			backTracking(0, 0, n);

			for (int i : result)
				sb.append(i).append(' ');
		}
		System.out.print(sb);
	}

	static boolean backTracking(int depth, int index, int n) {
		if (depth == n) //수열 찾음
			return true;

		int value = arr[index];
		if (value == 0) //수열 내의 숫자는 1이상이며 0으로 시작하지 않음
			return false;
		long masking = 1L << value;
		if ((visited & masking) == 0) { //앞에 value가 없으면
			visited |= masking;
			result[depth] = value;
			if (backTracking(depth + 1, index + 1, n)) //찾았으면 탐색 중지
				return true;
			visited ^= masking;
		}
		if (index < arr.length - 1) { //index가 끝을 가리키고 있지 않다면
			value = ((value << 2) + value) << 1; // *10
			value += arr[index + 1];
			masking = 1L << value;
			if (value <= n && (visited & masking) == 0) { //value가 n을 넘지 않고 앞에 없는 수라면
				visited |= masking;
				result[depth] = value;
				if (backTracking(depth + 1, index + 2, n)) //찾았으면 탐색 중지
					return true;
				visited ^= masking;
			}
		}
		return false;
	}
}
