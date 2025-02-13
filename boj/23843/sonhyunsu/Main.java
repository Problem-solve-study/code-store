//제출번호: 89998742
//메모리:   19592 KB
//실행시간: 232 ms
import java.io.*;
import java.util.*;

//그리디
//가장 오랜 시간이 걸리는 전자기기부터 콘센트에 배치한다.
//증명은... 다른 누군가가 해주겠죠?
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        st.nextToken();
        int m = Integer.parseInt(st.nextToken());

        int[] chargeTimes = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] plugHolder = new int[m];

        int min;
        int minIdx;
        Arrays.sort(chargeTimes);
        //충전시간이 오래 걸리는 전자기기부터 충전시간이 가장 짧은 전자기기 순으로 선택한다.
        for (int i = chargeTimes.length - 1; i >= 0; i--) {
            min = Integer.MAX_VALUE;
            minIdx = -1;
            //현재까지 배치된 시간 중 충전이 가장 덜 걸리는 콘센트를 선택한다.
            for (int j = 0; j < m; j++) {
                if (plugHolder[j] < min) {
                    min = plugHolder[j];
                    minIdx = j;
                }
            }

            //그 콘센트에 현재 전자기기를 충전한다.
            plugHolder[minIdx] += chargeTimes[i];
        }

        //콘센트들 중 충전이 가장 오래 걸리는 시간을 출력한다.
        System.out.print(Arrays.stream(plugHolder).max().getAsInt());
    }
}