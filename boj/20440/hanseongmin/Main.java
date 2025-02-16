import java.io.*;
import java.util.*;

/*
126472KB, 644ms

imos 누적합 문제라는 것은 빠르게 캐치할 수 있지만 시각의 범위가 매우 크기 때문에 단순히 배열에 저장할 수 없다.
시작 구간과 끝 구간을 1, -1 값과 함께 ArrayList에 넣어준 뒤 구간 값을 기준으로 정렬한 후 list에서 하나씩 꺼내보며
값을 업데이트 해주는 방식으로 풀 수 있다. 교육적인 좋은 문제인 거 같음

'구간이 가장 길면서' 시작 시간이 가장 빠른 구간을 출력하는 줄 알고 한참을 틀렸다..
 */

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StreamTokenizer st = new StreamTokenizer(br);

    static class Segment implements Comparable<Segment> {
        int pos;
        int value;

        public Segment(int pos, int value) {
            this.pos = pos;
            this.value = value;
        }

        @Override
        public int compareTo(Segment o) {
            int comp1 = Integer.compare(pos, o.pos);
            if (comp1 != 0) return comp1;
            return Integer.compare(value, o.value);
        }
    }

    public static void main(String[] args) throws Exception {
        //입력
        int n = nextInt();
        ArrayList<Segment> segments = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            segments.add(new Segment(nextInt(), 1));
            segments.add(new Segment(nextInt(), -1));
        }

        //선분 합치기
        Collections.sort(segments);
        ArrayList<Segment> temp = new ArrayList<>();
        temp.add(segments.get(0));
        for (int i = 1; i < segments.size(); i++) {
            Segment prev = temp.get(temp.size() - 1);
            Segment cur = segments.get(i);
            if (prev.value + cur.value == 0 && prev.pos == cur.pos) {
                temp.remove(temp.size() - 1);
            } else {
                temp.add(cur);
            }
        }
        segments = temp;

        int max = 0;
        int cnt = 0;
        int l = Integer.MAX_VALUE;
        int r = Integer.MAX_VALUE;
        for (int i = 0; i < segments.size() - 1; i++) {
            Segment cur = segments.get(i);
            cnt += cur.value;

            if (cnt > max) {
                //모기의 수가 갱신되면 구간 갱신
                max = cnt;
                l = cur.pos;
                r = segments.get(i + 1).pos;
            }
        }

        bw.write(String.format("%d%n%d %d", max, l, r));
        bw.flush();
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
