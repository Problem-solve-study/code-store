// 11588 KB, 64 ms
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int pc, adder;
	static int[] memory = new int[32];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String line;
			for (int i = 0; i < 32; i++) {
				line = br.readLine();
				if (line == null) return;
				memory[i] = Integer.parseInt(line, 2);
			}
			pc = 0;
			adder = 0;

			run();
			StringBuilder result = new StringBuilder();
			for (int i = 7; i >= 0; i--) result.append((adder >> i) & 1);
			System.out.println(result);
		}
	}

	static void run() {
		while (true) {
			int operator = memory[pc] >> 5;
			int operand = memory[pc] & 0b11111;
			pc = (pc + 1) % 32;
			switch (operator) { 
			case 0:
				memory[operand] = adder;
				break;
			case 1:
				adder = memory[operand];
				break;
			case 2:
				if (adder == 0)
					pc = operand;
				break;
			case 3:
				break;
			case 4:
				adder = (adder + 255) & 0xFF;
				break;
			case 5:
				adder = (adder + 1) & 0xFF;
				break;
			case 6:
				pc = operand;
				break;
			case 7:
				return;
			}
		}
	}
}
