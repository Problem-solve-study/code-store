import java.io.*;
import java.util.*;

/*
14444KB, 104ms

k의 수가 작아 완전탐색하는 문제임을 쉽게 유추할 수 있고 선택한 추를 왼쪽에 올릴지 오른쪽에 올리는 경우를 고려해주면 된다.
왼쪽에 올리는 경우는 현재 값에서 빼는 것으로, 오른쪽에 올리는 경우는 현재 값에서 더하는 것으로 처리할 수 있다.
측정 불가능한 가장 작은 수를 고르는 문제인줄 알고 한참을 삽질했다..
측정한 수를 Set에 담았다가 시간초과 나길래 최적화를 좀 했더니 시간이 상당히 빨리 나왔다.
 */

public class Main {
    static int k;
    static int[] arr;
    static boolean[] counted;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        k = Integer.parseInt(br.readLine());
        arr = new int[k];

        int sum = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
        }
        counted = new boolean[sum + 1];

        Arrays.sort(arr);
        findNumber(0, 0);

        int cnt = 0;
        for (int i = 1; i <= sum; i++) {
            if (!counted[i]) {
                cnt++;
            }
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
    }

    public static void findNumber(int idx, int value) {
        counted[value] = true;

        for (int i = idx; i < k; i++) {
            findNumber(i + 1, value + arr[i]);
            findNumber(i + 1, Math.abs(value - arr[i]));
        }
    }
}