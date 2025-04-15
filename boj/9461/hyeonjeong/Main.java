// 11600KB 68ms

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

// 간단한 규칙 찾기
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        List<Long> p = new ArrayList<>();
        p.add(0L);
        p.add(1L);
        p.add(1L);
        p.add(1L);
        p.add(2L);
        p.add(2L);
        p.add(3L);

        int t = nextInt();
        for (; t > 0; t--) {
            int n = nextInt();

            if (p.size() <= n) {
                for (int i = p.size(); i <= n; i++) {
                    p.add(p.get(i - 1) + p.get(i - 5));
                }
            }

            sb.append(p.get(n)).append('\n');
        }

        System.out.println(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();

        return (int) st.nval;
    }
}
