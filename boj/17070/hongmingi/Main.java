//14304KB, 108ms
import java.io.*;
import java.util.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	static int[][] arr;
	static int[][][] cnt;
	static int N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		for(int i=0; i<N; i++) {
			String s = br.readLine();
			StringTokenizer ss = new StringTokenizer(s);
			for(int j=0; j<N; j++) {
				arr[i][j] = Integer.parseInt(ss.nextToken());
			}
		}
		cnt = new int[N][N][3];
		cnt[0][1][2] = 1;

		
		for(int i=0; i<N; i++) { // 0:대각, 1:세로, 2:가로
			for(int j=2; j<N; j++) {
				if(arr[i][j] == 0) {
					if(i>=1) {
						cnt[i][j][1] = cnt[i-1][j][0] + cnt[i-1][j][1];
						if(arr[i-1][j] == 0 && arr[i][j-1] == 0) {
							cnt[i][j][0] = cnt[i-1][j-1][0] + cnt[i-1][j-1][1] + cnt[i-1][j-1][2];
						}
					}
					cnt[i][j][2] = cnt[i][j-1][0] + cnt[i][j-1][2];						
				}
			}
		}
		
		int sum = 0;
		for(int i=0; i<3; i++) {
			sum += cnt[N-1][N-1][i];
		}
		System.out.println(sum);

	}
	
}
	

