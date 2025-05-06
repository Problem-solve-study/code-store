import java.io.*;
import java.util.*;

/*
 * 19296KB, 284ms
 * 
 * 
 * 꺼야할 시간과 켜야할 시간을 식별
 * 켜야하는 경우가 K번과 같다면 켜야할 시간들의 합산
 * 크다면 꺼야할 시간들 중 구간의 길이가 작은 걸 선택해서 그냥 켜버리기
 * 
 * 1번째 친구는 1에 도착 2에 나감
 * 2번째 친구는 3에 도착 4에 나감
 * 3번째 친구는 6에 도착 7에 나감
 * 
 *      |---|   |---|       |---|
 * 0    1   2   3   4   5   6   7
 * 3번 켜야하는데 K가 2이므로 특정 구간은 켜고 있어야 함
 * 켤 구간의 후보는 2 ~ 3, 4 ~ 6이므로 구간이 더 작은 2 ~ 3을 선택.
 * 그럼 1 ~ 4, 6 ~ 7이 켜져있으므로 총 4
 * 
 *      |---|---|           |---|
 * 0    1   2   3   4   5   6   7
 * 켤 구간이 연속되어 있다면 하나의 구간으로 취급해야함
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt(); int K = nextInt();

        long res = 0;
        PriorityQueue<Integer> offSegment = new PriorityQueue<>();
        int cnt = 1;
        int prev = 0;
        for (int i = 0; i < N; i++) {
            int t = nextInt();
            //최초로 입력받은 경우거나, 구간이 이어져있는 경우가 아니라면
            //꺼야할 시간의 크기를 힙에 저장(구간의 길이가 작은걸 빠르게 뽑기 위함)
            //또한 구간의 수 + 1
            if (prev != 0 && prev + 1 != t) {
                offSegment.add(t - (prev + 1));
                cnt++;
            }

            //입력을 받았다면 1만큼의 시간은 반드시 켜고 있어야함(친구가 있다면 켜야하므로)
            res += 1;
            prev = t;
        }

        //꺼져있던 시간 중 크기가 가장 작은걸 차례대로 뽑아 그냥 켜버림
        for (int i = 0; i < cnt - K; i++) {
            res += offSegment.remove();
        }

        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}