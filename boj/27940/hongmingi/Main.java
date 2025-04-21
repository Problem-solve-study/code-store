import java.io.*;
import java.util.*;
/*
 * 문제는 간단한데 입출력형식에 따라서 시간초과가 날 수 있었던 문제.
 * BufferedReader로 읽어올 때 모든 입력에 대해 for문으로 비교할 시 시간초과가 남.
 * 어차피 입력을 받을 때 맨 앞에 칸은 무조건 물이 차기 때문에 그냥 맨 앞에 값만 고려해서 계산하여 해결.
 */
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int total = 0;
        int R, I;
        for(int m=1; m<=M; m++){
            st = new StringTokenizer(br.readLine());
            I = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            total+=R;
            if(total>K){
                System.out.println(m+" "+1);
                return;
            } 
        }
        System.out.println(-1);
    }
}