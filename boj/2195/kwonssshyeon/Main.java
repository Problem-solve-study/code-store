// 11520KB	72ms
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] source = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();
        
        int tIdx = 0;
        int ans = 0;

        while (tIdx < target.length) {
            int max = 0;
            int temp = 0;
            int sIdx = 0;

            while (sIdx < source.length && tIdx + temp < target.length) {
                if (target[tIdx + temp] == source[sIdx]) {
                    temp++;
                    max = Math.max(max, temp);
                } else {
                    temp = 0;
                }
                sIdx++;
            }
            tIdx += max;
            ans++;
        }
        System.out.println(ans);
    }
}
