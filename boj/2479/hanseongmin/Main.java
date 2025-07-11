import java.io.*;
import java.util.*;

/*
 * 14740KB, 136ms
 * 
 * BFS + 경로 역추적
 * N이 좀 작은거 같길래 경로 전체를 통째로 저장한 번 해봤다가 멤초남
 * 이후 그냥 역추적하는걸로 수정해서 AC
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        String[] code = new String[N + 1];
        ArrayList<Integer>[] hm1 = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            hm1[i] = new ArrayList<>();
        }

        for (int i = 1; i <= N; i++) {
            code[i] = br.readLine();
        }

        //해밍 거리가 1인 녀석들을 사전에 찾아
        //그래프 생성
        for (int i = 1; i <= N; i++) {
            for (int j = i + 1; j <= N; j++) {
                if (H(code[i], code[j]) == 1) {
                    hm1[i].add(j);
                    hm1[j].add(i);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        //BFS로 최단 경로를 탐색
        //visited 배열엔 역추적을 위해 이전에 어느 코드에서 왔는지 기록
        ArrayDeque<Integer> q = new ArrayDeque<>();
        int[] v = new int[N + 1];
        q.addLast(S);
        v[S] = S;
        while (!q.isEmpty()) {
            int cur = q.removeFirst();
            if (cur == E) {
                break;
            }

            for (int nextPos : hm1[cur]) {
                if (v[nextPos] == 0) {
                    q.addLast(nextPos);
                    v[nextPos] = cur;
                }
            }
        }

        //도착 지점에 도달한 적이 없으면 경로가 존재하지 않음
        if (v[E] == 0) {
            System.out.print(-1);
        } else {
            //경로 역추적하여 출력
            ArrayDeque<Integer> d = new ArrayDeque<>();
            d.addFirst(E);
            int cur = E;
            while (true) {
                if (cur == S) break;
                d.addFirst(v[cur]);
                cur = v[cur];
            }

            StringBuilder sb = new StringBuilder();
            for (int n : d) {
                sb.append(n).append(' ');
            }
            System.out.print(sb);
        }
    }

    static int H(String a, String b) {
        int res = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                res++;
            }
        }
        return res;
    }

    static boolean cc(ArrayList<Integer> list, int e) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == e) {
                return false;
            }
        }
        return true;
    }
}