import java.io.*;
import java.util.*;
/**
 * 84780 KB	484 ms
 * 맨 마지막 부서의 퇴근 시간은 모든 부서의 면담 소요시간을 다 더한 것,
 * 그 전 부서의 퇴근 시간은 맨 마지막 부서의 면담 소요 시간을 제외한 나머지 부서의 면담 소요 시간을 다 더한 것 ...
 * 먼저 끝나는 부서 퇴근 시간이 그 다음 부서 면담 소요 시간에 중복으로 들어가므로,
 * 소요시간이 작은 순서대로 퇴근 시켜야함. (정렬 & 누적합)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        long[] dept = new long[N]; // 모든 부서의 직원 수의 합은 1,000,000명 이하 long 자료형 신경쓰기기

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j = 0; j < cnt; j++){
                dept[i] += Integer.parseInt(st.nextToken());
            }
        }

        Arrays.sort(dept); // 정렬렬
        long answer = dept[0];
        for(int i = 1; i < N; i++){
            dept[i] += dept[i-1];
            answer += dept[i];
        }

        System.out.println(answer);
    }
}
