import java.io.*;
import java.util.*;

/*
23716KB, 272ms

N이 1000이므로 3중 반복문으로는 시간초과가 날 것이라고 생각했다.
그래서 2중 반복문으로 답을 구해봐야겠다는 생각이 들었고 A와 B를 고정시킨 후 C를 이분 탐색으로 찾아버리면 될 것 같았다.
모든 경우를 고려하지 못해서 1틀
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int A = nextInt();
        int B = nextInt();
        int C = nextInt();
        int[] aNums = new int[A];
        int[] bNums = new int[B];
        //C는 이분 탐색을 활용할 것이므로 트리셋 사용
        TreeSet<Integer> cNums = new TreeSet<>();
        for (int i = 0; i < A; i++) {
            aNums[i] = nextInt();
        }
        for (int i = 0; i < B; i++) {
            bNums[i] = nextInt();
        }
        for (int i = 0; i < C; i++) {
            cNums.add(nextInt());
        }

        int res = Integer.MAX_VALUE;
        //A와 B를 고정
        for (int i = 0; i < A; i++) {
            for (int j = 0; j < B; j++) {
                int max = Math.max(aNums[i], bNums[j]);
                int min = Math.min(aNums[i], bNums[j]);
                //먼저 c값 중 min과 max의 사이에 존재하는 값이 있는지 찾아봄
                Integer between = cNums.ceiling(min);
                if (between != null && (min <= between && between <= max)) {
                    //만일 min, max 사이에 C가 존재한다면 값이 바뀌지 않으므로 그대로 계산
                    res = Math.min(res, Math.abs(max - min));
                } else {
                    //그게 아니라면 min의 floor과 max의 ceiling을 가져와서 더 작은 벌점으로 답 갱신 시도
                    Integer l = cNums.floor(min);
                    Integer r = cNums.ceiling(max);
                    if (l != null) {
                        res = Math.min(res, Math.abs(max - l));
                    }
                    if (r != null) {
                        res = Math.min(res, Math.abs(r - min));
                    }
                }
            }
        }
        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
