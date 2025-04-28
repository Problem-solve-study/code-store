import java.io.*;
import java.util.*;

// 정보 상인이 정보를 내림차순으로 가지고 있어야하므로 HashMap<String, TreeSet>을 사용할까? 했는데
// 그냥 ArrayList에 정렬하고 넣어도 될 것 같아서 시도했음.

class Main {

    static int Q;
    static HashMap<String, ArrayList<Long>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Q = ni();

        long ans = 0;
        while (Q-- > 0) {
            int op = ni();
            if (op == 1) {
                String name = ns();
                map.putIfAbsent(name, new ArrayList<>());

                int k = ni();
                while (k-- > 0) {
                    long c = (long) ni();
                    map.get(name).add(c);
                }
            } else if (op == 2) {
                String name = ns();
                int k = ni();

                if (!map.containsKey(name))
                    continue;

                ArrayList<Long> list = map.get(name);
                Collections.sort(list);

                for (int i = list.size() - 1; i >= 0; i--) {
                    ans += list.get(i);
                    list.remove(i);
                    if (--k == 0)
                        break;
                }

                map.put(name, list);
            }
        }

        System.out.println(ans);
    }

    static void init() throws Exception {
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tks;

    static String ns() throws Exception {
        if (tks == null || !tks.hasMoreTokens())
            tks = new StringTokenizer(br.readLine());
        return tks.nextToken();
    }

    static int ni() throws Exception {
        return Integer.parseInt(ns());
    }
}
