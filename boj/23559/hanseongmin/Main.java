import java.io.*;
import java.util.*;

/*
26384KB, 496ms

아 냅색 기본 문제인가보다 하고 풀다가 가격의 수가 너무 커진다는걸 발견함.
N이 10만이니까 브루트포스는 절대 안되겠고 DP도 막혔다면 그리디인가? 싶어서 그리디로 접근

N의 최솟값이 1000N이므로 반드시 모든 날에 1000원짜리 학식은 먹을 수 있음
따라서 일단 1000원 짜리를 먹는다고 생각을 하고 5000원을 먹는 것이 이득이면 5000원을 먹는 것이 기본 아이디어

1000원짜리 학식도 충분히 가치있는 학식이라면 그때는 5000원을 먹지 않는 것이 도리어 이득일 수 있음.
따라서 1000원 짜리와 비교해서 5000원 짜리가 훨씬 더 맛있는 경우 5000원 선택한다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt(); int X = nextInt();
        int sum = 0;
        int[][] menus = new int[N][2];
        for (int i = 0; i < N; i++) {
            menus[i][0] = nextInt();
            menus[i][1] = nextInt();
            sum += menus[i][1];
        }

        //5000원과 1000원 짜리의 맛의 차이로 정렬
        Arrays.sort(menus, Comparator.comparingInt((int[] arr) -> -(arr[0] - arr[1])));
        //5000원을 얼마나 많이 선택할 수 있는지
        int cnt5000 = (X - (1000 * N)) / 4000;
        for (int i = 0; i < cnt5000; i++) {
            //차이로 정렬했다하더라도 1000원짜리가 모든 날에 대하여 5000원보다 맛있을 수 있으므로
            //5000원이 확실히 더 맛있어서 답이 더 커지는 경우만 값을 변화
            if (menus[i][0] > menus[i][1]) {
                sum -= menus[i][1];
                sum += menus[i][0];
            }
        }
        System.out.print(sum);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
