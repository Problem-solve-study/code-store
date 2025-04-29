import java.io.*;
import java.util.*;

/*
 * 11656KB, 68ms
 * 
 * 끝이 Cheese로 끝나는 String을 받아 set에 넣은 후 set에 넣은 개수 세기
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		HashSet<String> set = new HashSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			String str = st.nextToken();
			//현재 토핑이 치즈라면 set에 추가
			if (str.endsWith("Cheese")) {
				set.add(str);
			}
		}
		
		//치즈의 종류가 4개 이상이라면 콰트로치즈피자를 만들 수 있음
		System.out.print(set.size() >= 4 ? "yummy" : "sad");
	}
}