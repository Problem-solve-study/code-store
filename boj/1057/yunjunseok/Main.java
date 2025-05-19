// 11520KB, 64ms
import java.io.*;
import java.util.*;

public class Main {
	static int N, X ,Y;
	static int cnt;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		Y = Integer.parseInt(st.nextToken());

		cnt = 1;
		while (X != Y){
			X = (X + 1) / 2;
			Y = (Y + 1) / 2;
			cnt++;
			if (cnt > 100000){
				System.out.println(-1);
				return;
			}
		}
		System.out.println(cnt - 1);
	}
}