//11348KB 60ms
import java.io.*;
public class Main {
	static int T;
	static int minVal, temp;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine()); // 입력 test case의 개수

		for (int tc = 0; tc < T; tc++){
			String str = br.readLine();
			minVal = 0; // 꼭대기에 올라갈 수 있는 최소의 원숭이 수
			int depth = 0; // 트리의 깊이
			temp = 0;
			
			for (int i = 0; i < str.length(); i++){
				char c = str.charAt(i);
				if (c == '['){
					depth++;
				}else{
					depth--;
				}
				temp = Math.max(depth, temp);
			}
			minVal = 1 << (temp);
			sb.append(minVal).append('\n');
		}
		System.out.println(sb.toString());
	}
}