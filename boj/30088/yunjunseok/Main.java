//90892KB, 476ms
import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static long[] arr;
	static long[] sumArr;
public static void main(String[] args)throws IOException {
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    arr = new long[N];
    sumArr = new long[N + 1];
	
	for (int i = 0; i < N; i++){
		StringTokenizer st = new StringTokenizer(br.readLine());
		int cnt = Integer.parseInt(st.nextToken());
		long sum = 0;
		for (int j = 0; j < cnt; j++){
			sum += Integer.parseInt(st.nextToken());
		}
		arr[i] = sum;
	}
    Arrays.sort(arr);
    for (int i = 1; i < N; i++){
		arr[i] = arr[i - 1] + arr[i];
    }
	
	sumArr[1] = arr[0];
	for (int i = 2; i <= N ;i++){
		sumArr[i] = sumArr[i - 1] + arr[i - 1];
	}

    System.out.println(sumArr[N]);
	}
}