//문제: BOJ 3673 나눌 수 있는 부분 수열
//메모리: 39148 KB
//시간: 188 ms

/*
 * 예전에 풀었던 누적합 문제 중에
 * i번째 원소까지의 합을 구하고 해당 값이 몇번 나왔냐를 HashMap에 저장하여 개수를 구하는 문제가 기억이 났다.
 * 이 문제는 부분 수열의 합이 d로 나누어 떨어지는 것의 개수이다.
 * 나누어 떨어진다는 것은 모듈러 연산의 값이 0이라는 것이다.
 * 모듈러 연산은 분배법칙을 만족하기 때문에 미리 모듈러 연산을 해도 된다.
 * 즉, i번째 원소까지의 합을 d로 모듈러 연산을 한다.
 * 이때, 나머지가 a라고 하자. 그렇다면 나머지가 a가 되는 앞 부분을 빼면 나누어 떨어진다.
 * 이를 코드로 옮겼다.
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
		int c = nextInt();
        int[] arr;
		while (c-->0) {
            int d = nextInt(), n = nextInt(), sum = 0, result = 0;
            arr = new int[d];
            //i까지의 원소의 합을 d로 모듈러 연산을 하고 그 값이 나온 횟수를 result에 누적해주고 값이 나온 횟수를 증가
            while(n-->0)
                result+=arr[(sum=(sum+nextInt())%d)]++;
            sb.append(result+arr[0]).append('\n'); //i까지 원소의 합이 이미 나누어떨어진 경우를 위에서 카운트 해주지 못하므로 마지막에 더해준다.
		}
		System.out.print(sb);
	}

	static int nextInt() throws IOException {
		int n, c;
		while ((c = System.in.read()) < 48)
			;
		n = c & 15;
		while ((c = System.in.read()) > 47)
			n = (n << 3) + (n << 1) + (c & 15);
		return n;
	}
}
