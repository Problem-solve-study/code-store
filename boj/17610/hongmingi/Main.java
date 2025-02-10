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
        

        check = new boolean[s+1];
        dfs(0, 0);
        int cnt = 0;
        for(boolean c : check){
            if(!c) cnt++;
        }
        System.out.println(cnt);

    }

    static void dfs(int i, int partsum){
        if(partsum>=0&&!check[partsum]) check[partsum] = true;
        System.out.println(partsum);
        for(int j = i; j<n; j++){
            dfs(j+1, partsum+arr[j]);
            dfs(j+1, Math.abs(partsum-arr[j]));
        }
        

    }
}
