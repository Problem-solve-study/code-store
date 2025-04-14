//제출번호:	93101198
//메모리:	65788 KB
//실행시간:	684 ms
import java.io.*;
import java.util.*;

//1~idx까지 더한 누적합을 d로 나눈 나머지를 모든 idx에 대해 구한다
//나머지가 같은 2개의 idx를 선택하면 그 사이 구간은 d로 나누어 떨어진다
//이 떄 나머지가 같은 idx들 중 임의의 2개를 선택하면 하나의 부분 수열을 만들 수 있다

/*
예를 들어서 아래와 같이 나머지를 가지는 배열이 있다고 생각해보자
 ----a-----a----a---a----a----
(a 말고는 다 필요없는 수라서 - 처리했음)
서로 다른 2개의 a 위치를 선택하면 겹치지 않는 하나의 부분 수열을 만들 수 있다
따라서 a에 대해서 만들 수 있는 부분 수열의 개수는 5C2다

모든 나머지의 idx 개수에 대해서 (개수) C 2의 합을 구하면 답을 만들 수 있음
*/
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        Map<Integer, Integer> mod = new TreeMap<>();

        StringBuilder sb = new StringBuilder();
        int T = nextInt();
        for (int t = 0; t < T; t++) {
            int d = nextInt();
            int n = nextInt();

            mod.clear();

            int sum = 0;
            mod.put(0, 1); //시작점도 나머지가 0인 위치가 될 수 있음
            for (int i = 0; i < n; i++) {
				//모든 idx에 대해서 1~idx의 누적합을 d로 나눈 나머지를 구하고
                sum = (sum + nextInt()) % d; 

				//맵에다가 개수를 기록한다.
                mod.put(sum, mod.getOrDefault(sum, 0) + 1);
            }

            long res = 0;
			//배열에 있는 모든 나머지에 대해서
            for (int key : mod.keySet()) {
                int cnt = mod.get(key);

				//cnt C 2를 계산한다.
                res += 1L * cnt * (cnt - 1) / 2;
            }

            sb.append(res).append('\n');
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
