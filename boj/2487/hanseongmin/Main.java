import java.io.*;
import java.math.BigInteger;
import java.util.*;

/*
17088KB, 136ms

주어진 숫자대로 수를 섞었을 때 다시 원래 수열로 돌아오기 위한 연산 횟수 구하기
원래의 수열로는 반드시 돌아올 수 있는듯 함

궤적의 수의 범위가 크다. 단순 시뮬레이션으로 푸는 문제는 아닌 것 같고. 매개변수 탐색인가?
매개변수 탐색이라기엔 특정 범위를 만족했을 때 원래 수열대로 돌아갈 수 없을듯.

일단 예제를 돌려볼까 각 숫자마다 어떻게 움직이는지를 보자
1: 1 5 3 1 반복
2: 2 반복
3: 3 1 5 3 반복
4: 4 6 4 반복
5: 5 3 1 5 반복
6: 6 4 6 반복

1, 3, 5는 원래 자리로 돌아오기 위해서 3번의 섞기가 필요.
2는 항상 원래 자리
4, 6은 원래 자리로 돌아오기 위해서 2번의 섞기가 필요.
나온 수들의 LCM이 6.

각 자리수별로 얼마만큼의 연산이 필요한지를 구하고 LCM 구하기인듯.
각 자릿수별로 시뮬레이션을 돌려보고 섞기 횟수를 구하자. 궤적이 나타는 숫자들은 모두 같은 섞기 횟수를 가지므로
boolean 배열로 중복 계산하지 말고 한 번에 계산.

LCM을 잘못 구현해서 1틀 ㅜ
이 문제처럼 수가 반복되는걸 순열 사이클 분할이라고 하구나 처음 알았다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static BigInteger lcm;
    static int n;
    static HashMap<Integer, Integer> map = new HashMap<>(); //현재 인덱스, 이동할 인덱스
    static boolean[] v;

    public static void main(String[] args) throws Exception {
        n = nextInt();
        v = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            map.put(nextInt(), i);
        }

        for (int i = 1; i <= n; i++) {
            if (!v[i]) {
                findTrace(i);
            }
        }

        System.out.println(lcm);
    }

    static void findTrace(int i) {
        //덱을 이용해서 순열 사이클 분할
        ArrayDeque<Integer> d = new ArrayDeque<>();
        while (!v[i]) {
            d.addLast(i);
            v[i] = true;
            i = map.get(i);
        }

        //lcm 구하기
        BigInteger dSize = BigInteger.valueOf(d.size());
        lcm = lcm == null ? dSize : lcm.multiply(dSize).divide(lcm.gcd(dSize));
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
