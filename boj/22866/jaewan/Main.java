/*
 * 각 건물 옥상에서 양 옆 건물 몇 개 볼수 있는지.
 * 
 * 높이가 큰 건물만 볼 수 있음.
 * 더 높은 건물이 있으면 가려져서 보이지 않음.
 * 
 * 각 건물에서 볼 수 있는 건물의 개수를 출력.
 * 볼 수 있는 건물의 개수가 1개 이상이면, 거리가 가장 가까운 건물의 번호 중 작은 번호로 출력.
 * 
 * 스택으로 기록하면서 볼 수 있는 건물의 개수를 count.
 * 순방향 한번, 역방향 한번
 * 
 * 더 큰 건물에 가려지면 다음엔 볼 수 없음.
 * 가까운 건물의 번호 기록.
 */

import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] heights = new int[N], resCnt = new int[N], resIdx = new int[N];
        Arrays.fill(resIdx, -9999999);

        for (int i = 0; i < N; i++)
            heights[i] = readInt();

        int top = 0;
        int[] stack = new int[N + 1];

        for (int i = 0; i < N; i++) {
            while (top > 0 && heights[stack[top]] <= heights[i])
                top--;

            if (top > 0) {
                resCnt[i] += top;
                resIdx[i] = stack[top];
            }
            stack[++top] = i;
        }

        top = 0;
        for (int i = N - 1; i >= 0; i--) {
            while (top > 0 && heights[stack[top]] <= heights[i])
                top--;

            resCnt[i] += top;

            if (top > 0 && stack[top] - i < i - resIdx[i])
                resIdx[i] = stack[top];
            stack[++top] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(resCnt[i]);
            if (resCnt[i] > 0)
                sb.append(' ').append(resIdx[i] + 1);
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public class Building {
        int num, height;

        public Building(int num, int height) {
            this.num = num;
            this.height = height;
        }
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}