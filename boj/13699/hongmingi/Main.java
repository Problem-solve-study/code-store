//14152kb	104ms
import java.io.*;
import java.util.*;
public class Main {
    static boolean[] check;
    static int n;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new long[n+1];
        arr[0] = 1;
        for(int i=1; i<=n; i++){
            for(int j=0; j<i; j++){
                arr[i] += arr[j]*arr[i-j-1];
            }
        }
        System.out.println(arr[n]);
    }
}
