//문제: BOJ 1141번 접두사
//메모리: 11612 KB
//시간: 64 ms

/*
 * 접두사가 있다면 접두사를 포함한 단어가 아닌 접두사를 제외하는 것이 이득이다.
 * 만약, 서로 접두사가 아니면서 같은 접두사를 가지는 문자열 2개가 있을 때,
 * 접두사를 제외하면 2개의 문자열을 모두 집합에 포함시킬 수 있기 때문이다.
 * 
 * 사전기준으로 정렬하게 되면 접두사는 접두사를 포함한 단어보다 먼저 나온다.
 * 또한 접두사와 접두사를 포함한 단어 중 하나는 연속되어있다.
 * 입력받은 문자열들을 정렬하고
 * 순차탐색하면서 다음 문자열의 접두사라면 제외한다.
 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] strArr = new String[n];
		for (int i = 0; i < n; i++) //입력
			strArr[i] = br.readLine();
		Arrays.sort(strArr); //정렬
		int result = n;
		for (int i = 0; i < n - 1; i++)
			if (strArr[i + 1].startsWith(strArr[i])) //뒤의 문자열의 접두사라면
				result--; //제외
		System.out.println(result);
	}
}