//제출번호: 94025368
//메모리:   15976 KB
//실행시간: 276 ms
import java.io.*;
import java.util.*;

//모든 부서의 퇴근 시간의 합이 최소가 되기 위해서는
//각 부서 당 면담에 소요되는 시간들을 계산하고 면담 시간이 가장 짧은 부서부터 퇴근시키면 됨
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        List<Integer> times = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            int cnt = nextInt();
            int time = 0;
            for (int j = 0; j < cnt; j++) {
                time += nextInt();
            }

            times.add(time); //각 부서의 면담 시간의 합을 저장 
        }

        times.sort(Comparator.naturalOrder()); //짧은 순으로 정렬

        long res = 0;
        int sum = 0;
        for (int time : times) {
            res += sum += time; //면담 시간의 누적합을 res에 더함
        }

        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}