import java.io.*;
import java.util.*;

//주어진 명령을 처리하는 시뮬레이션
//가산기가 항상 양수를 가지도록 코드를 작성해야 ArrayIndexOutOfBounds를 만나지 않을 수 있음
//가산기가 음수면 pc에 음수가 들어가도록 명령어를 구성할 수 있기 때문
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String line;

        while ((line = br.readLine()) != null) {
            int[] commands = new int[32];
            commands[0] = Integer.parseInt(line, 2);
            for (int i = 1; i < 32; i++) {
                commands[i] = Integer.parseInt(br.readLine(), 2);   
            }

            int pc = 0;
            int adder = 0;

            program:
            while (true) {
                int command = commands[pc];
                pc = (pc + 1) % 32;
                int operator = command / 32 % 8; //혹시 몰라서 8로 나눈 나머지 사용
                int operand = command % 32;

                switch (operator) {
                    case 0:
                        //& 연산을 통해 끝 8자리 비트만 가져오도록 함.
                        //모두 int니까 범위는 0~255
                        commands[operand] = adder & 255; break;
                    case 1:
                        adder = commands[operand]; break;
                    case 2:
                        pc = ((adder & 255) == 0) ? operand : pc; break;
                    case 3:
                        break;
                    case 4:
                        adder--; break;
                    case 5:
                        adder++; break;
                    case 6:
                        pc = operand; break;
                    case 7:
                        break program;
                }    
            }

            bw.write(String.format("%s%n", byteTo8bitString(adder)));
        }

        bw.flush();
    }

    static String byteTo8bitString(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            sb.append((num & (1 << i)) != 0 ? 1 : 0);
        }
        return sb.toString();
    }
}