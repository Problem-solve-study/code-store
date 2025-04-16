import java.io.*;
import java.util.*;

/*
65732KM, 792ms

가장 가까운건 TreeSet의 higher, lower를 쓰면 된다는걸 알았지만 P 값만큼 움직여야한다는 점을 찾는게 어려웠음

그림판에 그려보니 특정 P에 대해 AD 방향의 대각선은 두 좌표의 차가 동일하다는 것을 알 수 있었고
BC 방향의 대각선은 두 좌표의 합이 동일하다는 것을 알 수 있었음
그리고 이 값은 대각선의 위치마다 달라지므로 이 값으로 대각선을 식별한다면
특정한 점이 주어졌을 때 이 점이 어떤 대각선에 위치하며,
동시에 해당 대각선에 어떤 점들이 존재하는지를 map을 통해 바로 구할 수 있을 것이라 생각했다.
이를 바로 구할 수 있다면 higher와 lower를 이용하여 logN만에 다음 점을 구할 수 있으므로 이를 이용했다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        TreeMap<Integer, TreeSet<int[]>> adMap = new TreeMap<>();
        TreeMap<Integer, TreeSet<int[]>> bcMap = new TreeMap<>();

        int N = nextInt(); int K = nextInt();
        String directions = nextToken();
        int[] curPos = null;
        for (int i = 0; i < N; i++) {
            int[] arr = new int[] {nextInt(), nextInt()};
            if (i == 0) {
                curPos = arr;
            }

            int ad = arr[0] - arr[1]; //AD 방향 대각선을 식별하기 위한 식별자
            int bc = arr[0] + arr[1]; //BC 방향 대각선을 식별하기 위한 식별자
            //둘 모두 x가 큰 원소를 큰 원소로 취급
            adMap.putIfAbsent(ad, new TreeSet<>(Comparator.comparingInt(e -> e[0] + e[1])));
            bcMap.putIfAbsent(bc, new TreeSet<>(Comparator.comparingInt(e -> e[0] - e[1])));
            adMap.get(ad).add(arr);
            bcMap.get(bc).add(arr);
        }

        for (char c : directions.toCharArray()) {
            int[] next = null;
            int ad = curPos[0] - curPos[1];
            int bc = curPos[0] + curPos[1];
            TreeSet<int[]> adSet = adMap.get(ad);
            TreeSet<int[]> bcSet = bcMap.get(bc);
            if (c == 'A') {
                next = adSet.higher(curPos);
            } else if (c == 'B') {
                next = bcSet.higher(curPos);
            } else if (c == 'C') {
                next = bcSet.lower(curPos);
            } else {
                next = adSet.lower(curPos);
            }

            if (next != null) {
                adSet.remove(curPos); bcSet.remove(curPos);
                curPos = next;
            }
        }
        System.out.print(curPos[0] + " " + curPos[1]);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextToken() throws Exception {
        st.nextToken();
        return st.sval;
    }
}