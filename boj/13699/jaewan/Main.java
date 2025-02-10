// 13008 KB, 96ms
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        long[] t = new long[36];
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        t[0]=1;
        for(int i=1;i<=n;i++){
            for(int j=0;j<i;j++){
                t[i]+=(t[j]*t[i-j-1]);
            }
        }
        System.out.println(t[n]);
    }
}