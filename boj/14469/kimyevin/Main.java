import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] infos = new int[N][2];
        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            infos[i][0] = Integer.parseInt(st.nextToken());
            infos[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(infos, (o1, o2) -> o1[0] - o2[0]);
        int current = 0;
        for(int i = 0; i < N; i++){
            if(current < infos[i][0]){ // 도착 전 작업 완료
                current = infos[i][0] + infos[i][1];
            }
            else{
                current += infos[i][1];
            }
        }
        System.out.println(current);
    }
}
