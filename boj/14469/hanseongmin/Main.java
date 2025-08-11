/*
18576KB, 196ms

집에 서버컴 하나 놔둔 기념으로 개발 환경 세팅 시켜두고 한 문제 풀었습니다.
다들 프로젝트 하느라 바쁘시겠네요 ㄷㄷ
*/

import java.io.*;
import java.util.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nextInt();
            arr[i][1] = nextInt();
        }
        Arrays.sort(arr, Comparator.comparingInt((int[] a) -> a[0]).thenComparingInt(a -> a[1]));
        
        int time = 0;
        for (int[] e : arr) {
            int arriveTime = e[0];
            int spendTime = e[1];
            if (time < arriveTime) time = arriveTime;
            time += spendTime;
        }
        System.out.println(time);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}