// 14156KB	104ms
import java.io.*;

public class Main {
    static int cnt;
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        cnt = 0;
        long[] arr = new long[n+3];
        arr[0] = arr[1] = 1;
        for(int i=2; i<=n; i++){
            arr[i] = arr[i-1]+arr[i-2] + 1;
        } 

        System.out.println(arr[n] % 1000000007);
    }
}
