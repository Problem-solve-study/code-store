// 143472 KB, 976 ms
/*
 * 정보 고릴라에게 정보를 구매
 * 어떤 고릴라가 어떤 가치가 있는 정보 k 개를 얻었다는 사실을 알고 있음.
 * 
 * 특정 고릴라에게 정보를 몇 개 살 것인지 정함.
 * 가치 순으로 가장 비싼 정보들을 구매
 * 
 * 고릴라가 가진 정보가 10개, 사고 싶은 정보 개수가 4개면, 가치 비싼 순으로 4개
 * 사고 나면 정보 파기
 * 
 * 1 Name k C[1], C[2], ... , C[k] : 이름이 Name 인 고릴라가 k개의 정보를 얻었으며, 각 가치들~
 * 2 Name b : Name에게 b개의 정보를 구매. 가장 비싼 b개, 가진 게 b개 이하면 모든 정보 구매
 * 
 * 호석이가 가진 정보들의 총합, 구매하는 데 쓴 돈의 총합.
 * 
 * Q <= 100,000
 * Name 은 소문자 혹은 대문자, 길이 15 이하
 * k <= 100,000
 * C <= 100,000
 * b <= 100,000
 * 
 * 모든 쿼리의 k 합은 1,000,000을 넘지 않음.
 * 
 * 처음에 마지막 줄 못 보고, 최악의 경우 1e10개의 데이터가 들어오는 줄 알았음;
 * 하지만 1e6이니까, 단순히 PQ로 구현하면 될듯
 * 
 * Map
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Collections;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws NumberFormatException, IOException {
        int N = nextInt();

        long cnt = 0;
        HashMap<String, PriorityQueue<Integer>> 고릴라 = new HashMap<>();
        for (int i = 0; i < N; i++) {
            int opt = nextInt();
            String name = nextString();

            switch (opt) {
                case 1:
                    if (!고릴라.containsKey(name)) {
                        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
                        고릴라.put(name, pq);
                    }
                    PriorityQueue<Integer> pq = 고릴라.get(name);
                    int k = nextInt();
                    for (int j = 0; j < k; j++)
                        pq.offer(nextInt());
                    break;

                case 2:
                    if (!고릴라.containsKey(name)) {
                        st.nextToken();
                        break;
                    }
                    pq = 고릴라.get(name);
                    int b = nextInt();
                    while (b-- > 0 && !pq.isEmpty())
                        cnt += pq.poll();
                    break;
            }
        }

        System.out.println(cnt);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }

    static String nextString() throws IOException {
        st.nextToken();
        return st.sval;
    }
}