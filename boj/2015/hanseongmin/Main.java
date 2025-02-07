import java.io.*;
import java.util.*;

/*
수열이 1부터 N까지 존재한다고 하자.
1 2 3 4 5 6 7 ... N
만일 내가 5번 인덱스까지 보았다고 한다면 여기서 발생할 수 있는 부분합의 구간들은
1) [1, 5], [2, 5], [3, 5]과 같이 닫힌 구간 내 최댓값이 현재 원소의 인덱스인 경우와
2) [1, 4], [2, 3].. 과 같이 현재 원소가 아닌 경우가 있을 수 있다.

1번인 경우 현재 원소가 더해졌을 때의 누적합 값이 k거나
현재 원소를 k로 만들 수 있는 값이
누적합 값들의 집합 내에 존재한다면 현재 원소 값을 k로 만들 수 있다.

2번의 경우 이전의 경우에서 이미 카운팅이 되었을 것이므로 고려하지 않아도 된다.

즉 현재 원소를 보고 있을 때 해당 원소를 이용하여 k를 만들 수 있는지만 고려하면 된다.

원하는 값이 존재하는지를 빠르게 판단하고 이전에 몇 개의 값이 나왔는지를
기록하기 위해 map을 사용한다.

N이 20만이므로 부분합의 개수가 int형 범위를 넘어갈 수 있음에 주의
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long res = 0;
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] sum = new int[n];
        HashMap<Integer, Long> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            sum[i] = i == 0 ? arr[i] : sum[i - 1] + arr[i];
            res += map.getOrDefault(sum[i] - k, 0L);
            map.put(sum[i], map.getOrDefault(sum[i], 0L) + 1);
            if (sum[i] == k) {
                //res의 카운팅을 현재 누적합 값을 넣기 전에 카운팅하기 때문에
                //현재 누적합이 k라면 카운트 하나 증가
                res++;
            }
        }

        bw.write(String.valueOf(res));
        bw.flush();
    }
}