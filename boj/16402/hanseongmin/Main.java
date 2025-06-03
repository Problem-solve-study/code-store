import java.io.*;
import java.util.*;

/*
16056KB, 136ms

그냥 대놓고 분리 집합 문제임.
분리집합을 활용하되 다른 왕국끼리 전쟁을 벌이는 경우와 같은 왕국 내에서 전쟁을 벌이는 경우를
적절히 분기해서 짜주면 됨
 */

public class Main {
    //map으로 분리 집합 사용
    //자신의 이름, 부모의 이름
    static TreeMap<String, String> kingdoms = new TreeMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            kingdoms.put(name, name);
        }

        for (int i = 0; i < M; i++) {
            String[] result = br.readLine().split(",");
            String p0 = find(result[0]);
            String p1 = find(result[1]);

            if (result[2].equals("1")) {
                //내전인 경우
                if (p0.equals(p1)) {
                    if (kingdoms.get(p1).equals(p1)) {
                        //속국이 이겼다면 종주국, 속국 역전
                        kingdoms.put(result[0], result[0]);
                        kingdoms.put(result[1], result[0]);
                    }
                } else {
                    //다른 왕국과의 싸움이라면 부모만 바꿔주면 됨
                    kingdoms.put(p1, p0);
                }
            } else {
                if (p0.equals(p1)) {
                    if (kingdoms.get(p0).equals(p0)) {
                        kingdoms.put(result[1], result[1]);
                        kingdoms.put(result[0], result[1]);
                    }
                } else {
                    kingdoms.put(p0, p1);
                }
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : kingdoms.entrySet()) {
            //key와 value의 값이 같다면 속국이 아니므로 출력
            //TreeMap에 저장시켜뒀으므로 정렬은 추가적으로 하지 않아도 됨
            if (entry.getKey().equals(entry.getValue())) {
                cnt++;
                sb.append(entry.getKey()).append('\n');
            }
        }

        System.out.println(cnt);
        System.out.print(sb);
    }

    static String find(String cur) {
        if (kingdoms.get(cur).equals(cur)) {
            return cur;
        }

        String p = find(kingdoms.get(cur));
        kingdoms.put(cur, p);
        return p;
    }
}
