/**
 * 	22116KB	256ms
 * [사고흐름]
 * 단순 그리디인 것 같아서 바로 시도했습니다.
 * 
 * [알고리즘 설명]
 * 촛불이 꺼져있는 시간을 diff배열에 저장하고 정렬합니다.
 * 전체 구간에서 diff배열에 담긴 가장 큰 값부터 K번 제거합니다.
 */

import java.io.*;
import java.util.Arrays;

public class Main {
    static int N, K;
    static int[] T, diff;

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        N = ni();
        K = ni()-1;
        T = new int[N];
        diff = new int[N-1];
        for (int i=0; i<N; ++i) T[i] = ni();
        for (int i=1; i<N; ++i) diff[i-1] = T[i] - (T[i-1]+1);
        Arrays.sort(diff);
        int res = T[T.length-1] - T[0] + 1;
        for (int i=0; i<K; ++i) res -= diff[N-2-i];
        System.out.println(res);
    }

    static int ni() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}