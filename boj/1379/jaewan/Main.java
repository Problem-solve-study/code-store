
// 28964 KB, 456 ms
/* 
 * 강의를 시작 시간 오름차순으로 정렬
 * 강의실 정보를 저장하는 PQ에 <강의실 번호, 강의 종료 시간> 을 종료 시간 오름차순으로 정렬
 * 
 * 이제 시작 시간 오름차순으로 정렬된 강의를 순차 배정
 * PQ의 첫번째, 즉 가장 빨리 끝나는 강의실의 종료 시간보다 시작 시간이 빠르면 새 강의실을 만들어 배정
 * 기존 강의실에 배정 가능하면 종료 시간 갱신해 PQ에 넣기
 * 
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) throws IOException {
        int N = readInt();
        Class[] classInfo = new Class[N];
        for (int i = 0; i < N; i++)
            classInfo[i] = new Class(readInt(), readInt(), readInt());

        Arrays.sort(classInfo);

        int roomCnt = 0;
        PriorityQueue<Room> pq = new PriorityQueue<>();
        pq.offer(new Room(0, Integer.MAX_VALUE));
        int[] roomNums = new int[N + 1];
        for (int i = 0; i < N; i++) {
            Room fastestEndRoom = pq.poll();
            // 새 방에 배정
            if (classInfo[i].startTime < fastestEndRoom.endTime) {
                roomNums[classInfo[i].no] = ++roomCnt;
                pq.offer(fastestEndRoom);
                pq.offer(new Room(roomCnt, classInfo[i].endTime));
            }
            // 기존 방에 배정, 갱신
            else {
                roomNums[classInfo[i].no] = fastestEndRoom.no;
                fastestEndRoom.endTime = classInfo[i].endTime;
                pq.offer(fastestEndRoom);
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(roomCnt).append('\n');
        for (int i = 1; i <= N; i++)
            sb.append(roomNums[i]).append('\n');
        System.out.print(sb);
    }

    static class Class implements Comparable<Class> {
        int no, startTime, endTime;

        public Class(int no, int startTime, int endTime) {
            this.no = no;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Main.Class o) {
            return startTime - o.startTime;
        }
    }

    static class Room implements Comparable<Room> {
        int no, endTime;

        public Room(int no, int endTime) {
            this.no = no;
            this.endTime = endTime;
        }

        @Override
        public int compareTo(Main.Room o) {
            return endTime - o.endTime;
        }
    }

    static int pos, len;
    static byte[] buf = new byte[8192];

    static byte read() throws IOException {
        if (pos == len) {
            len = System.in.read(buf);
            pos = 0;
        }
        return buf[pos++];
    }

    static int readInt() throws IOException {
        int c;
        while ((c = read()) <= 32)
            ;
        int n = c & 15;
        while ((c = read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}