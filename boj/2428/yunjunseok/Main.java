// 34032KB, 352ms
import java.io.*;
import java.util.*;
public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		long cnt = 0;

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		for (int i = 0; i < N - 1; i++){
			if (arr[i] >= arr[i + 1] * 0.9){
				int left = i + 1;
				int right = N;
				while (left < right){
					int mid = (left + right) / 2;
					if (arr[i] >= arr[mid] * 0.9){
						left = mid + 1;
					}
					else{
						right = mid;
					}
				}
				cnt += left - i - 1;
			}
		}
		System.out.println(cnt);
	}
}
