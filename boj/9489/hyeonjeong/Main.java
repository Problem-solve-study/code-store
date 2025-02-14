// 227596KB, 936ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static int[][] nodes;

    static final int NUMBER = 0;
    static final int PARENT = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            if (N == 0 && K == 0)
                break;
            
            // 루트 초기화
            st = new StringTokenizer(br.readLine(), " ");
            nodes = new int[N][2];
            nodes[0][0] = Integer.parseInt(st.nextToken());
            nodes[0][1] = -1;
            if (K == nodes[0][0]) {
                System.out.println(0);
                continue;
            }

            // 노드 추가
            int parent = -1;
            int ki = 0;
            for (short i = 1; i < N; i++) {
                int current = Integer.parseInt(st.nextToken());
                nodes[i][0] = current;
                nodes[i][1] = parent;

                // 연속되지 않으면 부모 교체
                if (current != nodes[i - 1][0] + 1) {
                    nodes[i][1] = ++parent;
                }

                if (current == K) {
                    ki = i;
                }
            }
            
            int count = countCousin(ki);
            System.out.println(count);
        }
    }

    static int countCousin(int ki) {
        // K가 루트의 자식이면 종료
        int pi = nodes[ki][PARENT];
        if (pi == 0)
            return 0;

        // 부모의 형제 찾기
        int[] siblings = new int[N];
        int size = 0;
        int si = pi - 1;
        while (si >= 0 && nodes[si][PARENT] == nodes[pi][PARENT]) {
            siblings[size++] = si--;
        }
        si = pi + 1;
        while (si < N && nodes[si][PARENT] == nodes[pi][PARENT]) {
            siblings[size++] = si++;
        }
        
        // 부모의 형제를 부모로 하는 자식 찾기
        boolean flag;
        int count = 0;
        int ci = ki + 1;
        while (ci < N) {
            if (nodes[ci][PARENT] == pi) {
                ci++;
                continue;
            }

            flag = false;
            for (int i = 0; i < size; i++) {
                if (siblings[i] == nodes[ci][PARENT]) {
                    count++;
                    ci++;
                    flag = true;
                    break;
                }
            }

            if (flag == false)
                break;
        }

        ci = ki - 1;
        while (ci >= 0) {
            if (nodes[ci][PARENT] == pi) {
                ci--;
                continue;
            }

            flag = false;
            for (int i = 0; i < size; i++) {
                if (siblings[i] == nodes[ci][PARENT]) {
                    count++;
                    ci--;
                    flag = true;
                    break;
                }
            }

            if (flag == false)
                break;
        }
        return count;
    }
}

// 22 28
// 1 / 3, 4, 5 / 8 9(3), 11 12(4), 14 15(5) / 17 18 19(8), 21 22(9), 24 25(11), 27 28 29(12), 31 32(14)
// 22 28
// 1 3 4 5 8 9 11 12 14 15 17 18 19 21 22 24 25 27 28 29 31 32