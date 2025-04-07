// 11428KB 64ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 내가 앞 자리랑 바꾼 경우 = 앞에 애가 자기 뒤(== 나)랑 바꾼 경우
// 내가 내 자리에 앉는 경우 = 앞에 애가 자기 앞이랑 바꿨거나 자기 자리에 앉은 경우
// 내가 뒷 자리랑 바꾼 경우 = 앞에 애가 자기 앞이랑 바꿨거나 자기 자리에 앉은 경우

// 고정석 나올 때마다 구간 별 경우의 수를 서로 곱하기
public class Main {
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int SWITCH_PREV = 0;  // 앞에 애가 여기 앉음
        final int CORRET = 1;       // 제자리에 앉음
        final int SWITCH_NEXT = 2;  // 뒤에 애가 여기 앉음
        
        int n = Integer.parseInt(br.readLine());
        int[][] seats = new int[3][n];
        boolean[] fixed = new boolean[n];

        int m = Integer.parseInt(br.readLine());
        for (int i = 0; i < m; i++) {
            int seat = Integer.parseInt(br.readLine()) - 1;
            fixed[seat] = true;
        }

        seats[CORRET][0] = 1;
        if (!fixed[0]) {
            seats[SWITCH_NEXT][0] = 1;
        }

        int answer = 1;
        for (int seat = 1; seat < n; seat++) {
            if (fixed[seat]) {
                int sum = seats[SWITCH_PREV][seat - 1] + seats[CORRET][seat - 1];
                answer *= sum;

                seats[CORRET][seat] = 1;
                continue;
            }

            seats[SWITCH_PREV][seat] = seats[SWITCH_NEXT][seat - 1];
            seats[CORRET][seat] = seats[CORRET][seat - 1] + seats[SWITCH_PREV][seat - 1];
            seats[SWITCH_NEXT][seat] = seats[CORRET][seat - 1] + seats[SWITCH_PREV][seat - 1];
        }

        if (!fixed[n - 1]) {
            answer *= seats[SWITCH_PREV][n - 1] + seats[CORRET][n - 1];
        }

        System.out.println(answer);
    }
}
