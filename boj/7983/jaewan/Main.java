
// 49628 KB, 1404 ms
/*
 * 정렬하고 뒤에서부터 배치.
 * 최대한 쉬다가 과제를 시작하기.
 */
import java.io.IOException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        int n = readInt();
        Task[] arr = new Task[n];
        for (int i = 0; i < n; i++)
            arr[i] = new Task(readInt(), readInt());
        Arrays.sort(arr);

        int day = arr[0].t;
        // 역순으로 추적하며 과제를 배치
        for (int i = 0; i < n; i++) {
            // 마감기한 넘었으면 스킵
            if (day > arr[i].t)
                day = arr[i].t;
            day -= arr[i].d;
        }

        System.out.println(day);
    }

    static class Task implements Comparable<Task> {
        int d, t;

        public Task(int d, int t) {
            this.d = d;
            this.t = t;
        }

        @Override
        public int compareTo(Main.Task o) {
            return o.t - this.t;
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