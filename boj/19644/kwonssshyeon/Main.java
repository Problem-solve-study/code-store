import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int l = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int ml = Integer.parseInt(st.nextToken());
        int mk = Integer.parseInt(st.nextToken());

        int c = Integer.parseInt(br.readLine());

        int[] z = new int[l + 2];
        for (int i = 1; i <= l; i++) {
            z[i] = Integer.parseInt(br.readLine());
        }

        int[] bomb = new int[l + ml + 2];
        int blocked = 0;
        String answer = "YES";

        for (int i = 1; i <= l; i++) {

            blocked += bomb[i];

            int shot = Math.min(i, ml) - blocked;
            if (shot < 0) shot = 0;

            long damage = (long) shot * mk;

            // 총으로 못죽으면
            if (z[i] > damage) {
                // 지뢰 없으면 실패
                if (c == 0) {
                    answer = "NO";
                    break;
                }
                // 지뢰 사용
                c--;
                blocked++;
                bomb[i + ml]--;
            }
        }
        System.out.println(answer);
    }
}
