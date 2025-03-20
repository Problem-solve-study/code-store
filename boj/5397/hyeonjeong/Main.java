// 	98076KB 376ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// 스택 2개를 연결리스트로 활용
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        char[] front;        // 커서 앞의 리스트
        char[] rear;         // 커서 뒤의 리스트
        int frontTop, rearTop;
        for (int t = 0; t < T; t++) {
            char[] input = br.readLine().toCharArray();

            front = new char[input.length];
            rear = new char[input.length];
            frontTop = 0;
            rearTop = 0;

            for (char token : input) {
                if (token == '-') {
                    if (frontTop == 0) {
                        continue;
                    }

                    frontTop--;
                    continue;
                }

                if (token == '<') {
                    if (frontTop == 0) {
                        continue;
                    }
                    rear[rearTop++] = front[--frontTop];
                    continue;
                }

                if (token == '>') {
                    if (rearTop == 0) {
                        continue;
                    }
                    front[frontTop++] = rear[--rearTop];
                    continue;
                }

                front[frontTop++] = token;
            }

            for (int i = 0; i < frontTop; i++) {
                bw.write(front[i]);
            }
            for (int i = rearTop - 1; i >= 0; i--) {
                bw.write(rear[i]);
            }
            bw.write('\n');
        }

        bw.flush();
    }
}
