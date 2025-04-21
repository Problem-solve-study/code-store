// 319620KB 1636ms

import java.io.*;
import java.util.*;

/*
 * 무너지는 층수와 무너지게 된 최고층을 찾았습니다.
 * 
 * 1층은 항상 무너지므로 1층의 빗물 양으로 무너지는지 확인하고,
 * 무너진다면 저층의 입력부터 빼면서 빼냈을 때 k 이하로 떨어질 때 그 전 층수를 출력합니다.
 * 
 * m + m
 */
class Main {
    static final StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();

        int sum = 0;
        List<int[]> rains = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int level = nextInt();
            int amount = nextInt();
            sum += amount;
            rains.add(new int[]{level, amount});

            if (sum > k) {
                break;
            }
        }

        if (rains.size() == m && sum <= k) {
            System.out.println(-1);
            return;
        }

        Collections.sort(rains, (r1, r2) -> r1[0] - r2[0]);

        // for (int i = 0; i < rains.size(); i++) {
        //     System.out.println(Arrays.toString(rains.get(i)));
        // }

        int prevLevel = rains.get(0)[0];
        System.out.print(rains.size() + " ");
        for (int i = 0; i < rains.size(); i++) {
            sum -= rains.get(i)[1];
            if (sum < k) {
                System.out.println(prevLevel);
                return;
            }
            prevLevel = rains.get(i)[0];
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
