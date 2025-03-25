//문제: BOJ 3425번
//메모리: 68132 KB
//시간: 276 ms

/*
 * 구현 문제
 * 입력되는 명령어들을 queue에 넣으면서 명령어의 개수를 카운트한다.
 * NUM 명령어의 경우 뒤에 피연산자가 있으므로 StringTokenizer를 통해 토크나이징하여 따로 queue에 넣는다.
 * queue에서 poll한 연산자에 대한 연산을 진행한다. 이를 명령어의 개수만큼 한다. 같은 명령어들을 여러개의 숫자들에 대해 진행해야하기 때문이다.
 */

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		sys: while (true) {
			Queue<String> op = new ArrayDeque<>();
			int opCnt = 0;
			while (true) {
				String input = br.readLine();
				if (input.equals("QUIT")) //QUIT면 전체 루프문 종료
					break sys;
				if (input.equals("END")) //END면 입력 루프문 종료
					break;
				opCnt++; //명령어 개수 카운트
				st = new StringTokenizer(input);
				while (st.hasMoreTokens())
					op.add(st.nextToken());
			}
			int n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				long[] stack = new long[1000]; //스택에 1000개 이상의 숫자가 저장되는 경우는 없으므로 길이는 1000으로
				int top = -1; //top을 마지막에 숫자를 넣은 위치로 한다.
				int k = Integer.parseInt(br.readLine());
				stack[++top] = k;
				boolean isValid = true;
				for (int j = 0; j < opCnt; j++) { //opCnt만큼 반복
					String oper = op.poll();
					String num = null;
					if (isValid) { //만약 문제에서 주어진 연산의 조건으로 인해 오류가 생긴다면 더 이상 명령 수행을 하지 않는다.
						switch (oper) {
						case "NUM": //NUM이면
							num = op.poll(); //숫자를 poll하여
							stack[++top] = Integer.parseInt(num); //스택에 넣기
							break;
						case "POP":
							if (top < 0) {
								isValid = false;
								break;
							}
							top--;
							break;
						case "INV":
							if (top < 0) {
								isValid = false;
								break;
							}
							stack[top] = -stack[top];
							break;
						case "DUP":
							if (top < 0) {
								isValid = false;
								break;
							}
							top++;
							stack[top] = stack[top - 1];
							break;
						case "SWP":
							if (top < 1) {
								isValid = false;
								break;
							}
							long temp = stack[top];
							stack[top] = stack[top - 1];
							stack[top - 1] = temp;
							break;
						case "ADD":
							if (top < 1) {
								isValid = false;
								break;
							}
							stack[top - 1] += stack[top];
							top--;
							break;
						case "SUB":
							if (top < 1) {
								isValid = false;
								break;
							}
							stack[top - 1] -= stack[top];
							top--;
							break;
						case "MUL":
							if (top < 1) {
								isValid = false;
								break;
							}
							stack[top - 1] *= stack[top];
							top--;
							break;
						case "DIV":
							if (top < 1 || stack[top] == 0) {
								isValid = false;
								break;
							}
							stack[top - 1] /= stack[top];
							top--;
							break;
						case "MOD":
							if (top < 1 || stack[top] == 0) {
								isValid = false;
								break;
							}
							stack[top - 1] %= stack[top];
							top--;
							break;
						}
					} else { //isValid가 false일때 NUM 뒤에 있는 숫자를 처리해주지 못하므로 따로 처리해준다.
						if(oper.equals("NUM")) {
							num = op.poll();
						}
					}
					op.add(oper);
					if (num != null) {
						op.add(num);
						num = null;
					}
					if (top>=0&&Math.abs(stack[top]) > 1_000_000_000) //1e9보다 커지면 error
						isValid = false;
				}
				if (top != 0) //마지막에 숫자가 하나가 아니라면 error
					isValid = false;
				sb.append(isValid ? stack[top] : "ERROR").append('\n');
			}
			sb.append('\n');
			br.readLine();
		}
		System.out.println(sb);
	}
}
