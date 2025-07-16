import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] buttons = new boolean[N];
        boolean[] current = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            buttons[i] = st.nextToken().equals("1") ? true : false;
        }

        int answer = 0;

        for(int i = 0; i < N; i++){
            if(buttons[i] == current[i]) continue;
            answer++;
            for(int k = i; k < i+3; k++){
                if(k >= N) break;
                current[k] = !current[k];
            }
        }
        
        System.out.println(answer);
    }
}
