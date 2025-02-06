//14308 KB, 104 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] nums = new int[(N + 1) / 2];
		char[] opts = new char[N / 2];
		String inStr = br.readLine();
		for (int i = 0; i < N; i++) {
			char ch = inStr.charAt(i);
			if ('0' <= ch && ch <= '9')
				nums[i / 2] = ch - '0';
			else
				opts[i / 2] = ch;
		}
		int[] maxDP = new int[(N + 1) / 2];
		int[] minDP = new int[(N + 1) / 2];
		maxDP[0] = nums[0];
		minDP[0] = nums[0];
		if (N > 2) {
			maxDP[1] = calcOperation(nums[0], nums[1], opts[0]);
			minDP[1] = calcOperation(nums[0], nums[1], opts[0]);
		}
		for (int i = 2; i < (N + 1) / 2; i++) {
			maxDP[i] = Math.max(calcOperation(maxDP[i - 1], nums[i], opts[i - 1]), // 괄호 안 친 경우
					calcOperation(maxDP[i - 2], calcOperation(nums[i - 1], nums[i], opts[i - 1]), opts[i - 2])); // 괄호 친 경우
			maxDP[i] = Math.max(maxDP[i],
					calcOperation(minDP[i - 2], calcOperation(nums[i - 1], nums[i], opts[i - 1]), opts[i - 2])); // 음수 곱하기의 경우

			// 최솟값 저장
			minDP[i] = Math.min(calcOperation(minDP[i - 1], nums[i], opts[i - 1]), // 괄호 안 친 경우
					calcOperation(minDP[i - 2], calcOperation(nums[i - 1], nums[i], opts[i - 1]), opts[i - 2])); // 괄호 친 경우
			minDP[i] = Math.min(minDP[i],
					calcOperation(maxDP[i - 2], calcOperation(nums[i - 1], nums[i], opts[i - 1]), opts[i - 2])); // 음수 곱하기의 경우
		}
		System.out.println(maxDP[N / 2]);
	}

	static int calcOperation(int num1, int num2, char opt) {
		switch (opt) {
		case '+':
			return num1 + num2;
		case '-':
			return num1 - num2;
		case '*':
			return num1 * num2;
		default:
			return -1;
		}
	}
}