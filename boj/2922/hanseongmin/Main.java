import java.io.*;
import java.util.*;

/*
 * 11988KB, 84ms
 * 
 * 연속된 자음, 모음 체크하는 메소드를 잘못 짜서 엄청 오래 걸렸다. (당연히 경우의 수를 잘못 카운팅한줄)
 * _의 최대 개수가 10개이므로 브루트포스로 구했다.
 * 이때 L의 없는 경우에는 중복해서 카운팅 되는 경우가 있어 이를 적절히 고려하여 카운팅한다.
 */

public class Main {
	static String str;
	static ArrayList<Integer> _pos = new ArrayList<>();
	static boolean[] isMo;
	static long res = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		str = br.readLine();
		boolean existL = false;
		isMo = new boolean[str.length()];
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == 'L') {
				existL = true;
			} else if (str.charAt(i) == '_') {
				_pos.add(i);
			}
			
			if (isMo(str.charAt(i))) {
				isMo[i] = true;
			}
		}
		
		//L이 이미 있는 경우는 그냥 브루트포스로 구하면 된다.
		if (existL) {
			dfs(0, -1);
		} else {
			//L이 없다면 L의 위치를 고정시켜두고 나머지를 브루트포스로 구한다.
			for (int _p : _pos) {
				dfs(0, _p);
			}
		}
		System.out.print(res);
	}
	
	static void dfs(int idx, int lPos) {
		if (idx == _pos.size()) {
			long r = 1;
			for (int i : _pos) {
				if (i == lPos) continue; //L을 배치했으므로 넘어감
				if ((isMo[i] && !canMo(i)) || (!isMo[i] && !canJa(i))) { //불가능한 경우였으면 나감
					return;
				}
				if (isMo[i]) {
					r *= 5;
				} else {
					if (i < lPos) { //현재 lPos보다 작은 i에 대해 자음을 배치한다면 L을 뺀 경우를 세야함
						r *= 20;
					} else {
						r *= 21;
					}
				}
			}
			res += r;
			return;
		}

		//자음을 놔보고
		dfs(idx + 1, lPos);
		//모음도 놔봄
		if (_pos.get(idx) != lPos) { //현재 위치가 L로 고정됐다면 모음은 못넣음
			isMo[_pos.get(idx)] = true;
			dfs(idx + 1, lPos);
			isMo[_pos.get(idx)] = false;
		}
	}
	
	static boolean canMo(int idx) {
		for (int i = 0; i <= str.length() - 3; i++) {
			if (isMo[i] && isMo[i + 1] && isMo[i + 2]) {
				return false;
			}
		}
		
		return true;
	}
	
	static boolean canJa(int idx) {
		for (int i = 0; i <= str.length() - 3; i++) {
			if (!isMo[i] && !isMo[i + 1] && !isMo[i + 2]) {
				return false;
			}
		}
		
		return true;
	}
	
	static boolean isMo(char c) {
		return c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
	}
}