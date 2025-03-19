//문제: BOJ 1891번 사분면
//메모리: 11532 KB
//시간: 64 ms

/*
 * 사분면 내에서 사분면을 나누므로 분할 정복을 생각했다.
 * 처음에 2가지 방법이 떠올랐다.
 * 규칙을 이용하는 방법과 사분면 계산을 이용하는 방법이다.
 * 
 * 사분면 계산을 이용하는 방법은
 * 입력받은 사분면 내의 임의의 좌표를 선택하고
 * 사분면의 사이즈를 x와 y에 곱한 값만큼 좌표를 이동시키고
 * 이동이 완료된 좌표의 사분면을 계산하는 방법을 사용할 수 있다.
 * 
 * 규칙을 이용하는 방법은
 * 가로로 한 칸 움직이면 1과 2가 토글되고 3와 4가 토글된다.
 * 세로로 한 칸 움직이면 1과 4가 토글되고 2와 3이 토글된다.
 * 예를 들면 41사분면을 왼쪽으로 한 칸 움직이면 42사분면이 된다.
 * 그런데 41사분면을 오른쪽으로 한칸 움직이면 32사분면이 되어 앞자리가 바뀐다.
 * 영역을 벗어났을 때, 앞자리가 바뀌므로 영역을 몇번 벗어났나를 알면 앞자리가 어떻게 바뀌는지 알 수 있다.
 * 제일 앞자리를 이동시켰는데 영역을 벗어났다면 -1을 뽑아내면 된다.
 * 
 * 첫 번째 방법은 해당 영역내에서 몇 사분면인지 구해줘야하므로
 * 사분면의 기준선이 계속 바뀌어 연산이 많아질 것이라고 생각하여
 * 두 번째 방법을 사용하였다.
 * +)처음에는 재귀로 구현하였으나 후에 반복문으로 변경하였다. 이로 인해 클래스를 선언하지 않아도 되었다.
 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		int d = Integer.parseInt(st.nextToken());
		char[] sabun = st.nextToken().toCharArray(); //사분면의 각 자리수를 쉽게 접근 및 수정하기 위해 배열로 만들었다.

		st = new StringTokenizer(br.readLine());
		//dx와 dy의 범위는 -(2^50) ~ (2^50)이므로 long으로 받아야한다.
		long dx = Long.parseLong(st.nextToken());
		long dy = Long.parseLong(st.nextToken());

		while (d-- > 0) {
			int target = sabun[d] - '1'; //수정하고자 하는 자리의 값을 1을 뺀 int타입으로 가지고 온다. '1'->0, '2'->1, '3'->2, '4'->3

			//규칙을 이용할 때, 쉽게 이용하기 위해 1사분면으로 이동시켜 dx와 dy를 수정하여 규칙을 이용한다.
			dx -= (target & 1) ^ (target >> 1);  //1,4사분면은 dx의 변함이 없고, 2,3사분면은 오른쪽으로 한칸 움직였으니 dx에 1을 빼야한다.
			dy -= target >> 1; //1,2사분면은 dx의 변함이 없고, 3,4사분면을 위로 한칸 움직였으니 dy에 1을 빼야한다.
			target = (dx++ & 1) == 0 ? 1 : 2; //만약 dx가 짝수라면 1사분면 홀수라면 2사분면
			dx >>= 1;
			//1사분면에서 오른쪽으로 이동하면 (이동한 칸 수+1)/2만큼 영역을 벗어난다.
			//1사분면에서 왼쪽으로 이동하면 (이동한 칸수)/2만큼 영역을 벗어난다.
			//dx가 양수면 오른쪽, 음수면 왼쪽
			//음수를 비트연산으로 /2를 구현하기 위해서는 1을 더한 뒤 쉬프트 연산을 해야한다.
			//그렇다면 부호에 상관없이 1을 더하고 오른쪽으로 한칸 쉬프트 하면 원하는 값이 나온다.
			target = ((dy++ & 1) == 0) ? target : (target == 1 ? 4 : 3); //만약 dy가 홀수라면 1사분면 -> 4사분면, 2사분면 -> 3사분면 짝수라면 그대로
			dy >>= 1; //연산법 유도는 위와 유사
			
			sabun[d] = (char) (target + '0');
		}

		if (dx == 0 && dy == 0) //영역을 벗어나지 않았다면
			for (char c : sabun) //사분면 출력
				sb.append(c);
		else //벗어났다면
			sb.append(-1); //-1 출력

		System.out.print(sb);
	}
}
