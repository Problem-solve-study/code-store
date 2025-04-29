//문제: BOJ 27964 콰트로치즈피자
//메모리: 11688 KB
//시간: 68 ms

/*
 * Cheese로 끝나면 Set에 넣고
 * 마지막에 Set의 사이즈로 판단
 */

import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws Exception {
		int n = nextInt();
		Set<String> hs = new HashSet<>();
		while (n-- > 0) {
			String topping = next();
			if (topping.endsWith("Cheese"))
				hs.add(topping);
		}
		System.out.println(hs.size() > 3 ? "yummy" : "sad");
	}

	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}

	static String next() throws Exception {
		st.nextToken();
		return st.sval;
	}
}
