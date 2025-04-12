import java.io.*;
import java.util.*;

/*
11584KB, 64ms

처음엔 구간 자체를 입력받은 뒤 적절히 정렬로 풀려고 했는데 이런저런 예외가 있을 것 같아 다른 방법을 찾음
시간 구간이 기껏해야 1000 언저리이니 IMOS가 되겠다 싶어서 IMOS 사용
구간을 분 기준으로 변환한 뒤 IMOS로 현 상태가 0인 최대 구간 길이 구하기
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[1321];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = getStartTime(st.nextToken());
            int end = getEndTime(st.nextToken());
            arr[start]++;
            arr[end]--;
        }

        int res = 0;
        int status = 0;
        int len = 0;
        for (int i = 600; i < 1320; i++) {
            status += arr[i];
            if (status == 0) {
                len++;
                res = Math.max(res, len);
            } else {
                len = 0;
            }
        }
        System.out.print(res);
    }

    //시작 시간은 그 전 10분까지 포함해야함. 이때 일과 시작 시간 600보다 작아지지 않도록 조정
    static int getStartTime(String time) {
        return Math.max(Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(2)) - 10, 600);
    }

    //종료 시간은 그 후 10분까지 포함해야함. 이때 일과 종료 시간 1320보다 커지지 않도록 조정
    static int getEndTime(String time) {
        return Math.min(Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(2)) + 10, 1320);
    }
}