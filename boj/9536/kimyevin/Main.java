import java.io.*;
import java.util.*;
/**
 * 11780KB	68ms
 * 파싱, HashSet, 문자열 다루기
 * set에 다른 동물 울음소리 넣어서 해당 set에 포함되면 출력X
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(T-- > 0){
            String[] crying = br.readLine().split(" ");
            Set<String> set = new HashSet<>();
            String line = br.readLine();
            while(!"what does the fox say?".equals(line)){
                String[] ipts = line.split(" ");
                set.add(ipts[2]); // 울음소리만 넣기
                line = br.readLine();
            }
            for(String c : crying){
                if(set.contains(c)) continue;
                sb.append(c).append(' ');
            }
        }
        br.close();
        System.out.println(sb);
    }
}
