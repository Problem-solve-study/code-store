// 	33996KB	348ms
import java.io.*;
import java.util.*;

/**
 * 전체 점수를 오름차순으로 정렬하고
 * 이분 탐색으로 lower bound를 찾음
 */
public class Main {
    static int n, num[];
    static long answer = 0L;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        num = new int[n];
        for (int i=0; i<n; i++) {
            num[i] = Integer.parseInt(st.nextToken()) * 10;
        }
        Arrays.sort(num);
        for (int i=1; i<n; i++) {
            answer += (i - bSearch((int) (num[i] * 0.9)));
        }
        System.out.print(answer);
    }

    static int bSearch(int target) {
        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;

            if (num[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}