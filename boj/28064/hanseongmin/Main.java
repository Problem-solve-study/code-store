import java.io.*;
import java.util.*;

/*
 * 33536KB, 120ms
 * 
 * N이 100이고 k도 최대 20이라서 그냥 O(KN^2)로 브루트포스 돌리기
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] names = new String[N];
		for (int i = 0; i < N; i++) {
			names[i] = br.readLine();
		}
		
		int res = 0;
		for (int i = 0; i < N; i++) {
			String S = names[i];
			for (int j = i + 1; j < N; j++) {
				String T = names[j];
				boolean find = false;
				for (int k = 1; k <= Math.min(S.length(), T.length()); k++) {
					if (T.substring(T.length() - k).equals(S.substring(0, k)) || S.substring(S.length() - k).equals(T.substring(0, k))) {
						find = true;
						break;
					}
				}
				
				if (find) {
					res++;
				}
			}
		}
		System.out.print(res);
	}
}