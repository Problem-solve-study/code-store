//문제: BOJ 25401번 카드 바꾸기
//메모리: 16972 KB
//시간: 144 ms

/*
 * 등차수열을 만들기 위해 카드를 바꾸는 횟수의 최솟값
 * 
 * 입력 수열의 임의의 위치(i)의 값을 기준으로 잡았을 때
 * 다른 위치(j)의 값을 바꾸지 않는다고 가정하였을 때
 * 	a[i]-a[j] = (a[0]+i*d) - (a[0]+j*d) = (i-j)*d;
 * 	공차는 (a[i]-a[j])/(i-j)
 * i가 아닌 모든 j에 대해 공차를 구하여 해당 공차를 카운트
 * 가장 많이 나온 공차로 등차수열을 만들 때가 i번째 값을 기준으로 했을 때 가장 적게 카드를 바꾸는 등차수열
 * 
 * 위 과정의 i를 0 ~ n-1까지 반복하면서 공차의 카운트 값(바꾸지 않아도 되는 카드의 개수)의 최대 값을 찾는다
 * 
 * N에서 위에서 찾은 값을 빼면 카드 교체 횟수의 최솟값
 * 
 * 처음에는 배열을 이용해보았으나
 * i가 바뀔때 초기화 시키는 과정이
 * HashMap의 clear 메소드가 가장 빨랐다
 * hashCode 메소드의 연산을 줄이기 위해 클래스를 선언하여 값 그대로 반환하게 오버라이딩
 * 해시충돌이 없으므로 equals 메소드도 무조건 true를 반환하게 오버라이딩
 */

import java.util.*;

public class Main {
	static class Val {
		int value;

		@Override
		public int hashCode() {
			return value;
		}

		@Override
		public boolean equals(Object obj) {
			return true;
		}
	}

	public static void main(String[] args) throws Exception {
		int N = nextInt(), max = 0, diff, len, cnt;
		int[] arr = new int[N];
		Val d = new Val();
		Map<Val, Integer> hm = new HashMap<>();

		for (int i = 0; i < N; i++)
			arr[i] = nextInt();
		for (int i = 0; i < N; i++) {
			hm.clear();
			for (int j = 0; j < N; j++) {
				if (i == j)
					continue;
				if ((diff = arr[i] - arr[j]) % (len = i - j) == 0) {
					d.value = diff / len;
					hm.put(d, cnt = (hm.getOrDefault(d, 1) + 1)); // 기준이 되는 카드도 바뀌지 않으므로 default를 1로
					max = max < cnt ? cnt : max;
				}
			}
		}

		System.out.print(N - max);
	}

	static int nextInt() throws Exception {
		int n, c;
		boolean minus = false;
		while ((c = System.in.read()) < 48)
			if (c == 45)
				minus = true;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return minus ? -n : n;
	}
}
