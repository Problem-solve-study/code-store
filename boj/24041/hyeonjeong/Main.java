// 295580KB 3776ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

// 날짜에 대해 이분탐색 + 세균 개수를 구해서 적은 애들부터 넣어서 채우기
// 세균수가 적고 G가 크면, 유통기한이 지나도 계속 먹을 수 있음 -> 이분탐색 범위를 크게 잡아야 한다.
// log(2,000,000,000) * 200,000 * log(200,000)? 정도면 될 듯?
class Main {
    static final int SPEED = 0;
    static final int GERM = 0;
    static final int OPTIONAL = 1;
    static final int LIFE = 2;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    static int n;
    static int g;
    static int k;
    static int[][] foods;
    static int maxDate = 0;

    public static void main(String[] args) throws IOException {
        n = nextInt();
        g = nextInt();
        k = nextInt();
        foods = new int[n][3];
        for (int i = 0; i < n; i++) {
            foods[i][SPEED] = nextInt();
            foods[i][LIFE] = nextInt();
            foods[i][OPTIONAL] = nextInt();
        }

        searchDate(0, Integer.MAX_VALUE);

        System.out.println(maxDate);
    }

    static void searchDate(int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + ((right - left) / 2);
        long germ = getGerms(mid);

        if (germ <= g) {
            if (mid > maxDate) {
                maxDate = mid;
            }

            searchDate(mid + 1, right);
            return;
        }

        searchDate(left, mid);
    }

    static long getGerms(int date) {
        long[][] todayFoods = new long[n][2];
        for (int i = 0; i < n; i++) {
            todayFoods[i][OPTIONAL] = foods[i][OPTIONAL];
            todayFoods[i][GERM] = (long) foods[i][SPEED] * Math.max(date - foods[i][LIFE], 1);
        }

        Arrays.sort(todayFoods, (f1, f2) -> {
            if (f1[OPTIONAL] != f2[OPTIONAL])
                return f1[OPTIONAL] < f2[OPTIONAL] ? -1 : 1;

            if (f1[GERM] == f2[GERM]) {
                return 0;
            }
            if (f1[GERM] < f2[GERM]) {
                return -1;
            }
            return 1;
        });

        long sum = 0;
        for (int i = 0; i < n - k; i++) {
            sum += todayFoods[i][GERM];
        }

        int i = n - k;
        while (i < n && todayFoods[i][OPTIONAL] == 0) {
            sum += todayFoods[i][GERM];
            i++;
        }

        // System.out.println(date);
        // for (i = 0; i < n; i++) {
        //     System.out.println(Arrays.toString(todayFoods[i]));
        // }
        // System.out.println(sum);
        // System.out.println();

        return sum;
    }

    static int nextInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }
}
