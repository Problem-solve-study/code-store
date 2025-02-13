import java.io.*;
import java.util.*;

/*
20616KB, 252ms

문제를 처음 보았을 때는 값을 다 더하고 M으로 나누면 되는거 아닌가? 
싶지만 n이 m의 배수가 아닐 경우 남는 전자기기의 충전 시간에 따라 최적해가 되지 못할 수 있다.

남는 전자기기의 충전 시간이 최소가 되게 만들어야 하므로 내림차순으로 정렬한 후 
큰 값부터 차례대로 충전을 시키면 최적해가 될 수 있다.

힙으로 구현하여 AC
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        //내림차순으로 정렬하기 위한 reverseOrder
        PriorityQueue<Integer> h = new PriorityQueue<>(Collections.reverseOrder());
        //스트림으로 값 일괄 삽입
        Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(h::add);
        
        int time = 0;
        while (!h.isEmpty()) {
            //m개를 충전하기 위해 힙에서 m개의 큰 값을 추출하고 min 값도 동시 계산
            int min = Integer.MAX_VALUE;
            ArrayList<Integer> list = new ArrayList<>();
            for (int i = 0; i < m && !h.isEmpty(); i++) {
                int cur = h.remove();
                min = Math.min(min, cur);
                list.add(cur);
            }

            //min 값만큼 충전하고 전자기기를 교체하기 위해 시간 값 갱신
            time += min;
            for (int cur : list) {
                int next = cur - min;
                if (next > 0) {
                    h.add(next);
                }
            }
        }

        bw.write(String.valueOf(time));
        bw.flush();
    }
}