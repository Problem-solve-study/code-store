import java.io.*;

/*
13024KB, 100ms

문제만 읽어보면 구현, 시뮬레이션 느낌이 가득나는데 막상 문제 조건을 읽어보면 그냥 분리집합 문제임
직사각형이 (경계까지 포함하여) 겹치면 union한다. 이때 완전히 포함된 직사각형은 union하지 않는다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static Rectangles[] rectangles;
    static class Rectangles {
        int parent, num, x1, y1, x2, y2;

        public Rectangles(int parent, int num, int x1, int y1, int x2, int y2) {
            this.parent = parent;
            this.num = num;
            this.x1 = x1; this.y1 = y1;
            this.x2 = x2; this.y2 = y2;
       }

       int find() {
            if (num == parent) return num;
            return parent = rectangles[parent].find();
       }

       void union(Rectangles r) {
            rectangles[find()].parent = r.find();
       }

       boolean isIntersect(Rectangles r) {
            //       가로변이 겹치지 않거나     세로변이 겹치지 않거나        포함되지 않으면
           //두 직사각형이 만나는 것
           return !((x2 < r.x1 || r.x2 < x1) || (y2 < r.y1 || r.y2 < y1) || (contains(r) || r.contains(this)));
       }

       boolean contains(Rectangles r) {
            //가로변의 두 꼭짓점 점이 this 가로변의 두 꼭짓점 사이에 있으면서 세로변의 두 꼭짓점이 this 세로변의 두 꼭짓점 사이에 있으면 포함
           //이때 경계에 맞닿는경우는 union 해야하는 경우이므로 등호는 포함하지 않음
            return (x1 < r.x1 && r.x1 < x2) && (x1 < r.x2 && r.x2 < x2) && (y1 < r.y1 && r.y1 < y2) && (y1 < r.y2 && r.y2 < y2);
       }

       boolean isConnectedToStart() {
           return ((y1 == 0 || y2 == 0) && x1 <= 0 && 0 <= x2) || ((x1 == 0 || x2 == 0) && y1 <= 0 && 0 <= y2);
       }
    }

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        rectangles = new Rectangles[N];
        for (int i = 0; i < N; i++) {
            int x1 = nextInt(); int y1 = nextInt(); int x2 = nextInt(); int y2 = nextInt();
            rectangles[i] = new Rectangles(i, i, x1, y1, x2, y2);
            for (int j = 0; j < i; j++) {
                if (rectangles[i].isIntersect(rectangles[j])) {
                    rectangles[i].union(rectangles[j]);
                }
            }
        }

        int connetedToStart = 0;
        int ans = 0;
        for (Rectangles r : rectangles) {
            if (r.parent == r.num) ans++;
            if (r.isConnectedToStart()) connetedToStart = 1;
        }

        System.out.print(ans - connetedToStart);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
