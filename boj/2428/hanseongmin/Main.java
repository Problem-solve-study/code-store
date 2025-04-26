import java.io.*;
import java.util.*;

/*
23496KB, 272ms

파일의 개수가 10만이기 때문에 단순한 완탐이 의도가 아닐 확률이 높아 보였음
문제의 조건을 만족시키는 범위가 있을 것이라고 생각해서 정렬 후 이분탐색을 사용해야겠다고 생각함
조건을 만족하는 upper bound를 구한 후 쌍의 개수를 한 번에 계산하는 것으로 해결

이분 탐색 구현 실수해서 1틀
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }

        //이분 탐색을 사용하기 위한 정렬
        Arrays.sort(arr);
        long res = 0;
        //i번째 파일을 고정
        for (int i = 0; i < N; i++) {
            //이분 탐색 범위를 [i + 1, N - 1]로 지정하여 반드시 i보다 큰 파일이 나오게 함
            int s = i + 1;
            int e = N - 1;
            int mid;
            while (s <= e) {
                mid = (s + e) / 2;
                if (arr[i] >= 0.9 * arr[mid]) {
                    s = mid + 1;
                } else {
                    e = mid - 1;
                }
            }

            //s에는 upper bound가 저장됨, s - 1인 e까지가 검사해야할 파일의 대상이므로
            //e - i만큼의 쌍을 검사해야함
            res += e - i;
        }
        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
