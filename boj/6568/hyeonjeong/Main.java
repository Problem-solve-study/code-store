// 12040kb, 72ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int[] memory = new int[32];
    static int accumulator;
    static int pc;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String line = br.readLine();
        while (line != null) {
            // 메모리 초기화
            for (int i = 0; i < 32; i++) {
                int statement = Integer.parseInt(line, 2);
                memory[i] = statement;
                line = br.readLine();
            }

            // 레지스터 초기화
            accumulator = 0;
            pc = 0;

            procced();

            bw.write(String.format("%8s\n", Integer.toBinaryString(accumulator)).replace(' ', '0'));
        }

        bw.close();
    }

    static void procced() {
        while (true) {
            int op = memory[pc] / 32;
            int value = memory[pc] % 32;

            pc = (pc + 1) % 32;

            switch (op) {
                case 0:
                    memory[value] = accumulator;
                    break;
                case 1:
                    accumulator = memory[value];
                    break;
                case 2:
                    if (accumulator == 0) pc = value;
                    break;
                case 3:
                    break;
                case 4:
                    accumulator = accumulator != 0 ? accumulator - 1 : 255;
                    break;
                case 5:
                    accumulator = (accumulator + 1) % 256;
                    break;
                case 6:
                    pc = value;
                    break;
                case 7:
                    return;
            }
        }
    }
}
