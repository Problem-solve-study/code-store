import java.io.*;
import java.util.*;

/*
41188KB, 648ms

주최자는 수상할 인원을 마음대로 선택할 수 있고 심판은 반드시 그리디하게 선택하므로
먼저 심판이 선택하게 한 후 남은 인원 중에서 그리디하게 주최자가 선택한다.

만일 특정 원소가 주최자 기준 상위 M개, 심판 기준 상위 N개에 모두 포함될 경우
해당 원소는 주최자가 선택하지 않아도 알아서 심판에 의해 골라지게 되므로 해당 원소는 주최자가 고르지 않고
심판이 고르게 한 후 주최자는 심판이 고르고 남은 원소들 중에서 그리디하게 고르는게 최선이다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int M = nextInt();
        int K = nextInt();
        boolean[] v = new boolean[N];

        //배열에 저장할까 하다가 그냥 힙에 바로 넣어도 될 것 같아 힙으로 풀이
        //h1 -> (인덱스, 주최자 기준 점수), 주최자 기준 점수 기준 정렬
        //h2 -> (인덱스, 주최자 기준 점수, 심판 기준 점수), 심판 기준 점수 정렬
        PriorityQueue<int[]> h1 = new PriorityQueue<>(Comparator.comparingInt(arr -> -arr[1]));
        PriorityQueue<int[]> h2 = new PriorityQueue<>(Comparator.comparingInt(arr -> -arr[2]));
        for (int i = 0; i < N; i++) {
            int a = nextInt();
            int b = nextInt();
            h1.add(new int[] {i, a});
            h2.add(new int[] {i, a, b});
        }

        //10^9를 더할 경우 int를 넘어갈 수 있으므로 long
        long sum = 0;

        //먼저 심판이 고름
        for (int i = 0; i < K; i++) {
            int[] arr = h2.remove();
            v[arr[0]] = true;
            sum += arr[1];
        }

        //이후 남은 작품에 대해 주최자가 고름
        for (int i = 0; i < M; i++) {
            int[] arr = h1.remove();
            //심판이 이미 고른 작품이라면 넘어감
            if (v[arr[0]]) {
                i--;
                continue;
            }
            v[arr[0]] = true;
            sum += arr[1];
        }

        System.out.println(sum);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
