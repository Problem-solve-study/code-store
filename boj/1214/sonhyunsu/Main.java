
//제출번호: 90845056
//메모리:   11648 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//솔직히 시간복잡도 계산이 잘 안 되서 시초를 각오하고 믿음으로 제출함

//일단 d가 p 또는 q로 나누어 떨어지면 d를 그대로 낼 수 있음
//안 된다면 일단 p와 q 중에 큰 값 기준으로 d 이상의 금액을 만들고
//큰 값을 1개씩 빼고 빈 금액을 작은 값으로 채워보면서 최소 금액을 만들어 봄
//그렇게 만들다 보면 최소 금액의 사이클이 생길 것이라고 생각함

//이 때 큰 값이 32000 이상이라면 최대 32000 번만 반복하면 되고
//큰 값이 32000 이하라면 사이클 금액의 개수가 32000 개 이하로만 나오기 때문에
//시간 안에 되지 않을까 생각함
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static Set<Integer> set = new HashSet<>();

    public static void main(String[] args) throws IOException {
        int d = nextInt();
        int p = nextInt();
        int q = nextInt();

        int res = Integer.MAX_VALUE;
        // d가 p 또는 q로 나눠지면 d 금액을 바로 만들 수 있음
        if (d % p == 0 || d % q == 0) {
            res = d;
        } else {

            // 큰 값과 작은 값을 골라냄
            int max = Math.max(p, q);
            int min = Math.min(p, q);

            // 초기 금액을 큰 값으로만 만들어 냄
            int val = max * (d / max) + max;
            int count = d / max + 1; // 큰 지폐의 개수를 세어둠

            // 큰 지폐를 하나씩 없애보면서 계산해 봄
            // 사이클을 확인하기 위해서 set 사용
            while (count-- >= 0 && !set.contains(val)) {
                set.add(val);
                res = Math.min(res, val);

                // 만약 만든 금액이 d라면 d를 내는 방법이 존재하는 것
                if (val == d) {
                    break;
                }

                // 큰 지폐를 한 장 빼고, 작은 지폐를 필요한 금액만큼 더함
                val -= max;
                val += min * ((d - val) / min);
                // d - val이 min에 나누어 떨어질 수 있기 때문에
                // if문을 이용해서 더 필요한만큼 더 함
                if (val < d) {
                    val += min;
                }
            }
        }

        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}