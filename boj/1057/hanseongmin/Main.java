import java.io.*;
import java.util.*;

/*
 * 11536KB, 64ms
 * 
 * 대진표를 그려보며 규칙을 파악해보니 0-base로 순서를 매기면 2로 나눈 몫이 같은 그룹끼리 매칭이 된다.
 * 또한 승리했을 때의 번호 역시 2로 나눈 몫이 된다.
 * 이를 이용하여 재귀적으로 구해주면 logN의 복잡도로 구할 수 있다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int N = nextInt();
		//0-base로 순서를 매기기 위해 - 1
		int a = nextInt() - 1;
		int b = nextInt() - 1;
		System.out.print(getAns(a, b, N, 1));
	}
	
	static int getAns(int a, int b, int len, int depth) {
		//2로 나눈 몫이 같다면 현재 라운드에서 대결하는 것
		if (a / 2 == b / 2) {
			return depth;
		}
		
		return getAns(a / 2, b / 2, len / 2, depth + 1);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}