// 11736KB	72ms
import java.io.*;
import java.util.*;
/*
 * 단순 구현. 순위 변하는 걸 적용하는 데 ArrayList가 매우 사용하기에 효과적이었음.
 */

public class Main{
    static int N, M;
    static ArrayList<Integer> ranks;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ranks = new ArrayList<>();
        
        for(int i=1; i<=N; i++){
            ranks.add(Integer.parseInt(br.readLine())-1, i);
        }

        int[] finalRank = new int[3];
        int nowRank;
        for(int i=M-1; i>=0; i--){
            nowRank = Integer.parseInt(br.readLine());
            if(nowRank==3){
                finalRank[2] = ranks.get(i);
            } else if(nowRank==2){
                if(finalRank[1]!=0) finalRank[2] = finalRank[1];
                finalRank[1] = ranks.get(i);
            } else if(nowRank==1){
                if(finalRank[1]!=0) finalRank[2] = finalRank[1];
                if(finalRank[0]!=0) finalRank[1] = finalRank[0];
                finalRank[0] = ranks.get(i);
            } else {
                continue;
            }
        }

        System.out.printf(finalRank[0]+"\n"+finalRank[1]+"\n"+finalRank[2]);

    }
}
