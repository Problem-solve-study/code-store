// 14148 KB, 140 ms
/*
 * 임의의 위치 두 개 swap 으로 같은 모양들끼리 연속하도록 정돈
 * 필요한 맞바꾸기의 최소 횟수를 출력
 * 
 * 전체 개수 3 <= N <= 100,000
 * 
 * 연속되게 배치했을 때, 원래 위치와 다른 것들끼리 swap을 함.
 * 1 3 3 2 1 1 3 2 를 1 1 1 2 2 3 3 3 으로 배치한다고 하면,
 * 1 위치에 3이 있는 것을 3 위치에 1 이 있는 것과 swap.
 * 만약 없으면 2 위치에 1 있는 것과 swap.
 * 그렇게 하나씩 올바른 위치를 찾음.
 * 
 * 시간복잡도는 O(8 x N)
 * 
 */

import java.io.IOException;

public class Main {
    static int N, min = Integer.MAX_VALUE;
    static int[] numCnt = new int[4], nums;
    static int[][] data;

    public static void main(String[] args) throws IOException {
        N = readInt();
        nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = readInt();
            numCnt[nums[i]]++;
        }

        int[] perm = { 1, 2, 3 };
        func(perm);

        while (nextPerm(perm))
            func(perm);

        System.out.println(min);
    }

    static void func(int[] perm) {
        data = new int[4][4];

        int idx = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < numCnt[perm[i]]; j++)
                data[perm[i]][nums[idx++]]++;

        // 연산 횟수 계산
        int cnt = 0;
        // 1부터 제자리로, 2에 있는 1과 1에 있는 2를 swap
        int t = Math.min(data[1][2], data[2][1]);
        cnt += t;
        data[1][2] -= t;
        data[2][1] -= t;

        // 3에 있는 1을 1에 있는 2와 swap
        t = Math.min(data[1][2], data[3][1]);
        cnt += t;
        data[1][2] -= t;
        data[3][1] -= t;
        data[3][2] += t;

        // 1에 있는 3과 3에 있는 1을 swap
        t = Math.min(data[1][3], data[3][1]);
        cnt += t;
        data[1][3] -= t;
        data[3][1] -= t;

        // 1에 있는 3과 2에 있는 1을 swap
        t = Math.min(data[1][3], data[2][1]);
        cnt += t;
        data[1][3] -= t;
        data[2][1] -= t;
        data[2][3] += t;

        // 남은 2에 있는 3과 3에 있는 2를 swap
        t = Math.min(data[2][3], data[3][2]);
        cnt += t;
        data[2][3] -= t;
        data[3][2] -= t;

        min = Math.min(cnt, min);
    }

    static boolean nextPerm(int[] perm) {
        // 꼭대기 찾기
        int len = perm.length;
        int i = len - 1;

        while (i > 0 && perm[i - 1] > perm[i])
            i--;

        if (i == 0)
            return false;

        // 바꿀 위치 찾기
        int j = len - 1;
        while (perm[i - 1] > perm[j])
            j--;

        swap(perm, i - 1, j);

        int k = len - 1;
        while (i < k)
            swap(perm, i++, k--);
        return true;
    }

    static void swap(int[] perm, int i, int j) {
        int t = perm[i];
        perm[i] = perm[j];
        perm[j] = t;
    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}