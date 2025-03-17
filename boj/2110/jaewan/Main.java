/*
 * 매개 변수 탐색
 * 거리를 left = 1, right = max 로 시작해 범위 변경해가며 만족하는 최대 거리 확인
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int N, C;
    static ArrayList<Integer> list;

    public static void main(String[] args) throws IOException {
        N = readInt();
        C = readInt();
        list = new ArrayList<>(N);
        for (int i = 0; i < N; i++)
            list.add(readInt());
        Collections.sort(list);
        System.out.println(parametricSearch(1, (list.get(N - 1) - list.get(0) / (C - 1))));
    }

    static int parametricSearch(int start, int end) {
        int mid;
        while (start <= end) {
            mid = (start + end) / 2;
            if (isValid(mid))
                start = mid + 1;
            else
                end = mid - 1;
        }
        return end;
    }

    static boolean isValid(int diff) {
        int cnt = 1, prev = list.get(0);
        for (int i = 1; i < N; i++) {
            if (list.get(i) - prev < diff)
                continue;
            cnt++;
            prev = list.get(i);
            if (cnt >= C)
                return true;
        }
        return false;
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }
}
