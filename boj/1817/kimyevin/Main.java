import java.util.*;
import java.io.*;

public class B1817{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        if(N == 0){
            System.out.println(0);
            return;
        }

        int[] books = new int[N];
        int[] boxes = new int[N];
        int index = 0;

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++){    
            books[i] = Integer.parseInt(st.nextToken());
            if(boxes[index] + books[i]<= M){
                boxes[index] += books[i];
            }
            else{
                boxes[++index] += books[i];
            }
        }
        System.out.println(index+1);

    }
}