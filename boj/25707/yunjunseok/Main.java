//12132KB, 88ms
import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int sum = 0;
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		for (int i = 0; i < N - 1; i++){
			sum += Math.abs(arr[i] - arr[i + 1]);
		}
		sum += Math.abs(arr[0] - arr[N - 1]);
		System.out.println(sum);
	}
}