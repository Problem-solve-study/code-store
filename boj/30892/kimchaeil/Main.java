//문제: BOJ 30892번 상어 키우기
//메모리: 25628 KB
//시간:  384 ms

/*
 *  먹을 수 있는 것들 중 가장 큰 걸 먹는다
 *  위 방법을 먹을 수 있는 만큼 반복하면 된다.
 */

import java.io.*;
import java.util.*;

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		int n = nextInt();
		int k = nextInt();
		long t = nextInt();
		long[] arr = new long[n + 1];
		for (int i = 0; i < n; i++)
			arr[i] = nextInt();
		arr[n] = Long.MAX_VALUE;

		Arrays.sort(arr); // 상어 크기 오름차순 정렬
		long[] stack = new long[n]; // 샼보다 작지만 가장 크지 않아 먹지 않은 상어
		int sIdx = 0;
		for (int i = 0; i < n; i++) {
			if (k == 0) //더 이상 못 먹으면 break
				break;
			if (arr[i + 1] >= t) { //다음 상어가 샼 크기보다 크면
				if (arr[i] < t) { //현재 상어가 샼 크기보다 작으면
					t += arr[i]; //먹는다
					k--;
				} else if (sIdx > 0) { //현재 상어가 샼 크기보다 크지만 남겼던 상어가 있으면
					t += stack[--sIdx]; //남긴 상어 중 가장 큰 상어 먹기
					k--;
					i--; //현재 상어를 다시 확인하기 위해 i 감소
				} else { //남은 상어도 없다면 더 이상 먹을 상어가 없음
					break;
				}
			} else { //다음 상어가 샼 크기보다 작으면
				stack[sIdx++] = arr[i]; //남기기
			}
		}

		while (k-- > 0 && sIdx > 0) //더 먹을 수 있는데 남긴 상어가 있으면
			t += stack[--sIdx]; //큰 상어부터 먹기

		System.out.print(t);
	}

	static int nextInt() throws IOException {
		st.nextToken();
		return (int) st.nval;
	}
}
