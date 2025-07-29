import java.util.*;
import java.io.*;

/*
 * 34248KB	416ms
 * 성장력 낮은 거부터 정렬 & 단순 구현
 */

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] woods = new int[n][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            woods[i][0] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            woods[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(woods, (o1, o2) -> o1[1] - o2[1]);
        long answer = 0;
        for(int i = 0; i < n; i++){
            answer += woods[i][0] + i * woods[i][1];
        }

        System.out.println(answer);
    }
}