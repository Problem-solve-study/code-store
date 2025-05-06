// 22052KB	276ms
import java.io.*;
import java.util.*;

/**
 * 일단 최소 n만큼은 켜둬야 한다.
 * 만약 성냥이 부족하면, 구간의 거리가 가장 작은 것 부터 이어서 켜두면 된다.
 * 구간의 거리 배열 diff를 구하고 정렬하여 (n-k) 개까지 더함.
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n, k, time[];

    public static void main(String[] args) throws IOException {
        n = nextInt();
        k = nextInt();
        time = new int[n];
        for (int i=0; i<n; i++) {
            time[i] = nextInt();
        }

        int[] diff = new int[n-1];
        for (int i=1; i<n; i++) {
            diff[i-1] = time[i] - (time[i-1] + 1);
        }
        Arrays.sort(diff);
        int answer = n;
        for (int i=0; i<n-k; i++) {
            answer += diff[i];
        }
        System.out.println(answer);
    }


    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
