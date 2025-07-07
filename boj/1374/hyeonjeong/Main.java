import java.io.*;
import java.util.*;

// 강의를 (시작 시간, 종료 시간)을 기준으로 정렬 -> 강의를 탐색하면서, 강의실 중에 종료 시간이 현재 강의의 시작 시간보다 빠른 강의실이 없으면 강의실 추가
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        final int START = 0;
        final int END = 1;

        int n = nextInt();
        int[][] classes = new int[n][2];
        for (int i = 0; i < n; i++) {
            st.nextToken();     // 수업 번호 버리기
            classes[i][START] = nextInt();
            classes[i][END] = nextInt();
        }

        Arrays.sort(classes, (c1, c2) -> c1[START] == c2[START] ? c1[END] - c2[END] : c1[START] - c2[START]);
            
        PriorityQueue<Integer> rooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            Integer min = rooms.peek();

            // i번째 수업을 시작할 수 있는 강의실이 있으면, 해당 강의실을 업데이트
            if (min != null && min <= classes[i][START]) {
                rooms.poll();
            }

            // i번째 수업 추가
            rooms.add(classes[i][END]);
        }

        System.out.println(rooms.size());
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
