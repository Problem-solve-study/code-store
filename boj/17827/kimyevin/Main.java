import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * 72900KB	548ms
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            num[i] = Integer.parseInt(st.nextToken());
        }
        int rotation = N - V + 1; // 사이클 길이 구하기기
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < M; i++){
            int q = Integer.parseInt(br.readLine());
            if(q < N) sb.append(num[q]);
            else{
                int answer = (q - V+1) % rotation; // modular 연산
                sb.append(num[V + answer - 1]);
            }
            sb.append("\n");
        }
        
        br.close();
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
