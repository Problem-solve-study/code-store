import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		long V;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		V = Long.MIN_VALUE;
		for (int i = N - 1; i >= 0 ; i--){
			if (arr[i] > V){
				V = arr[i];
			}else if (arr[i] < V){
				if (V % arr[i] == 0){
					continue;
				}else{
					V = ((V/arr[i]) + 1) * arr[i];
				}
			}
		}
		System.out.println(V);
	}
}