import java.io.*;
import java.util.*;
/*
 * HashMap과 다중 for문을 사용한 브루트포스 문제.
 */
public class Main{
    static int N, M;
    static HashSet<Integer>[] friends;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        friends = new HashSet[N+1];

        for(int i=0; i<=N; i++){
            friends[i] = new HashSet<>();
        }
        int a, b;
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            friends[a].add(b);
            friends[b].add(a);
        }
        // 친구 1~N까지 순회하면서 3명 다 친구인 경우 확인.
        int minN = Integer.MAX_VALUE;
        boolean isFriends = false;
        for(int i=1; i<=N; i++){
            if(friends[i].size()<2) continue;
            // i의 친구인 j 중에
            for(int j:friends[i]){
                if(friends[j].size()<2) continue;
                // i와 j와 같이 친구인 k 체크 후
                for(int k:friends[j]){
                    if(!friends[i].contains(k)) continue; 
                    else{
                        // 있을 경우 친구 수 체크.
                        minN = Math.min(minN, friends[i].size()+friends[j].size()+friends[k].size()-6);
                        isFriends = true;
                    }
                }
            }
        }
        if(isFriends) System.out.println(minN);
        else System.out.println(-1);
    }
}
