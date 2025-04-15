
// 40944KB	1312ms
import java.io.*;
import java.util.*;

/**
 * 우선 구해야하는 수위 범위가 매우 크고 입력값을 봤을때 nlogn 안으로 풀어야 함.
 * 구매 후 x일 에 비례하게 세균의 수가 늘어남 => 이분탐색
 * mid를 바꿔가며 각각의 세균 수를 계산 후 오름차순 정렬했고, 앞에서부터 n-k 개의 세균의 합과 g를 비교하며 이분탐색 진행
 * 목표값과 같은 것 중 가장 큰 mid 를 찾아야 하므로 upperbound - 1을 구함
 * 
 * [오답노트]
 * 1. 세균 수 정렬을 매 턴마다 해야하는데 1번만해서 1틀
 * 2. o == 0 인 재료는 반드시 포함해야 하는데 n-k개만 포함해서 1틀
 * 3. 유통기한이 전부 지났어도 세균 수가 G 보다 작아서 가능한 경우가 있다. => 이분탐색 범위를 1e9로 잡아서 1틀
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n, g, k;
    static int[][] map;
    static int num, essential;
    static Comparator<int[]> comparator = new Comparator<int[]>() {
        @Override
        public int compare(int[] n1, int[] n2) {
            long result1 = (long) n1[2] * n1[0] * Math.max(1, num - n1[1]); // 필수 재료는 항상 앞에 포함하기 위해 n[0] 을 곱함
            long result2 = (long) n2[2] * n2[0] * Math.max(1, num - n2[1]);
            return Long.compare(result1, result2);
        }
    };

    public static void main(String[] args) throws IOException {
        n = nextInt();
        g = nextInt();
        k = nextInt();
        map = new int[n][3];
        for (int i = 0; i < n; i++) {
            int s = nextInt();
            int l = nextInt();
            int o = nextInt();
            map[i] = new int[] { s, l, o };
            if (o == 0)
                essential++;
        }

        System.out.print(binarySearch());
    }

    // upperbound - 1
    static int binarySearch() {
        int left = 0, right = 2_000_000_001;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (caculate(mid) <= g) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left - 1;
    }

    static long caculate(int mid) {
        num = mid;
        Arrays.sort(map, comparator);
        long result = 0;
        for (int i = 0; i < Math.max(essential, n - k); i++) {
            result += (long) map[i][0] * Math.max(1, mid - map[i][1]);
        }
        return result;
    }

    // fast i/o
    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
