import java.io.*;
import java.util.*;

/*
19784KB, 196ms

n^2으로 보면 시간 초과에 걸릴 것 같아 슬라이딩 윈도우로 구현
윈도우 범위를 벗어나면 map의 값을 1 줄이고 윈도우 범위에 새로 들어온 녀석을 추가해준다.
이를 전체 경우에서 확인해주면 된다.
초밥이 원형으로 배치되었음에 유의
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[n];
        for (int i = 0; i < n; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        //최초 윈도우 범위 맵에 삽입
        HashMap<Integer, Integer> selected = new HashMap<>();
        selected.put(c, 1);
        for (int i = 0; i < k; i++) {
            selected.put(sushi[i], selected.getOrDefault(sushi[i], 0) + 1);
        }
        
        //이후 윈도우를 옮겨가며 경우의 수 카운팅
        int res = selected.size();
        for (int tail = 0; tail < n; tail++) {
            int head = (tail + k) % n;
            selected.put(sushi[tail], selected.get(sushi[tail]) - 1);
            if (selected.get(sushi[tail]) <= 0) selected.remove(sushi[tail]);
            selected.put(sushi[head], selected.getOrDefault(sushi[head], 0) + 1);
            res = Math.max(res, selected.size());
        }

        bw.write(String.valueOf(res));
        bw.flush();
    }
}