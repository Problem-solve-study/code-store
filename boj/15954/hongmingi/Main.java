import java.io.*;
import java.util.*;
/*
 * 완전 정직한 완탐.
 */
public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dolls = new int[N];
        st = new StringTokenizer(br.readLine());
        int total = 0;
        double mean, stdev;
        double maxStd = Integer.MAX_VALUE;

        for(int i=0; i<N; i++){
            dolls[i]=Integer.parseInt(st.nextToken());
        }

        for(int i=K; i<=N; i++){
            for(int j=0; j<=i-K; j++){
                total = 0;
                for(int k=j; k<i; k++){
                    total+=dolls[k];
                }
                mean = (double)total/(i-j);
                stdev = 0;
                for(int k=j; k<i; k++){
                    stdev += Math.pow((mean-dolls[k]), 2)/(i-j);
                }
                stdev = Math.sqrt(stdev);
                if(stdev<maxStd) maxStd = stdev;
            }
        }

        System.out.println(maxStd);
        
    }
}