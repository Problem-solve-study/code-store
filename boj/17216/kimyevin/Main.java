import java.util.*;
import java.io.*;

/*
 * 11856KB	80ms
 * 나를 포함하는 감소 부분 수열을 b배열에 기록
 * b[i] = 나보다 큰 원소의 감수 부분 수열 + arr[i]
 */

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] b = new int[N];
        int answer = 0;
        for(int i = 0; i < N; i++){
            b[i] = arr[i];
            for(int j = 0; j < i; j++){
                if(arr[i] < arr[j]){
                    b[i] = Math.max(b[i], b[j] + arr[i]);
                }
            }
            answer = Math.max(answer, b[i]);
        }

        System.out.println(answer);
    }
}