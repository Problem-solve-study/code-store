import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String s = br.readLine();
		StringTokenizer st = new StringTokenizer(s);
		int N = Integer.parseInt(st.nextToken());
		int i = Integer.parseInt(st.nextToken());
		String ss = br.readLine();
		StringTokenizer st2 = new StringTokenizer(ss);
		long[] arr = new long[N];
		for(int j = 0; j<N; j++) {
			arr[j] = Integer.parseInt(st2.nextToken());
		}
		for(int j=0; j<i; j++) {
			Arrays.sort(arr);
			long k = arr[0]+arr[1];
			arr[0] = k;
			arr[1] = k;
		}
		
		long sum = 0;
		for(long a:arr) {
			sum += a;
		}
		System.out.println(sum);
	}
}
