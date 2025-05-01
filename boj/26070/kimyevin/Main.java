import java.io.*;
import java.util.*;
/**
 * 11516KB	60ms
 * 치킨-A, 피자-B, 햄-C 식권(x, y, z)
 * 치킨3=>피자1, 피자3=>햄1, 햄3=>치킨1
 * 더이상 의미 없어질 때까지 계속 교환
 * 의미가 없어진다는 건... 한바퀴 돌때까지 음식 먹은 곰곰이가 없거나,
 * 식권 교환도 이뤄지지 않았다는 뜻!
 */
public class Main{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] gom = new int[3];
        int[] sik = new int[3];
        long answer = 0;
        for(int i =0 ;i <3; i++){
            gom[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 3; i++){
            sik[i] = Integer.parseInt(st.nextToken());
        }
        
        while(true){
            boolean flag = false;
            for(int i = 0; i < 3; i++){
                int eat = Math.min(gom[i], sik[i]);
                if(eat > 0) flag = true; // 곰곰이 먹이기
                answer += eat;
                gom[i] -= eat;
                sik[i] -= eat;

                if(sik[i] >= 3){ // 식권 끼리 교환
                    sik[(i+1) % 3] += sik[i] / 3;
                    sik[i] %= 3;
                    flag = true;
                }
            }
            if(!flag) break;
        }

        System.out.println(answer);
    }
}