//제출번호: 96073611
//메모리:   11636 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//1번 경기에서 들어온 등수에 맞게 배열을 만들고
//그 중 m명을 잘라서 2번 경기의 순서대로 수행
//그 후 금은동을 출력 
public class Main {
    public static void main(String[] args) throws IOException {
        int n = nextInt(), m = nextInt();
        List<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(nextInt()-1, i + 1);
        }

        int[] res = new int[3];
        for (int i = m-1; i >= 0; i--) {
            int rank = nextInt();

            if (rank == 3) {
                res[2] = arr.get(i);
            } else if (rank == 2) {
                res[2] = res[1];
                res[1] = arr.get(i);
            } else if (rank == 1) {
                res[2] = res[1];
                res[1] = res[0];
                res[0] = arr.get(i);
            }
        }

        System.out.printf("%d%n%d%n%d", res[0], res[1], res[2]);
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}