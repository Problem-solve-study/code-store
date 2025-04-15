// 97948KB	2548ms
import java.io.*;
import java.util.*;
/*
 * 이분탐색 공부용으로 아주 좋았던 문제
 * essential이 1일 때 필수인 줄 알고 3시간 날림....
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long G = Long.parseLong(st.nextToken());
        long K = Long.parseLong(st.nextToken());

        element[] mealkit = new element[(int)N];
    
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            mealkit[i] = new element(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        long s = 0;
        long e = 2000000000;
        
        int cnt;
        
        while(s<=e){
            long mid = (s+e)/2;
            cnt = 0;
            long sum = 0;
            Arrays.sort(mealkit, (y,x)->Long.compare(x.speed*Math.max(1,(mid-x.limit)),y.speed*Math.max(1, (mid-y.limit))));
        
            for(int i=0; i<N; i++){
                if(mealkit[i].essential == 0){
                    sum+=(long)mealkit[i].speed * Math.max(1, mid-mealkit[i].limit);
                }
                else{
                    if(cnt<K){
                        cnt++;
                        continue;
                    }
                    else{
                        sum+=mealkit[i].speed * Math.max(1, mid-mealkit[i].limit);
                    }
                }
                if(sum>G) break;
            }
            if(sum>G){
                e = mid-1;
            }else{
                s = mid+1;
            }
        }
        
        System.out.println(e);
    }
    //재료
    static class element{
        long speed, limit;
        int essential;
        public element(long speed, long limit, int essential){
        this.speed = speed;
        this.limit = limit;
        this.essential = essential;//==1?true:false;
        }
    }
}
