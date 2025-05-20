// 188884KB	588ms
import java.io.*;
import java.util.*;

/**
 * 1 + 1
 * 2 + 1
 * 1 + 2
 * 통과시켜 주셔서 감사합니다...
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int[] num = new int[n];
        
        for (int i=0; i<n; i++) {
            num[i] = nextInt();
        }

        int answer = 0;
        HashSet<Integer> set = new HashSet<>();
        for (int i=1; i<n; i++) {
            
            for (int j=0; j<i; j++) {
                set.add(num[i-1] + num[j]);
            }
            for (int j=0; j<i; j++) {
                if (set.contains(num[i]-num[j])) {
                    answer++;
                    break;
                }
            }
        }

        System.out.print(answer);
    }


    static int nextInt() throws IOException{
        st.nextToken();
        return (int) st.nval;
    }
}