//31112KB, 252ms
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		for (int i = 0 ; i < T;i ++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			String str1 = st.nextToken();
			String str2 = st.nextToken();
			boolean[] check = new boolean[str1.length()];
			int checkCnt = str1.length();
			int[] strCnt = new int[2];
			int res = 0;

			for (int j = 0; j < str1.length(); j++){
				if (str1.charAt(j) == '1'){
					strCnt[0] += 1;
				}
				if (str2.charAt(j) == '1'){
					strCnt[1] += 1;
				}
				if (str1.charAt(j) == str2.charAt(j)){
					check[j] = true;
					checkCnt--;
				}
			}
			int diffCnt = Math.abs(strCnt[0] - strCnt[1]);
			if (checkCnt - diffCnt != 0){
				res = diffCnt + (checkCnt - diffCnt) / 2;
			}else if (checkCnt - diffCnt == 0){
				res = diffCnt;
			}
			sb.append(res).append('\n');
		}
		System.out.println(sb.toString());
	}
}