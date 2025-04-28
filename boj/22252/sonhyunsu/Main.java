//제출번호: 93660703
//메모리:   46852 KB
//실행시간: 624 ms
import java.io.*;
import java.util.*;

//문제 읽어보니까 그냥 고릴라마다 가진 정보를 PQ에 넣고 호석이가 살 때 PQ에서 뽑으면 되겠다 싶었음
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int q = nextInt();

        //고릴라마다 PQ를 만들기 위해서 맵 생성
        Map<String, PriorityQueue<Integer>> gorilas = new TreeMap<>();
        long res = 0;
        for (int i = 0; i < q; i++) {
            int type = nextInt();
            String name = nextString();
            int k = nextInt();
            if (type == 1) {
                //고릴라가 정보를 얻으면
                PriorityQueue<Integer> pq = gorilas.getOrDefault(name, new PriorityQueue<>(Comparator.reverseOrder()));
                for (int j = 0; j < k; j++) {
                    pq.add(nextInt()); //pq에 추가
                }
                gorilas.put(name, pq);
            } else {
                //호석이가 정보를 사면
                PriorityQueue<Integer> pq = gorilas.getOrDefault(name, new PriorityQueue<>(Comparator.reverseOrder()));
                while (k-- > 0 && !pq.isEmpty()) {
                    res += pq.poll(); //가장 큰 가치를 지닌 정보부터 k개를 삼
                }
            }
        }

        //결과 출력
        System.out.print(res);
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