// 37180KB 252ms
import java.io.*;
import java.util.StringTokenizer;
public class Main {
	public static int N;
	public static int start, end;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		int maxStart = -1;
		int minEnd = Integer.MAX_VALUE;
		for (int i = 0; i < N; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());

			if (maxStart < start){
				maxStart = start;
			}
			if (minEnd > end){
				minEnd = end;
			}
		}
		if (maxStart - minEnd <= 0){
			System.out.println(0);
		}else{
			System.out.println(maxStart - minEnd);
		}
	}
}
