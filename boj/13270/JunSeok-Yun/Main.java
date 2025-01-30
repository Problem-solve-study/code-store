import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] minVal = new int[N + 1];
		int[] maxVal = new int[N + 1];
		for (int i = 1; i <= N; i++)
			minVal[i] = 1000000000;
		int fibo1 = 1;
		int fibo2 = 2;
		int temp;

		while (fibo2 <= N){
			for (int i = fibo2; i <= N; i++){
				minVal[i] = Math.min(minVal[i], minVal[i-fibo2] + fibo1);
				maxVal[i] = Math.max(maxVal[i], maxVal[i-fibo2] + fibo1);
			}
			temp = fibo2;
			fibo2 += fibo1;
			fibo1 = temp;
		}
		System.out.println(minVal[N] + " " + maxVal[N]);
	}
}
