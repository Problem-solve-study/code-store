import java.io.*;
import java.util.*;

/*
281036KB, 796ms

그냥 시뮬레이션하면 쉽게 구할 수 있는데 N의 제한이 10만을 넘어가서 풀 수가 없음
문제에서 B의 값은 오름차순으로, 즉 정렬된 상태로 주어진다고 하였으므로 이분탐색을 사용하라는 것을 쉽게 캐치 가능

범위가 int를 넘어갈 수 있다는 것에 유의.
upper bound를 구해야하는데 double 배열로 변환 후 upper bound를 찾는 꼼수가 long 범위로 넘어가면 오작동한다는 걸 처음 알았음
아무래도 엄청나게 큰 값 + 0.5가 실수 오차 때문에 같은 값으로 취급되는 듯. 수 범위가 크면 이분 탐색 직접 구현할 것
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[] A = new long[N];
        long[] B = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Long.parseLong(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Long.parseLong(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int idx = bs(B, A[i]);
            sb.append(Math.max(idx - i, 0)).append(' ');
        }
        System.out.print(sb);
    }

    static int bs(long[] arr, long key) {
        int s = 0;
        int e = arr.length - 1;
        int mid;
        while (s <= e) {
            mid = (s + e) / 2;
            if (arr[mid] <= key) {
                s = mid + 1;
            } else {
                e = mid - 1;
            }
        }
        return e;
    }
}
