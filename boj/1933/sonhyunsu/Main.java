//제출번호: 91562606
//메모리:   41792 KB
//실행시간: 836 ms
import java.io.*;
import java.util.*;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        List<int[]> arr = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            int left = nextInt();
            int height = nextInt();
            int right = nextInt();

            //왼쪽에 높이가 시작되는 위치, 오른쪽에 높이가 끝나는 위치를 기록
            arr.add(new int[]{left, height, i});
            arr.add(new int[]{right, -height, i});
        }

        //위치를 기준으로 먼저 정렬하고,
        //같은 위치라면 높이가 시작되는 위치가 높은게 먼저 나오도록 함
        //자연스럽게 같은 위치라도 높이가 끝나는 건물보다 높이가 시작하는 건물이 먼저 나옴
        arr.sort((i1, i2) -> {
            if (i1[0] == i2[0]) {
                return -Integer.compare(i1[1], i2[1]);
            }
            return Integer.compare(i1[0], i2[0]);
        });

        //이미 빠진 건물을 체크하기 위해서 boolean 배열 생성
        boolean[] isEnded = new boolean[n + 1];
        //높이가 가장 높은 건물이 먼저 나오도록 PQ 생성
        PriorityQueue<int[]> pq = new PriorityQueue<>((i1, i2) -> -Integer.compare(i1[0], i2[0]));
        StringBuilder sb = new StringBuilder();

        //바닥에 항상 높이가 0인 건물이 있다고 만들어버림
        pq.add(new int[]{0, 0});
        for (int[] item : arr) {
            int pos = item[0]; //위치
            int height = item[1]; //높이
            int order = item[2]; //건물

            //만약 높이가 시작되는 위치일 때
            if (height > 0) {
                //이전에 가장 높았던 건물보다 더 높다면 스카이라인이 갱신됨
                if (pq.peek()[0] < height) {
                    sb.append(pos).append(' ').append(height).append(' ');
                }

                //현재 건물의 높이를 pq에 추가
                pq.add(new int[]{height, order}); 
            } else {
                //높이가 끝나는 위치일 때
                isEnded[order] = true;

                //만약에 빠지는 건물이 PQ에 저장된 가장 높은 건물이라면
                if (pq.peek()[0] == -height && pq.peek()[1] == order) {
                    //이미 빠진 건물들을 PQ에서 모두 뺌
                    while (isEnded[pq.peek()[1]]) {
                        pq.poll();
                    }

                    //아직 안 빠진 건물들 중 가장 높은 건물이
                    //지금 빠지는 건물보다 더 낮다면 스카이라인이 갱신됨
                    if (pq.peek()[0] < -height) {
                        sb.append(pos).append(' ').append(pq.peek()[0]).append(' ');
                    }
                }
            }
        }

        //스카이라인 출력
        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}