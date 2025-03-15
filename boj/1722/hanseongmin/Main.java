import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

/*
18448KB, 188ms

간단한 수학 문제
20!까지 가능하므로 next_permutation으로 구하는 것은 불가능

1 2 3 4에서 2 1 3 4로 바뀌었다면 그 사이에 순열이 몇개가 있었을까를 생각해보면
기준으로 잡고있는 1번째자리 수를 제외한 나머지의 순열만큼 바뀐 것이다.
즉 1 2 3 4 -> 2 1 3 4라면 3!인 6개만큼이 바뀐 것이며 1 2 3 4 -> 3 1 2 4라면 2 * 3!으로 12개만큼 바뀐 것이다.
이 특성을 이용하여 적절히 구해주면 된다.
 */

public class Main {
    static int N;
    static long[] factorials;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        getFactorials();

        StringTokenizer st = new StringTokenizer(br.readLine());
        int order = Integer.parseInt(st.nextToken());
        if (order == 1) {
            getAns(Long.parseLong(st.nextToken()));
        } else {
            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            getAns2(arr);
        }

        System.out.println(sb);
    }

    //k번째 순열 구하기
    static void getAns(long k) {
        int[] arr = new int[N];
        boolean[] v = new boolean[N + 1];
        IntStream.rangeClosed(1, N).forEach(i -> arr[i - 1] = i);

        //N번째자리 확정
        for (int i = 0; i < N; i++) {
            long prev = 0; //이전에 몇 번째만큼 스킵할 수 있는지 체크
            int cnt = 0; //현재 자릿수에서 몇 번째 수인지 체크
            //n번째 자리에서 어떤 수가 들어오는지 탐색
            for (int j = 1; j <= N; j++) {
                //현재 숫자가 다른 자리에서 배치됐다면 넘어감
                if (v[j]) continue;
                //마지막 자릿수라면 그냥 배치하면 된다.
                if (i == N - 1) {
                    arr[i] = j;
                    break;
                }

                cnt++;
                //현재 자릿수에서 j + 1를 배치하면 몇 번째 순열로 바뀌는지를 계산
                long cur = cnt * factorials[(N - i - 1)];
                //prev <= k <= cur를 만족하면 현재 자리에 j를 배치한다.
                if (prev <= k && k <= cur) {
                    v[j] = true;
                    arr[i] = j;
                    k -= prev;
                    break;
                } else {
                    prev = cur;
                }
            }
        }

        Arrays.stream(arr).forEach(n -> sb.append(n).append(' '));
    }

    //몇 번째 순열인지 구하기
    static void getAns2(int[] arr) {
        long k = 0;

        boolean[] v = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            int cnt = 0;
            for (int j = 1; j <= N; j++) {
                if (v[j]) continue;
                if (arr[i] != j) {
                    cnt++;
                    continue;
                }
                if (i == N - 1) {
                    k++;
                }

                v[j] = true;
                //현재 자리에 j를 배치하였을 때 스킵할 수 있는 수만큼 k에 더해줌
                k += cnt * factorials[(N - i - 1)];
                break;
            }
        }
        sb.append(k);
    }

    //각 수의 팩토리얼 값을 미리 계산
    static void getFactorials() {
        factorials = new long[N + 1];
        long n = 1;
        factorials[1] = n;
        for (int i = 2; i <= N; i++) {
            factorials[i] = i * factorials[i - 1];
        }
    }
}
