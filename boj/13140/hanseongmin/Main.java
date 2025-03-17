import java.io.*;

/*
 * 12760KB, 92ms
 * 
 * 구해야할 수가 총 7개에 각 수에 들어갈 수 있는 수가 10개밖에 안되서 브루트포스로 모두 돌려보면 되겠다고 생각했다.
 * 포맷 조심하기.. 왜 틀리지 하고 있었다.. 조합구할 때 실수한줄
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	static int N;
	static boolean[] v = new boolean[10];
	static int[] arr = new int[7];
	static boolean flag = false;    //값을 찾았으면 flag on
	
	public static void main(String[] args) throws Exception {
		N = nextInt();
		dfs(0);
		StringBuilder sb = new StringBuilder();
		if (flag) { //값을 찾았다면
			sb.append(' ').append(' ').append(arr[0]).append(arr[1]).append(arr[2]).append(arr[2]).append(arr[3]).append('\n');
			sb.append('+').append(' ').append(arr[4]).append(arr[3]).append(arr[5]).append(arr[2]).append(arr[6]).append('\n');
			sb.append("-------").append('\n');
			sb.append(String.format("%7d", N)); //포맷 주의!!! 이거 때문에 맞왜틀하고 있었음
		} else {
			sb.append("No Answer");
		}
		System.out.print(sb);
	}
	
	static void dfs(int idx) {
		if (idx == 7) {
			if (check()) { //모든 수를 골랐을 때 합이 N이 된다면 flag on
				flag = true;
			}
			return;
		}
		if (flag) return;
		
		for (int i = 0; i < 10; i++) {
			if (flag) return;
            //h와 w는 0이 될 수 없음
			if ((idx == 0 || idx == 4) && i == 0) continue;
            //서로 다른 한 자리수이므로 boolean 배열로 체크
			if (!v[i]) {
				v[i] = true;
				arr[idx] = i;
				dfs(idx + 1);
				v[i] = false;
			}
		}
	}
	
	static boolean check() {
		int a = arr[0] * 10000 + arr[1] * 1000 + arr[2] * 100 + arr[2] * 10 + arr[3];
		int b = arr[4] * 10000 + arr[3] * 1000 + arr[5] * 100 + arr[2] * 10 + arr[6];
		return (a + b) == N;
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}
