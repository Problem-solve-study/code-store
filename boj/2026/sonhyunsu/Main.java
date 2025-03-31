//제출번호: 92311820
//메모리:   25156 KB
//실행시간: 260 ms
import java.io.*;
import java.util.*;

//K가 62까지여서 비트마스킹을 쓰면 될까 싶었는데 n이 900이어서 쓰기 힘들 것 같았음
//조합 & 백트래킹으로 구현하고 믿음으로 제출해서 맞았음
//가지치기를 빡세게 하려고 노력했음 
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static List<List<Integer>> graph = new ArrayList<>();
    static int k, n, f;
    static int[] inCount = new int[901];
    static int[] selected = new int[63];

    public static void main(String[] args) throws IOException {
        k = nextInt();
        n = nextInt();
        f = nextInt();

        for (int i = -1; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < f; i++) {
            int a = nextInt();
            int b = nextInt();
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        //작은 순으로 선택하기 위해서 정렬했음.
        for (int i = 1; i <= n; i++) {
            graph.get(i).sort(Comparator.naturalOrder());
            graph.get(0).add(i); //백트래킹 일반화를 위해서 가상의 0번을 모든 점들이랑 연결함
        }

        //조건에 만족하는 조합을 찾으면 true, 못 찾으면 false를 저장.
        boolean isFound = backtrack(0, 0);
        StringBuilder sb = new StringBuilder();

        //만약 찾았다면 찾은 조합을 sb에 저장
        if (isFound) {
            for (int i = 1; i <= k; i++) {
                sb.append(selected[i]).append('\n');
            }
        } else {
            //못 찾았다면 -1을 sb에 저장
            sb.append(-1);
        }

        System.out.print(sb);
    }

    static boolean backtrack(int cnt, int num) {
        //일단 selected 배열에 num을 저장함
        selected[cnt] = num;

        //만약 선택된 모든 값들의 edge가 cnt만큼 선택되었는지 확인함 
        boolean canSelect = true;
        for (int i = 1; i <= cnt; i++) {
            canSelect &= inCount[selected[i]] == cnt;
        }

        //하나라도 cnt만큼 선택되지 않았으면 불가능한 조합임
        if (!canSelect) {
            return false;
        }

        //k개만큼 선택했으면 가능한 조합임
        if (cnt == k) {
            return true;
        }

        //num을 포함해서 모든 엣지에 선택된 횟수를 1씩 증가시킴
        inCount[num]++;
        for (int next : graph.get(num)) {
            inCount[next]++;
        }

        //num의 엣지들을 하나씩 보면서
        for (int next : graph.get(num)) {
            //num 보다 작은 애들은 이미 선택됐거나 선택할 필요가 없음.
            if (next < num) {
                continue;
            }

            //다음 숫자를 선택해 봄
            if (backtrack(cnt + 1, next)) {
                return true;
            }
        }
        
        //1씩 증가했던 값들 원상 복구
        for (int next : graph.get(num)) {
            inCount[next]--;
        }
        inCount[num]--;

        //여기까지 왔으면 불가능한 조합임
        return false;
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}