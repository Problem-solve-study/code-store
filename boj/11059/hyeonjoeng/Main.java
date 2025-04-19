import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 완전탐색
 * 누적합 & 슬라이딩 윈도우
 */
class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();

        int length = line.length();

        int[] numbers = new int[length + 1];
        numbers[0] = 0;
        for (int i = 1; i <= length; i++) {
            numbers[i] = numbers[i - 1] + line.charAt(i - 1) - '0';
        }

        for (int size = length % 2 == 0 ? length : length - 1; size > 0; size -= 2) {
            for (int i = 0; i + size <= length; i++) {
                int mid = i + size / 2;
                if (numbers[mid] - numbers[i] == numbers[i + size] - numbers[mid]) {
                    System.out.print(size);
                    return;
                }
            }
        }
    }
}
