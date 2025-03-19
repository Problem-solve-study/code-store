// 11544KB	64ms
import java.io.*;
import java.util.*;

public class Main {
    static int d;
    static String num;
    static long hor, ver;
    static StringBuilder sb = new StringBuilder();
    
    /**
     * 1. 현재 위치의 좌표, 크기 찾고
     * 2. 이동시키기
     * 3. 이동한 좌표의 숫자 찾기
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        d = Integer.parseInt(st.nextToken());
        num = st.nextToken();
        st = new StringTokenizer(br.readLine());
        hor = Long.parseLong(st.nextToken());
        ver = Long.parseLong(st.nextToken());
        long size = ((long)1<<d);
        long[] pos = findBase(size);

        long ny = pos[0] - ver;
        long nx = pos[1] + hor;
        if (ny >= 0 && ny < size && nx >= 0 && nx < size) {
            divideNconquer(ny, nx, size, 0);
            System.out.print(sb);
        } 
        else {
            System.out.print(-1);
        }
    }

    static long[] findBase(long size) {
        long x = 0, y = 0;
        for (int i=0; i<d; i++) {
            size /= 2;
            char c = num.charAt(i);
            if (c == '1') {
                x += size;
            }
            else if (c == '3') {
                y += size;
            }
            else if (c == '4') {
                x += size;
                y += size;
            }
        }
        return new long[] {y, x};
    }

    static void divideNconquer(long y, long x, long size, int cnt) {
        if (cnt == d) {
            return;
        }

        size /= 2;
        if (y < size && x >= size) {
            sb.append('1');
            divideNconquer(y, x - size, size, cnt + 1);
        }
        else if (y < size && x < size) {
            sb.append('2');
            divideNconquer(y, x, size, cnt + 1);
        }
        else if (y >= size && x < size) {
            sb.append('3');
            divideNconquer(y - size, x, size, cnt + 1);
        }
        else if (y >= size && x >= size) {
            sb.append('4');
            divideNconquer(y - size, x - size, size, cnt + 1);
        }
    }
}