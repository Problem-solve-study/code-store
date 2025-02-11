import java.io.*;
import java.util.*;

/*
60856KB, 420ms

길이가 10으로 매우 짧으므로 완전탐색이라고 생각했다.
처음에는 String으로 탐색할까 했는데 대놓고 2진수 문제인데 그럴 필요가 있나 싶어 비트마스킹으로 접근
비트마스킹을 해주며 BFS를 돌려주면 무난히 통과하는 것 같다.

msb 계산을 잘못해서 한 번 틀렸다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashSet<Integer> set = new HashSet<>();
        //Integer.parseInt할 때 뒤에 진수를 입력하면 해당 진수로 이루어진 문자열을 10진수로 파싱해줌
        int[] start = new int[] {Integer.parseInt(br.readLine(), 2), 0};
        int target = Integer.parseInt(br.readLine(), 2);
        
        int res = 0;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(start);
        set.add(start[0]);
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            if (cur[0] == target) {
                res = cur[1];
                break;
            }

            //MSB를 반환. 이때 MSB의 인덱스를 반환하는게 아니므로 주의 필요
            //한 자리 수 보수로 바꾸기
            int msb = Integer.highestOneBit(cur[0]);
            for (int i = 0; (1 << i) < msb; i++) {
                int newValue = cur[0] ^ (1 << i);
                if (!set.contains(newValue)) {
                    q.add(new int[] {newValue, cur[1] + 1});
                    set.add(newValue);
                }
            }

            //현재 수에 1 더하기
            if (!set.contains(cur[0] + 1)) {
                q.add(new int[] {cur[0] + 1, cur[1] + 1});
                set.add(cur[0] + 1);
            }

            //현재 수에 1 빼기
            if (cur[0] != 0 && !set.contains(cur[0] - 1)) {
                q.add(new int[] {cur[0] - 1, cur[1] + 1});
                set.add(cur[0] - 1);
            }
        }

        bw.write(String.valueOf(res));
        bw.flush();
    }
}