//제출번호: 96074722
//메모리:   12140 KB
//실행시간: 92 ms
import java.io.*;
import java.util.*;

//좌표값이 크고 k가 작기 때문에 좌표압축을 먼저 생각함
//그리고 L의 좌표가 정해졌을 때 구간 안에 있는 별똥별을 계산하기 위해 누적합을 구현
//L의 좌표는 별똥별이 떨어지는 좌표들(x, y)로 만들 수 있는 모든 좌표들을 L의 우하단으로 잡고
//L 안에 별똥별이 몇 개인지 세면 됨
public class Main {
    public static void main(String[] args) throws IOException {
        int n = nextInt(), m = nextInt(), l = nextInt(), k = nextInt();
        
        int[][] stars = new int[k][];
        TreeSet<Integer> xSet = new TreeSet<>(), ySet = new TreeSet<>();
        for (int i = 0; i < k; i++) {
            int x = nextInt(), y = nextInt();

            xSet.add(x);
            ySet.add(y);
            stars[i] = new int[] {x, y};
        }

        xSet.add(-100001);
        ySet.add(-100001);

        Integer[] xPos = xSet.toArray(new Integer[0]), yPos = ySet.toArray(new Integer[0]);

        int[][] sum = new int[xPos.length][yPos.length];
        for (int[] star : stars) {
            sum[valToIndex(xPos, star[0])][valToIndex(yPos, star[1])]++;
        }

        int res = k;
        for (int x = 1; x < xPos.length; x++) {
            for (int y = 1; y < yPos.length; y++) {
                sum[x][y] += sum[x-1][y] + sum[x][y-1] - sum[x-1][y-1];

                int edgeX = valToIndex(xPos, xSet.lower(xPos[x] - l));
                int edgeY = valToIndex(yPos, ySet.lower(yPos[y] - l));
                int val = sum[x][y] - sum[x][edgeY] - sum[edgeX][y] + sum[edgeX][edgeY];

                res = Math.min(res, k - val);
            }
        }

        System.out.print(res);
    }

    static int valToIndex(Integer[] pos, int val) {
        int left = 0, right = pos.length-1, mid;
        while (left <= right) {
            mid = (left + right) >> 1;
            
            if (pos[mid] <= val) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}