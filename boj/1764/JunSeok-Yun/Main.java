
//29080KB 372ms
import java.io.*;
import java.util.*;

// 입력으로 듣도 못한 사람의 명단과, 보도 못한 사람의 명단이 주어질 때, 두 명단에서 중첩해서 나오는 명단을 구하는 문제
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 듣도 못한 사람의 수
        int M = Integer.parseInt(st.nextToken()); // 보도 못한 사람의 수
        TreeMap<String, Integer> map = new TreeMap<>(); // 정렬하여 값을 저장하기 위한 TreeMap
        StringBuilder sb = new StringBuilder();
        String str;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            str = br.readLine();
            if (map.containsKey(str)) { // 현재 명단에 있는 사람이 있다면 value값 증가
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        for (int i = 0; i < M; i++) {
            str = br.readLine();
            if (map.containsKey(str)) {
                map.put(str, map.get(str) + 1);
            } else {
                map.put(str, 1);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            // 듣도 못한 명단에서 나온 사람과 보도 못한 명단에서 나온 사람이 중첩되서 나온다면
            // 듣도 보도 못한 사람이다.
            if (entry.getValue() > 1) {
                cnt++;
                sb.append(entry.getKey()).append('\n');
            }
        }
        System.out.println(cnt);
        System.out.println(sb.toString());
    }
}