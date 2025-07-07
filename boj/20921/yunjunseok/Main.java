//12280KB, 128ms
import java.io.*;
import java.util.*;

public class Main {
	static int N,K;
	static int curIdx;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new int[N];
		for (int i = 0; i < N; i++){
			arr[i] = i + 1;
		}
		curIdx = N - 1;
		for (int i = 0; i < K; i++){
			swap();
			System.out.println(Arrays.toString(arr));
		}
		for (int i = 0 ; i < N; i++){
			sb.append(arr[i]).append(" ");
		}
		System.out.println(sb.toString());
	}

	public static void swap(){
		if (curIdx == 0){
			curIdx = N - 1;
		}
		if (arr[curIdx - 1] < arr[curIdx]){
			int temp = arr[curIdx];
			arr[curIdx] = arr[curIdx - 1];
			arr[curIdx - 1] = temp;
			curIdx--;
		}else{
			while (true){
				if (curIdx == 0){
					curIdx = N - 1;
					continue;
				}
				if (arr[curIdx - 1] < arr[curIdx]){
					int temp = arr[curIdx];
					arr[curIdx] = arr[curIdx - 1];
					arr[curIdx - 1] = temp;
					curIdx--;
					return ;
				}
				curIdx--;
			}
		}
	}
}