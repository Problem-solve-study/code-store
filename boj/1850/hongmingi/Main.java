import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * 유클리드 호제법으로 푸는 문제.
 * 1의 개수들을 담는 변수 자체를 유클리드 호제법으로 풀어도 가능함.
 * 다만 1의 개수가 많기에 stringbuilder나 bufferedwriter 사용해야함.
 */
public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        
        long tmp;
        if(A<B){
            tmp = A;
            A = B;
            B = tmp;
        }
        while(B>0){
            tmp = A;
            A = B;
            B = tmp % A;
        }
        StringBuilder sb = new StringBuilder();
        
        while(A>0){
            A--;
            sb.append("1");
        }
        System.out.println(sb);
    }
}
