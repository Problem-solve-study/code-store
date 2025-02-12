import java.io.*;
import java.util.*;

/*
11676KB, 64ms

독해가 제일 어려웠다..
1. 입력으로 주어지는 코드들이 메모리 각각의 인덱스에 차례대로 저장된다.
2. PC의 값은 현재 실행할 코드의 인덱스 값이다.

위 2개만 캐치했다면 그 다음은 순수 구현 문제에 가까워 그대로 구현하면 된다.
메모리 제한이 조금 빡세길래 String 사용을 최대한 자제하고 char[]를 사용했다.

어셈블리어를 잘 몰라서 문제 이해하는데 조금 많은 시간을 썼는데
어셈블리어에 대해 조금 알고있었으면 수월하게 했을듯.
 */

public class Main {
    static final byte STA = 0b000;
    static final byte LDA = 0b001;
    static final byte BEQ = 0b010;
    static final byte NOP = 0b011;
    static final byte DEC = 0b100;
    static final byte INC = 0b101;
    static final byte JMP = 0b110;
    static final byte HLT = 0b111;
    static byte[] memory = new byte[32];
    static byte adder = 0;
    static byte pc = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder sb = new StringBuilder();
        loop:
        while (true) {
            //메모리, 가산기, PC 초기화
            memory = new byte[32];
            adder = 0;
            pc = 0;

            //테스트케이스 입력
            for (int i = 0; i < 32; i++) {
                String code = br.readLine();
                if (code == null) {
                    break loop;
                }
                memory[i] = (byte) Integer.parseInt(code, 2);
            }

            program:
            while (true) {
                //연산자와 피연산자는 비트연산을 통해 파싱 (fetch)
                byte operator = (byte) ((memory[pc] & (0xE0)) >> 5);
                byte operand = (byte) (memory[pc] & (0x1f));

                //명령어를 실행시키기 전에 PC 값을 늘림
                pc = (byte) ((pc + 1) % 32);
                //주어진 연산들에 대한 단순 구현
                switch (operator) {
                    case STA:
                        memory[operand] = adder;
                        break;
                    case LDA:
                        adder = memory[operand];
                        break;
                    case BEQ:
                        pc = (adder == 0 ? operand : pc);
                        break;
                    case NOP:
                        break;
                    case DEC:
                        adder--;
                        break;
                    case INC:
                        adder++;
                        break;
                    case JMP:
                        pc = operand;
                        break;
                    case HLT:
                        break program;
                }
            }
            //프로그램이 종료됐으므로 현재 가산기 값을 출력
            sb.append(toCharArray(adder)).append('\n');
        }

        bw.write(sb.toString());
        bw.flush();
    }

    static byte[] parsing(String s) {
        char[] charArr = s.toCharArray();
        byte order = 0;
        for (int i = 0; i < 3; i++) {
            order |= (charArr[i] - '0' << (2 - i));
        }

        byte body = 0;
        for (int i = 3; i < 8; i++) {
            body |= (charArr[i] - '0' << (7 - i));
        }

        return new byte[] {order, body};
    }

    static char[] toCharArray(byte b) {
        char[] arr = new char[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = ((b & (1 << (7 - i))) == 0 ? '0' : '1');
        }

        return arr;
    }
}