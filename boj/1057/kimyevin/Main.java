import java.io.*;
import java.util.*;
/**
 * 11520KB	64ms
 * 다음 라운드에 배정되는 번호는 j = (j + 1) / 2
 * j와 h가 같아질 때까지 돌리기
 * 68ms -> 64ms 되려면 비트 연산 & 같아지는 라운드 되기기 전에 멈추기
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int j = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int round = 0;
        while(true){
            j = (j+1) >> 1;
            h = (h+1) >> 1;
            round++;
            if(j == h) break; // 미리 종료
        }
        System.out.println(round);
    }
}
