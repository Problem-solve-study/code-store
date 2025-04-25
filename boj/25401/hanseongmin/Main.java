import java.io.*;

/*
13016KB, 236ms

처음엔 초항과 공차를 고정시킨 후 브루트포스로 구하려고 했다. 그런데 수의 범위가 200만이기 때문에 바로 기각.
배열에서 각 원소 중 두 개를 고정시킨 후 바꿀 카드를 전부 탐색하면 N^3으로 아슬아슬하게 돌아갈 것 같다는 생각이 들었다.
원소 2개를 고정시킨다면 공차가 정해지므로 등차수열이 유일하게 결정되기 때문
N^3치고는 상당히 빠르게 돌아가는 듯 하다
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }

        int res = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) { //고정 시킬 숫자1
            for (int j = i + 1; j < N; j++) { //고정 시킬 숫자2
                int diff = arr[j] - arr[i];
                int adjustDiff = diff / (j - i);
                //불가능한 공차일 경우(공차가 정수가 아닌 실수인 경우) 넘어감
                if (arr[i] + adjustDiff * (j - i) != arr[j]) continue;

                //바꿔야 할 카드 카운팅
                int cnt = 0;
                int prev = arr[i];
                for (int k = i - 1; k >= 0; k--) {
                    if (arr[k] != prev - adjustDiff) {
                        cnt++;
                    }
                    prev -= adjustDiff;
                }

                prev = arr[i];
                for (int k = i + 1; k < N; k++) {
                    if (k != j && arr[k] != prev + adjustDiff) {
                        cnt++;
                    }
                    prev += adjustDiff;
                }
                res = Math.min(res, cnt);
            }
        }
        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
