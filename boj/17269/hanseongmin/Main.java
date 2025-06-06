import java.io.*;
import java.util.*;

/*
 * 11712KB, 76ms
 * 
 * 그냥 문제에서 주어진대로 구현만 하면됨
 * 획수만 제대로 넣어뒀다면 딱히 실수할만한 건덕지도 없는듯
 */

public class Main {
    static HashMap<Character, Integer> map = new HashMap<>();
    static {
        map.put('A', 3); map.put('B', 2); map.put('C', 1);
        map.put('D', 2); map.put('E', 4); map.put('F', 3);
        map.put('G', 1); map.put('H', 3); map.put('I', 1);
        map.put('J', 1); map.put('K', 3); map.put('L', 1);
        map.put('M', 3); map.put('N', 2); map.put('O', 1);
        map.put('P', 2); map.put('Q', 2); map.put('R', 2);
        map.put('S', 1); map.put('T', 2); map.put('U', 1);
        map.put('V', 1); map.put('W', 1); map.put('X', 2);
        map.put('Y', 2); map.put('Z', 1);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String A = st.nextToken();
        String B = st.nextToken();
        System.out.print(getScore(concatName(A, B)) + "%");
    }

    static String concatName(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int aIdx = 0;
        int bIdx = 0;

        while (!(aIdx == a.length() && bIdx == b.length())) {
            if (aIdx != a.length()) {
                sb.append(a.charAt(aIdx++));
            }

            if (bIdx != b.length()) {
                sb.append(b.charAt(bIdx++));
            }
        }

        return sb.toString();
    }

    static int getScore(String name) {
        ArrayList<Integer> oldList = new ArrayList<>();
        ArrayList<Integer> newList = new ArrayList<>();
        for (int i = 0; i < name.length(); i++) {
            oldList.add(map.get(name.charAt(i)));
        }

        while (oldList.size() != 2) {
            for (int i = 0; i < oldList.size() - 1; i++) {
                newList.add((oldList.get(i) + oldList.get(i + 1)) % 10);
            }

            oldList = new ArrayList<>(newList);
            newList.clear();
        }

        return oldList.get(0) * 10 + oldList.get(1);
    }
}