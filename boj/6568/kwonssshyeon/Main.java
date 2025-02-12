// 11596KB	64ms
import java.io.*;

/**
 * 구현 문제
 * 주의할 부분
 * 1. !! 자바에서 음수 모듈러는 음수를 반환한다.
 * 빼기 연산시 음수가 되면 레지스터에 음수가 저장되면서 IndexOutOfBound 발생
 * => 1을 빼는 대신 255(2^8-1) 을 더함으로써 빼기 연산을 구현할 수 있다.
 * 
 * 2. 출력 형식
 * 그냥 십진수를 이진수로 출력하려면 8자리를 다 채우지 못할 수 있다.
 * 패딩을 구현하는것이 필요
 */

public class Main {
    static int[] codes = new int[32];
    static StringBuilder sb = new StringBuilder();
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

		while (true) {
			for (int i = 0; i < 32; i++) {
				if ((input = br.readLine()) == null) {  // 파일입출력에서 EOF를 null로 할 수 있다. (콘솔에서는 ctrl^z로 하면된다.)
					System.out.print(sb.toString());
					return;
				}
				codes[i] = Integer.parseInt(input, 2);
			}
			simulate();
		}
    }

    static void simulate() {
        int pc = 0, adder = 0;
        boolean hlt = false;
        while (!hlt) {
            int command = codes[pc] / 32;
            int operand = codes[pc] % 32;
            pc = (pc+1) % 32;
            switch (command) {
                case 0: 
                    codes[operand] = adder;
                    break;
                case 1: 
                    adder = codes[operand];
                    break;
                case 2: 
                    if (adder == 0) pc = operand;
                    break;
                case 3:
                    break;  
                case 4:
                    adder = (adder + 255) % 256;
                    break;
                case 5:
                    adder = (adder + 1) % 256;
                    break;
                case 6:
                    pc = operand;
                    break;
                case 7:
                    hlt = true;
                    break;
            }
        }
        for (int i=7; i>=0; i--) {
            sb.append((adder >> i) & 1);
        } sb.append("\n");
    }
}