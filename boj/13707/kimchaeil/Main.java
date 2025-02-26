//문제: 13707번 합분해 2

//중복조합
//메모리: 12232KB
//시간:  172ms

//조합
//메모리: 12156KB
//시간: 128ms

import java.io.*;

/*
 * 문제를 보면
 * 원하는 답은 kHn이다.(중복조합)
 * kHn은 (k+n-1)C(n) = (k+n-1)C(k-1)이다.(조합)
 * 즉, 이 문제는 조합을 구하는 문제이다.
 * 재귀로 조합을 구하게 되면 시간 복잡도가 크기 때문에
 * DP로 구하는 방법을 사용해야 한다.
 * DP로 풀게 되면 중복 조합으로도 조합으로도 풀 수 있다.
 * 아래에 두 코드 모두 적혀있다.
 * 
 * +)중복조합으로 풀면 O(n*k)이고 조합으로 풀면 O((n+k)^2)인데 이상하게 조합이 더 빠르다.
 */


public class Main {
	static StreamTokenizer st;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		st = new StreamTokenizer(br);
		int n = nextInt(), k = nextInt();
		
//		//중복 조합으로 DP
//		int[] arr = new int[k];
//		for(int i=0;i<k;i++) 
//			arr[i]=i+1;
//		for(int i=1;i<n;i++)
//			for(int j=1;j<k;j++)
//				arr[j]=(arr[j]+arr[j-1])%1_000_000_000;
//		System.out.println(arr[k-1]);
		
		//조합으로 DP
		n += k - 1;
		int[] arr = new int[n + 1];
		arr[0] = arr[1] = 1;
		for (int i = 2; i <= n; i++) {
			arr[i] = 1;
			for (int j = i - 1; j > 0; j--) {
				arr[j] = (arr[j] + arr[j - 1]) % 1_000_000_000;
			}
		}
		System.out.println(arr[k - 1]);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}