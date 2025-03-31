import java.io.*;
import java.util.*;

/*
18592KB, 236ms

가지치기 잘해야한다는 소리를 듣기도 했고 문제를 읽어봤을 때 딱히 테크닉이나 알고리즘을 써야할만한 문제가 아닌거같아
백트래킹으로 접근했다. N이 작은데? 싶어서 무지성으로 돌렸다간 시간이든 메모리든 둘 중 하나한테 터지는듯 하다.
자신이 생각할 수 있는 모든 가지치기를 조건으로 걸어주면 풀리는듯 하다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int K, N, F;
    static ArrayList<Integer>[] adj;
    static boolean[] v;
    static boolean isFind = false;

    public static void main(String[] args) throws Exception {
        K = nextInt();
        N = nextInt();
        F = nextInt();
        v = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < F; i++) {
            int a = nextInt();
            int b = nextInt();
            adj[a].add(b);
            adj[b].add(a);
        }

        //번호를 증가하는 순서대로 출력해야하니 인접리스트 값 정렬
        for (int i = 0; i <= N; i++) {
            if (adj[i].isEmpty()) continue;
            Collections.sort(adj[i]);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            //자신 + 자신과 연결된 친구의 수가 K보다 작으면 절대 K개를 뽑을 수 없으므로 넘어감
            if (1 + adj[i].size() < K) continue;

            list.clear();
            v[i] = true; list.add(i);
            find(i, list);
            if (isFind) break;
            v[i] = false; list.remove(list.size() - 1);
        }

        if (isFind) {
            StringBuilder sb = new StringBuilder();
            list.forEach(e -> sb.append(e).append('\n'));
            System.out.print(sb);
        } else {
            System.out.print(-1);
        }
    }

    static void find(int n, ArrayList<Integer> list) {
        if (list.size() == K) { //K개만큼 뽑았다면 가능한 경우
            isFind = true;
            return;
        }

        for (int next : adj[n]) {
            if (v[next]) continue; //이미 뽑힌 친구라면 넘어감
            if (n > next) continue; //나보다 작은 친구라면 그 전의 탐색에서 확인해봤을 것이니 넘어감
            //지금까지 뽑힌 친구 + 자신 + 자신과 연결된 뽑힐 친구의 후보를 모두 더해도 K 미만이라면 반드시 불가능하니 생략
            if (list.size() + 1 + expectedSize(next, list) < K) continue;

            //현재 보고 있는 next가 list 내부의 모든 친구들과 친구인지 확인
            if (check(next, list)) {
                v[next] = true; list.add(next);
                find(next, list);
                if (isFind) return;
                v[next] = false; list.remove(list.size() - 1);
            }
        }
    }

    //contains를 O(1)로 뽑아내고 싶어 처음엔 트리셋을 썼는데 메모리 초과나서
    //ArrayList를 사용한 다음 정렬하고 이분 탐색으로 logN으로 존재 여부를 판단하였음
    static int expectedSize(int next, ArrayList<Integer> list) {
        int cnt = 0;
        for (int e : adj[next]) {
            int idx = Collections.binarySearch(list, e);
            if (idx < 0) {
                cnt++;
            }
        }

        return cnt;
    }

    static boolean check(int next, ArrayList<Integer> list) {
        for (int f : list) {
            int idx = Collections.binarySearch(adj[f], next);
            if (idx < 0) {
                return false;
            }
        }
        return true;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}