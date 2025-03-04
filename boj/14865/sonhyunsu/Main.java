import java.io.*;
import java.util.*;

public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {

        int n = nextInt();

        int x = nextInt();
        int y = nextInt();
        int initX = x;
        int initY = y;
        int nx = 0;
        int ny = 0;
        boolean isYPositive = y > 0;

        List<Integer> points = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            nx = nextInt();
            ny = nextInt();

            // 만약에 x좌표가 같고, x축을 지나는 선분이 그어진다면 교차점을 등록한다. y==0이므로 x만 등록
            if (x == nx && cross(y, ny)) {
                points.add(x);
            }

            x = nx;
            y = ny;
        }

        // 마지막에 첫 점과 끝 점을 이어줌
        if (nx == initX && cross(ny, initY)) {
            points.add(nx);
        }

        List<Tower> towers = new ArrayList<>();
        // 만약 y좌표가 양수로 시작한다면 x축과 교차하는 첫 점과 가장 마지막 점이 하나의 봉우리를 이룬다.
        if (isYPositive) {
            for (int i = 2; i < points.size(); i += 2) {
                int a = points.get(i - 1);
                int b = points.get(i);
                towers.add(new Tower(Math.min(a, b), Math.max(a, b)));
            }

            int a = points.get(0);
            int b = points.get(points.size() - 1);
            towers.add(new Tower(Math.min(a, b), Math.max(a, b)));

        } else {
            // 아니라면 처음부터 인접한 두 교차점이 하나의 봉우리를 이룬다.
            for (int i = 0; i < points.size(); i += 2) {
                int a = points.get(i);
                int b = points.get(i + 1);
                towers.add(new Tower(Math.min(a, b), Math.max(a, b)));
            }
        }

        // 봉우리의 시작점 기준으로 정렬한다. (겹치는 봉우리가 없기 때문에 시작점이 같은 경우는 없다.)
        towers.sort((t1, t2) -> Integer.compare(t1.start, t2.start));

        // aRes: 다른 봉우리에 의해 포함되지 않는 봉우리 개수
        // bRes: 다른 봉우리를 포함하지 않는 봉우리 개수
        int aRes = 0, bRes = 0;

        // 겹친 봉우리를 Stack으로 관리함.
        Stack<Integer> lastEnds = new Stack<>();
        for (Tower tower : towers) {

            // 만약에 end가 tower.start보다 앞인 봉우리가 있다면 그 쪽 봉우리 세트에는
            // 다른 봉우리를 포함하지 않는 봉우리가 반드시 하나 존재한다.
            if (!lastEnds.isEmpty() && lastEnds.peek() < tower.start) {
                bRes++;
            }

            // tower와 겹치지 않는 봉우리는 stack에서 제거한다.
            while (!lastEnds.isEmpty() && lastEnds.peek() < tower.start) {
                lastEnds.pop();
            }

            // 만약에 stack이 비어있다면 tower를 포함하는 봉우리가 없다는 뜻이므로
            // tower가 다른 봉우리에 의해 포함되지 않는 봉우리가 된다.
            if (lastEnds.isEmpty()) {
                aRes++;
            }

            // tower의 end를 stack에 기록한다.
            lastEnds.add(tower.end);
        }
        // 마지막으로 stack에 넣은 봉우리는 항상 다른 봉우리를 포함하지 않는 봉우리가 된다.
        bRes++;

        System.out.printf("%d %d", aRes, bRes);
    }

    static boolean cross(int y1, int y2) {
        return y1 < 0 && y2 > 0 || y1 > 0 && y2 < 0;
    }

    static class Tower {
        int start;
        int end;

        Tower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}
