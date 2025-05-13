// 18308KB 184ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String key = br.readLine();
        String encryption = br.readLine();

        int keyLength = key.length();
        int encryptionLength = encryption.length();
        int lineCount = encryptionLength / keyLength;

        // 키 정렬
        int[][] sortedKey = new int[keyLength][2];
        for (int i = 0; i < keyLength; i++) {
            sortedKey[i][0] = key.charAt(i);
            sortedKey[i][1] = i;
        }
        Arrays.sort(sortedKey, (k1, k2) -> k1[0] - k2[0]);

        // 암호문 입력
        char[][] tokens = new char[lineCount][keyLength];
        for (int line = 0; line < lineCount; line++) {
            for (int kj = 0; kj < keyLength; kj++) {
                tokens[line][kj] = encryption.charAt(kj * lineCount + line);
            }
        }

        // 복호화: 원래 kj번째 열이었던 tj번째 열을 kj열에 저장
        char[][] plain = new char[lineCount][keyLength];
        for (int kj = 0; kj < keyLength; kj++) {
            for (int tj = 0; tj < keyLength; tj++) {
                if (sortedKey[tj][1] != kj) {
                    continue;
                }

                for (int idx = 0; idx < lineCount; idx++) {
                    plain[idx][kj] = tokens[idx][tj];
                }

                break;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < keyLength; j++) {
                sb.append(plain[i][j]);
            }
        }

        System.out.println(sb);
    }
}
