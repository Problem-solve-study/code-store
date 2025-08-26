//문제: 17124번 두 개의 배열
//메모리: 24160 KB
//시간: 544 ms

/*
 * A의 원소를 순회하며 B의 원소 중 가장 가까운 원소를 찾아야 한다.
 * 이를 위해서 B를 정렬하고 이분탐색을 진행한다.
 * A의 원소와 같은 값이 B에 있다면 mid를 선택하고
 * left가 범위를 벗어나면 right를, right가 범위를 벗어나면 left를
 * 둘 다 유효한 범위라면 문제의 조건에 맞게 비교하여 선택한다.
 *
 * +) n이 최대 10^6이고 원소는 최대 10^9이므로 원소의 합을 구할 때 long을 써야한다.
 */

import java.util.Arrays;
public class Main {
    public static void main(String[] args) throws Exception {
        int test_case = nextInt();
        StringBuilder sb = new StringBuilder();
        while (test_case-- > 0) {
            int n = nextInt(), m = nextInt();
            int[] A = new int[n];
            int[] B = new int[m];
            for (int i = 0; i < n; i++) A[i] = nextInt();
            for (int i = 0; i < m; i++) B[i] = nextInt();
            Arrays.sort(B);

            long result = 0;
            for (int target : A) {
                int left = 0, right = m - 1, mid = 0;
                while (left <= right) {
                    mid = (left + right) >> 1;
                    if (B[mid] < target) left = mid + 1;
                    else if (B[mid] > target) right = mid - 1;
                    else break;
                }
                if (B[mid] == target) result += target;
                else if (left >= m) result += B[right];
                else if (right < 0) result += B[left];
                else result += (target - B[right]) <= (B[left] - target) ? B[right] : B[left];
            }
            sb.append(result).append('\n');
        }
        System.out.println(sb);
    }

    static int nextInt() throws Exception {
        int n, c;
        while ((c = System.in.read()) < 48) ;
        n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}
