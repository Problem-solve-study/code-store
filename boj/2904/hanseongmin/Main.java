import java.io.*;
import java.util.*;

/*
14024KB, 272ms

두 수 A와 B를 고르고, A를 나눌 수 있는 소수 X를 고른다. 그 다음, A를 지우고 A/X를 쓰고, B를 지우고 B×X를 쓴다.
->
결국 A에 있는 소인수 중 하나를 B에게 나누어준다는 말

점수는 종이에 적혀있는 모든 수의 최대공약수이므로 소인수들을 모든 원소에게 분배해주면 최대의 점수를 얻을 수 있음
구현하다 실수해서 3틀함
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    //소인수를 구하기 위한 에라토스테네스의 체
    static boolean[] isNotPrime = new boolean[1000001];
    static {
        for (int i = 2; i <= 1000000; i++) {
            if (isNotPrime[i]) continue;
            for (int j = 2; i * j <= 1000000; j++) {
                isNotPrime[i * j] = true;
            }
        }
    }

    static class Data {
        int num;
        HashMap<Integer, Integer> map = new HashMap<>();

        //입력받으며 바로 소인수분해 수행
        public Data(int num) {
            this.num = num;

            int temp = num;
            for (int i = 2; i <= num && temp != 0; i++) {
                if (isNotPrime[i]) continue;
                while (temp % i == 0) {
                    map.put(i, map.getOrDefault(i, 0) + 1);
                    temp /= i;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        Data[] datas = new Data[N];
        for (int i = 0; i < N; i++) {
            datas[i] = new Data(nextInt());
        }

        //입력받은 모든 수에 대해 소인수들의 개수를 카운팅
        HashMap<Integer, Integer> cntMap = new HashMap<>();
        for (Data d : datas) {
            for (Map.Entry<Integer, Integer> e : d.map.entrySet()) {
                cntMap.put(e.getKey(), cntMap.getOrDefault(e.getKey(), 0) + e.getValue());
            }
        }

        //분배하기 위해 소인수의 개수를 전체 수의 개수로 나눔
        //이 결과로 1 이상이 나온 소인수는 모든 원소에 배분할 수 있음
        for (int key : cntMap.keySet()) {
            cntMap.put(key, cntMap.get(key) / N);
        }

        //결과로 나올 점수를 미리 계산하고
        int ans = 1;
        for (Map.Entry<Integer, Integer> e : cntMap.entrySet()) {
            if (e.getValue() == 0) continue;
            ans *= (int) Math.pow(e.getKey(), e.getValue());
        }

        //연산을 몇번 해야하는지 계산
        int cnt = 0;
        for (Data d : datas) {
            //현재 수를 대상으로 분배할 소인수를 가지고 있지 않으면 다른 수에서 적절히 뺏어옴
            //소인수의 실제 이동은 고려하지 않고 연산이 이루어졌다고만 기록
            for (Map.Entry<Integer, Integer> e : cntMap.entrySet()) {
                if (e.getValue() == 0) continue;
                int diff = e.getValue() - d.map.getOrDefault(e.getKey(), 0);
                if (diff > 0) cnt += diff;
            }
        }

        System.out.print(ans + " " + cnt);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
