import java.util.*;
import java.io.*;


public class Main {
    static int[] fibo;
    static int[] min;
    static int[] max;
    static int n;

	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        if (n == 2) {
            System.out.print(1+" "+1);
        }
        else {
            fibo = new int[n+1];
            fibo[2] = 1;
            fibo[3] = 2;
            for (int i=4; i<=n; i++) {
                fibo[i] = fibo[i-1] + fibo[i-2];
            }
    
            min = new int[n+1];
            Arrays.fill(min, Integer.MAX_VALUE);
            max = new int[n+1];
           
            min[2] = 1;
            min[3] = 2;
            for (int i=4; i<=n; i++) {
                min[i] = min[i-2] + fibo[2];
            }
    
            max[2] = 1;
            max[3] = 2;
            for (int i=4; i<=n; i++) {
                for (int j=i-2; j>=2; j--) {
                    max[i] = Math.max(max[i], max[j] + max[i-j]);
                }
            }
    
            System.out.print(min[n]+" "+max[n]);
        }
	}
}