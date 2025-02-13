//문제: 23843번 콘센트
//메모리: 12892 KB
//시간: 92 ms

import java.util.*;
import java.io.*;

/*
 * 충전에 필요한 시간을 내림차순으로 정렬하고
 * 충전 시간이 오래 걸리는 전자기기부터 대기시간이 가장 짧은 콘센트에 추가하면 된다고 생각했다.
 * +) 우선순위큐를 사용하면 poll과 add 연산에서 오버헤드가 생겨서 배열로 직접 최소인 곳을 찾게 바꾸었다.
 *    출력이 하나이므로 sysout이 더 효율적
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());

		int[] times = new int[n]; // 장치들의 충전시간
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			times[i] = Integer.parseInt(st.nextToken());
		}

		int[] sockets = new int[m];
		int result = 0;
		Arrays.sort(times); // 오름차순 정렬
		for (int i = n - 1; i >= 0; i--) { // 뒤에서 부터 접근(내림차순): 충전시간이 오래 걸리는 것부터
			int idx = 0;
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < m; j++) { // 대기시간이 최소인 idx 찾기
				if (sockets[j] < min) {
					min = sockets[j];
					idx = j;
				}
			}
			sockets[idx] += times[i]; // 찾은 idx에 추가
			result = result < sockets[idx] ? sockets[idx] : result; // result 확인
		}
		System.out.println(result);
	}

}