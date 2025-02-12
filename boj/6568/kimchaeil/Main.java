//메모리: 11668 KB
//시간: 68 ms

/*
 * 배열을 참조할 때의 인덱스를 유효하게 만들기 위한 노력을 하였다.
 */
import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] arr = new int[32]; //메모리

		sys: while (true) {
			int pc = 0;
			int adder = 0;
			for (int i = 0; i < 32; i++) {
				String input = br.readLine();
				if (input == null || input.length() == 0) { //종료조건
					break sys;
				}
				arr[i] = Integer.parseInt(input, 2); //메모리에 입력
			}
			simul: while (true) {
				pc &= 31; //pc를 5비트로 유지
				adder &= 255; //adder를 8비트로 유지
				switch ((arr[pc] & 224) / 32) { //arr[pc]에서 6~8번째 비트만 추출하여 32로 나눈 몫
				case 0:
					arr[arr[pc] & 31] = adder; //arr에 접근할때는 인덱스를 5비트로 만들고 접근
					pc++;
					break;
				case 1:
					adder = arr[arr[pc] & 31]; //arr에 접근할때는 인덱스를 5비트로 만들고 접근
					pc++;
					break;
				case 2:
					if (adder == 0) {
						pc = arr[pc]; //jump하는 경우에는 pc를 증가시키면 안됨
					} else {
						pc++;
					}
					break;
				case 3:
					pc++;
					break;
				case 4:
					adder--;
					pc++;
					break;
				case 5:
					adder++;
					pc++;
					break;
				case 6:
					pc = arr[pc]; //jump하는 경우에는 pc를 증가시키면 안됨
					break;
				case 7:
					break simul;
				}
			}
			for (int i = 7; i >= 0; i--) {
				bw.write(((1 << i) & adder) == 0 ? '0' : '1'); //1바이트 길이의 2진수 출력
			}
			bw.write('\n');
		}
		bw.flush();
		bw.close();
	}

}
