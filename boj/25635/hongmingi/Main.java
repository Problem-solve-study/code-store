// 32348KB	364ms
import java.io.*;
import java.util.*;
/*
 * 개인적으로 상당히 어려웠던 문제.
 * 최댓값을 구하고 최댓값을 제외한 나머지 값들의 합이 최댓값보다 클 때, 그보다 작을 때를 구분해서 계산하는 점화식 문제.
 */
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] games = new long[n];
        long maxGame = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());;
        for(int i=0; i<n; i++){
            games[i] = Integer.parseInt(st.nextToken());
            maxGame = Math.max(maxGame, games[i]);
        }
        long cnt = 0;

        long sum = Arrays.stream(games).sum() - maxGame;
        if(sum<maxGame){
            cnt = sum*2+1;
        }else{
            cnt = sum+maxGame;
        }

        System.out.println(cnt);
    }
}