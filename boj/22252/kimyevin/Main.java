import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
/*
 * 147996KB	1032ms
 *
 * Map에 고릴라 이름(String) key, PriorityQueue<정보> value 로 저장
 * 쿼리에 맞춰서 그대로 구현 (b개수 <= 정보개수) 아닐 수도 있으니 신경쓰기
 * +) 음수 값으로 넣으면 default 정렬로 가능
 * 최대 10만원인 정보를 5만(10만?)마리의 고릴라로부터 10만개씩 가질 수 있으므로 long type
 */
public class Main{
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Map<String, PriorityQueue<Integer>> record = new HashMap<>();
        long total = 0;
        int Q = Integer.parseInt(br.readLine());
        while(Q-- > 0){
            st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            if(type == 1){ // query type 구분
                String name = st.nextToken();
                int c = Integer.parseInt(st.nextToken());
                PriorityQueue<Integer> infos = record.getOrDefault(name, new PriorityQueue<>());
                // 못 보던 고릴라일수도 있으니 default 값 명시
                for(int i = 0; i < c; i++){
                    infos.add(- Integer.parseInt(st.nextToken())); // 음수로 저장 for 내림차순 정렬
                }
                record.put(name, infos);
            }
            else{
                String name = st.nextToken();
                // 못 보던 고릴라일수도 있으니 default 값 명시
                PriorityQueue<Integer> infos = record.getOrDefault(name, new PriorityQueue<>());
                int cnt = Integer.parseInt(st.nextToken());
                while(!infos.isEmpty() && cnt-- > 0){
                    total -= infos.poll(); // 음수로 저장해서 마이너스로 더해야한다
                }

            }
        }
        br.close();
        System.out.println(total);
    }
}