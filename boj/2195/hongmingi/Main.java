// 11536KB	76ms
import java.util.*;
import java.io.*;
/*
 * 목표 문자열과 입력된 문자열의 idx를 비교해가면서 그때마다 greedy하게 최대로 나올 수 있는 값을 찾아서 cnt를 
 * 올리는 방식으로 구현.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] S = br.readLine().toCharArray(); // 원본 문자열
        char[] P = br.readLine().toCharArray(); // 매칭할 문자열

        int s = S.length;
        int p = P.length;
        int idx = 0;   // P에서 현재 탐색 위치
        int used = 0;  // S를 사용한 횟수

        while (idx < p) {
            int cnt = 0;
            for (int i = 0; i < s; i++) {
                int j = 0;
                while (j + i < s && idx + j < p && S[i + j] == P[idx + j]) {
                    j++;
                }
                cnt = Math.max(cnt, j);
            }
            if (cnt == 0) {
                idx++; // 매칭이 하나도 안 되면 한 칸 이동
            } else {
                idx += cnt; // 최대 매칭된 만큼 이동
            }
            used++;
        }
        System.out.println(used);
    }
}
