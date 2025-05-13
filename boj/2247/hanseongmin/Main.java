import java.io.*;

/*
11652KB, 480ms

10까지라고 생각해보면

숫자 - 약수(실질적 약수의 수)
1 - 1(0)
2 - 1 2(0)
3 - 1 3(0)
4 - 1 2 4
5 - 1 5(0)
6 - 1 2 3 6(2)
7 - 1 7(0)
8 - 1 2 4 8(2)
9 - 1 3 9(1)
10 - 1 2 5 10(2)

1 ~ 10까지 2를 제외한 2의 배수는? 4개
1 ~ 10까지 3을 제외한 3의 배수는? 2개
1 ~ 10까지 4를 제외한 4의 배수는? 1개
1 ~ 10까지 5를 제외한 5의 배수는? 1개

N / 2까지 모든 약수를 더하기

시간제한 2초에 최대 1억번 돌것이고 연산도 간단하니 돌아갈거같아서
그대로 구현
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
	public static void main(String[] args) throws Exception {
		int n = nextInt();
        //혹시 몰라서 long으로 함
		long res = 0;
		for (int i = 2; i * 2 <= n; i++) {
            //자기 자신은 제외해야하니 n / i - 1 (2의 배수를 셀 때 2는 제외해야함)
			res += ((n / i) - 1) * i;
			res %= 1_000_000;
		}
		System.out.print(res);
	}
	
	static int nextInt() throws Exception {
		st.nextToken();
		return (int) st.nval;
	}
}