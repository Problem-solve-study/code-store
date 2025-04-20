// 22708KB, 124ms
import java.io.*;
public class Main {
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		String[] arr = new String[N];
		int res = 0;

		for (int i = 0; i < N; i++){
			arr[i] = br.readLine();
		}

		for (int i = 0; i < N; i++){
			for (int j = i + 1; j < N; j++){
				for (int k = 1; k <= Math.min(arr[i].length(), arr[j].length()); k++){
					if (arr[i].endsWith(arr[j].substring(0,k)) || arr[j].endsWith(arr[i].substring(0,k))){
						res++;
						break;
					}
				}
			}
		}
		System.out.println(res);
	}
}