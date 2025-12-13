// 	11644KB	80ms

import java.util.*;
import java.io.*;

/**
 * 10만 이하의 소수를 초기화 한 후, slice 크기를 5에서 1로 줄여가며 부분문자열에 대해 소수 판정
 */
class Main {
    static final int MAX_PRIME = 100_000;
    static boolean[] isNotPrime = new boolean[MAX_PRIME + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        initializePrime();
        // System.out.println(Arrays.toString(isNotPrime));

        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = br.readLine();
            if (line.equals("0")) {
                break;
            }
            
            int maxPrime = 0;
            for (int sliceSize = 5; sliceSize > 0; sliceSize--) {
                for (int i = 0; i < line.length() - sliceSize; i++) {
                    int curr = Integer.parseInt(line.substring(i, i + sliceSize));

                    if (!isNotPrime[curr]) {
                        maxPrime = Math.max(curr, maxPrime);
                    }
                }

                if (maxPrime != 0) {
                    break;
                }
            }
            
            sb.append(maxPrime).append('\n');
        }

        System.out.println(sb);
    }

    static void initializePrime() {
        for (int n = 4; n <= MAX_PRIME; n++) {
            for (int q = 2; q <= Math.sqrt(n); q++) {
                if (n % q == 0) {
                    isNotPrime[n] = true;
                    break;
                }
            }
        }
    }
}
