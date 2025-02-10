import java.io.*;
import java.util.*;
public class Main {
    static boolean[] check;
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        int s = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
            s+=arr[i];
        }

        Arrays.sort(arr);
        
        // 만들수 있는 무게인지 배열을 통해 체크
        check = new boolean[s+1];
        dfs(0, 0);
        int cnt = 0;
        for(boolean c : check){
            if(!c) cnt++;
        }
        System.out.println(cnt);

    }
    // dfs를 통해 완전탐색하여 만들수 있는 모든 무게의 경우의 수를 체크. dfs 방식은 성민씨 코드 참조함.
    static void dfs(int i, int partsum){
        if(partsum>=0&&!check[partsum]) check[partsum] = true;
        System.out.println(partsum);
        for(int j = i; j<n; j++){
            dfs(j+1, partsum+arr[j]);
            dfs(j+1, Math.abs(partsum-arr[j]));
        }
        

    }
}
