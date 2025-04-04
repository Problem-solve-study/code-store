// 25808KB	316ms
import java.io.*;
import java.util.*;
/*
 * 명단 작성 -> 중복 x -> HashSet.
 * 그런데 출력을 사전순으로 한다네? -> TreeSet.
 */
public class Main {
      public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        HashSet<String> hash = new HashSet<>();
        for(int i=0; i<N; i++){
          hash.add(br.readLine());
        }
        Set<String> hash2 = new TreeSet<>();
        for(int i=0; i<M; i++){
          String name = br.readLine();
          if(hash.contains(name)){
            hash2.add(name);
          }
        }
        System.out.println(hash2.size());
        for(String name:hash2) System.out.println(name);
    }
}
