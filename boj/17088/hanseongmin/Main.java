import java.io.*;

/*
19888KB, 184ms

등차수열의 식은 초항과 공차로 이루어져 있으므로 해당 두 수를 적절히 조절하며 확인해보면 됨
초항으로 가능한 값 3개, 공차로 가능한 값 3개 9가지의 경우의 수를 따져보며 수열을 만들 수 있는지 없는지 체크
수열의 체크는 O(N)에 가능하므로 9N에 문제를 풀 수 있고 N의 최댓값이 100,000이므로 1초 안에 문제 해결 가능
배열의 크기가 1일 때를 조심
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);
    static int res = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nextInt();
        }

        if (n == 1) {
            bw.write(String.valueOf(0));
        } else {
            //초항과 공차를 조절
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int cnt = 0;
                    if (i != 0) cnt++;
                    if (j != 0) cnt++;

                    int[] temp = arr.clone();
                    temp[0] += i;
                    temp[1] += j;
                    check(temp, cnt);
                }
            }

            bw.write(String.valueOf(res == Integer.MAX_VALUE ? -1 : res));
        }
        bw.flush();
    }

    static void check(int[] arr, int initCnt) {
        //배열의 크기가 3 미만이라면 그 자체로 등차수열
        if (arr.length < 3) {
            res = 0;
            return;
        }

        int cnt = initCnt;
        //공차
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            //등차수열의 식을 이용하여 현재 항이 어떤 값인지를 구함
            int targetV = arr[0] + diff * i;
            //조절할 수 있는 값은 1뿐이므로 diff 값을 계산하여 1인지 아닌지를 판별
            int curDiff = Math.abs(targetV - arr[i]);
            //1을 초과할 경우 1을 더하거나 빼서 해당 수를 만들 수 없으므로 등차수열을 만들 수 없다.
            if (curDiff > 1) return;
            if (curDiff == 1) cnt++;
        }

        res = Math.min(res, cnt);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
