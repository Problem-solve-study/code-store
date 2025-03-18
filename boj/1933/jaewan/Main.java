
// 29952 KB, 592 ms
/*
 * 왼쪽 좌표 순서대로 정렬
 * 정렬 후에는 높이 순서대로 PQ에 저장.
 * 
 * 들어오는 경우
 * 1. pq에 높이 0짜리 시작 0, 높이 0, 끝 1,000,000,001 으로 바닥 저장
 * 2. 새로 들어오는 건물의 시작이 현재 최고높이 건물의 끝보다 크면, 높이가 변한다.
 * 2-1. 최고높이 끝보다 작은 것들 같이 pq에서 제거, 높이 달라졌으면 최고 높이를 출력.
 * 2-2. 또 종료되면 반복, 최고 높이의 right가 새로 들어온 건물의 left보다 크거나 같아질 때까지.
 * 3. 새로 들어오는 건물이 현재 높이보다 작으면, 그냥 pq에 넣는다.
 * 4. 새로 들어오는 건물이 현재 높이보다 크면, 높이가 변하므로 출력.
 */
import java.io.IOException;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {
    static int N;
    static Building[] buildings;

    public static void main(String[] args) throws IOException {
        N = readInt();
        buildings = new Building[N];
        for (int i = 0; i < N; i++)
            buildings[i] = new Building(readInt(), readInt(), readInt());
        Arrays.sort(buildings, (b1, b2) -> b1.left == b2.left ? b2.height - b1.height : b1.left - b2.left);

        PriorityQueue<Building> pq = new PriorityQueue<>((b1, b2) -> b2.height - b1.height);
        pq.add(new Building(0, 0, 1000000001));
        Building cur;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            // 현재 최고높이보다 다음 건물 시작 위치가 크다 == 현재 건물이 끝난다 == 변화가 있다(건물 낮아짐)
            while (pq.peek().right < buildings[i].left) {
                cur = pq.poll();
                // 현재 건물 끝나고, pq에 있는 현재 건물보다 right가 작거나 같은 것 다 제거
                while (pq.peek().right <= cur.right)
                    pq.poll();
                // 높이 달라졌으면
                if (cur.height != pq.peek().height)
                    sb.append(cur.right).append(' ').append(pq.peek().height).append(' ');
            }

            // 현재 높이보다 높아졌다 == 변화가 있다
            if (pq.peek().height < buildings[i].height) {
                sb.append(buildings[i].left).append(' ').append(buildings[i].height).append(' ');
            }

            pq.add(buildings[i]);
        }

        while (pq.size() > 1) {
            cur = pq.poll();
            while (pq.peek().right <= cur.right)
                pq.poll();
            // 높이 달라졌으면
            if (cur.height != pq.peek().height)
                sb.append(cur.right).append(' ').append(pq.peek().height).append(' ');
        }

        System.out.println(sb.toString());
    }

    static int readInt() throws IOException {
        int c;
        do {
            c = System.in.read();
        } while (c <= 32);
        int n = c & 15;
        c = System.in.read();
        while (c > 47) {
            n = (n << 3) + (n << 1) + (c & 15);
            c = System.in.read();
        }
        return n;
    }

    static class Building {
        int left, height, right;

        public Building(int left, int height, int right) {
            this.left = left;
            this.height = height;
            this.right = right;
        }

    }
}
