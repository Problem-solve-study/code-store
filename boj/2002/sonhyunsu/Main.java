
//제출번호: 91138930
//메모리:   11812 KB
//실행시간: 72 ms
import java.io.*;
import java.util.*;

//나와야 하는 순서에서 벗어나는 자동차의 개수를 세어주면 되는 문제
//먼저 나온 자동차는 순서를 계산할 때 건너뛰어야 하기 때문에 boolean 사용
//TreeMap으로 12700 KB가 나오고 HashMap으로는 11812 KB 나옴
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new HashMap<>();

        int n = Integer.parseInt(br.readLine());
        boolean[] doSkip = new boolean[n + 1]; // 자동차가 지나갔는 지 판단
        for (int i = 0; i < n; i++) {
            map.put(br.readLine(), i); // 들어간 자동차 순으로 순서를 매김
        }

        int order = 0;
        int count = 0;
        for (int i = 0; i < n; i++) {
            // 지금 빠져나온 자동차의 순서를 가져옴
            int curOrder = map.get(br.readLine());

            // 다음 순서를 계산할 때 순서를 스킵할 것을 기록함
            doSkip[curOrder] = true;

            // 만약에 지금 나와야 하는 순서보다 지금 자동차 순서가 더 뒤라면 추월한 자동차임
            if (curOrder > order) {
                count++;
            } else {
                // 아니라면 다음에 나와야 할 자동차의 순서를 계산함
                while (doSkip[order]) {
                    order++;
                }
            }
        }

        // 추월한 자동차의 순서 출력
        System.out.print(count);
    }
}