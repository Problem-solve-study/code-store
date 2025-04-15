//제출번호:	93128943
//메모리:	161488 KB
//실행시간:	1316 ms
import java.io.*;
import java.util.*;

//처음에 파라메트릭 서치로 접근함
//최대 K개까지 뺄 수 있다는 것을 어떻게 구현할까 고민했는데
//시간이 2초나 되길래 PQ를 이용해서 구현해보기로 함
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int g = nextInt();
        int k = nextInt();

        int[] s = new int[n];
        int[] l = new int[n];
        int[] o = new int[n];
        for (int i = 0; i < n; i++) {
            s[i] = nextInt();
            l[i] = nextInt();
            o[i] = nextInt();
        }

		//범위 넘어가는 것을 방지하기 위해서 long으로 선언
		//밀키트를 산 날짜로부터 mid만큼 지난 날의 상태를 구할 것
        long left = 0, right = (int) 2e9, mid;
        PriorityQueue<Long> pq = new PriorityQueue<>();
        while (left <= right) {
            mid = (left + right) / 2;

            pq.clear(); //이전 값이 남아 있을 수 있기 때문에 PQ 초기화

            long germSum = 0;
            for (int i = 0; i < n; i++) {
				//세균 수를 수식에 맞게 구함
                long germ = s[i] * Math.max(1, mid - l[i]);

				//만약에 반드시 넣어야 하는 재료면 세균 수를 germSum에 바로 더함
                if (o[i] == 0) {
                    germSum += germ;
                } else {
					//반드시 넣지 않아도 되는 재료면 잠시 빼둠
                    pq.add(germ);
                }
            }

			//최대 K개를 선택하지 않을 때까지 pq에서 빼서 germSum에 넣음
			//pq에 남은 재료들이 최종적으로 선택하지 않는 재료들임
            while (pq.size() > k) {
                germSum += pq.poll();
            }

			//만약 선택한 재료들의 세균 수의 합이 g 이하이면 날짜를 늘려봄
            if (germSum <= g) {
                left = mid + 1;
            } else {
				//세균 수의 합이 g보다 크면 못 먹으니까 날짜를 줄여봄
                right = mid - 1;
            }
        }

		//right가 최댓값을 가지고 있으므로 right 출력
        System.out.print(right);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}