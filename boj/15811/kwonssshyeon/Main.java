// 15384KB	516ms
import java.io.*;
import java.util.*;

/**
 * set에 필요한 알파벳을 전부 저장 -> 리스트로 변환하여 인덱스로 접근할 수 있도록 함
 * 백트래킹으로 각각의 알파벳이 어떤 숫자와 매핑되는지 alpha 배열에 저장 (인덱스 : 알파벳, 값 : 매핑된 숫자)
 * 계산
 */
public class Main {
    static char[] a, b, target;
    static int[] alpha = new int[26];
    static boolean[] visited = new boolean[26];
    static boolean possible;
    static HashSet<Character> set = new HashSet<>();
    static ArrayList<Character> list;
    static int limit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = st.nextToken().toCharArray();
        b = st.nextToken().toCharArray();
        target = st.nextToken().toCharArray();
        for (char c : a) {
            set.add(c);
        }
        for (char c : b) {
            set.add(c);
        }
        for (char c : target) {
            set.add(c);
        }
        limit = set.size();
        list = new ArrayList<>(set);
        dfs(0);
        System.out.print(possible ? "YES" : "NO");
    }


    // alpha 순열을 만드는 함수  (백트래킹)
    static void dfs(int cnt) {
        if (possible) return;
        
        if (cnt == limit) {
            possible = possible || calculate();
            return;
        }

        for (int i=0; i<10; i++) {
            if (!visited[i]) {
                visited[i] = true;
                alpha[list.get(cnt)-'A'] = i;
                dfs(cnt+1);
                visited[i] = false;
            }
        }
    }

    // 올바른 식인지 확인하는 함수
    static boolean calculate() {
        int na = 0, nb = 0, nt = 0;
        for (char c : a) {
            na = na * 10 + alpha[c-'A'];
        }
        for (char c : b) {
            nb = nb * 10 + alpha[c-'A'];
        }
        for (char c : target) {
            nt = nt * 10 + alpha[c-'A'];
        }
        return (na+nb) == nt;
    }
}
