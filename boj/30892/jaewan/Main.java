
/*
 * 내 크기보다 작은 최대 크기의 상어를 잡아먹기.
 * 이진탐색으로 내 크기보다 작은 최대 크기의 상어 위치를 찾음.
 */
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    static int N, K;
    static Long size;
    static ArrayList<Integer> sharks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = readInt();
        K = readInt();
        int T = readInt();
        size = (long) T;

        for (int i = 0; i < N; i++)
            sharks.add(readInt());

        Collections.sort(sharks);

        for (int i = 0; i < K; i++) {
            // 현재 크기보다 작은 상어 중 가장 큰 상어를 찾음.
            int t = search(0, sharks.size() - 1);
            // 없는 경우
            if (t < 0)
                break;
            size += sharks.get(t);
            sharks.remove(t);
        }
        System.out.println(size);
    }

    static int search(int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (sharks.get(mid) < size)
                left = mid + 1;
            else
                right = mid - 1;
        }
        return right;
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