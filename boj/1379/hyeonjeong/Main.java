import java.io.*;
import java.util.*;

// 그리디, Heap
// 시작 시간이 빠른 강의부터 강의실 배정
// 강의 종료 시간이 가장 빠른 강의실에서 시작할 수 있으면 거기서 시작, 없으면 새 강의실 추가
class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        final int LEC_NUM = 0;
        final int LEC_START = 1;
        final int LEC_END = 2;
        final int ROOM_NUM = 0;
        final int ROOM_END = 1;

        int n = next();

        int[][] lectures = new int[n][3];
        for (int i = 0; i < n; i++) {
            lectures[i][LEC_NUM] = next() - 1;
            lectures[i][LEC_START] = next();
            lectures[i][LEC_END] = next();
        }

        Arrays.sort(lectures, (l1, l2) -> l1[LEC_START] - l2[LEC_START]);

        PriorityQueue<int[]> rooms = new PriorityQueue<>((r1, r2) -> r1[ROOM_END] - r2[ROOM_END]);
        Map<Integer, Integer> lectureToRoom = new HashMap<>();
        for (int[] lecture : lectures) {
            // 강의실이 없으면 강의실 추가
            if (rooms.peek() == null || rooms.peek()[ROOM_END] > lecture[LEC_START]) {

                int roomNumber = rooms.size() + 1;

                rooms.add(new int[] { roomNumber, lecture[LEC_END] });
                lectureToRoom.put(lecture[LEC_NUM], roomNumber);
                continue;
            }

            // 강의실이 있으면 기존 강의실 업데이트
            int[] room = rooms.poll();
            room[ROOM_END] = lecture[LEC_END];
            rooms.add(room);
            lectureToRoom.put(lecture[LEC_NUM], room[ROOM_NUM]);
        }

        StringBuilder sb = new StringBuilder();
        sb.append(rooms.size()).append('\n');
        for (int i = 0; i < n; i++) {
            sb.append(lectureToRoom.get(i)).append('\n');
        }

        System.out.println(sb);
    }
    
    static int next() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
