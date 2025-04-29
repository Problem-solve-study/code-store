import java.io.*;
import java.util.*;
/**
 * 11688 KB	68 ms
 * #구현 #문자열 #Set
 * 마지막 6글자가 Cheese인지 확인하고, 치즈라면 Set에 넣기기
 */
public class Main {
    static String[] answer = {"yummy", "sad"};
    static String cheese = "Cheese";
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<String> chSet = new HashSet<>();
        while(N-- > 0){
            String topping = st.nextToken();
            int start = topping.length() - 6; // 검사 시작할 index 찾기기
            boolean flag = true;
            if(start < 0) continue;
            for(int i = 0; i < 6; i++){ // Cheese 검사
                if(topping.charAt(start + i) != cheese.charAt(i)){
                    flag = false;
                    break;
                }
            }
            if(flag) chSet.add(topping); // set에서 중복 체크
        }
        if(chSet.size() >= 4) System.out.println(answer[0]);
        else System.out.println(answer[1]);
    }
}
