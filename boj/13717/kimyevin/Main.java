import java.io.*;
import java.util.*;
/**
 * 11604KB	68ms
 * Pi 포켓몬 진화시키기 위해 Ki개의 사탕이 필요
 * 진화가 되면 2개의 사탕 돌려받음
 * N종의 포켓몬이 있고, Pi 포켓몬의 Mi개 갖고 있음.
 * 진화시킬 수 있는 포켓몬 총 마릿수
 * => 그냥 반복문으로 계산하기 (진화 된 후에는 2개의 사탕 돌려받기)
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int total = 0, max = -1;
        String answer = "";
        for(int i = 0; i < N; i++){
            String name = br.readLine();
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int count = 0;
            while(M >= K){
                M -= (K-2);
                count++;
            }
            total += count;
            if(count > max){ // 기존 max 값보다 진화 값이 더 크면 기록
                max = count;
                answer = name;
            }
        }
        System.out.println(total);
        System.out.println(answer);
    }
}
