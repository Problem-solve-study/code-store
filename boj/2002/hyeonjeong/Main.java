// 11820KB 72ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        Map<String, Integer> sequence = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String car = br.readLine();
            sequence.put(car, i);
        }

        String[] outgoings = new String[n];
        for (int i = 0; i < n; i++) {
            outgoings[i] = br.readLine();
        }

        // 내 뒤로 나보다 빨리 들어갔던 차가 없으면 추월 X
        int count = 0;
        int minCar = sequence.get(outgoings[n - 1]);
        for (int i = n - 2; i >= 0; i--) {
            int idx = sequence.get(outgoings[i]);

            if (idx < minCar) {
                minCar = idx;
                continue;
            }

            count++;
        }

        System.out.println(count);
    }
}
