// 54112 KB, 632 ms
/*
 * 맵을 다 만들수 없음. 좌표 범위가 1e9 까지이기 때문에.
 * 맵으로 만들어야 할듯.
 * 
 * 개구리의 점프는 4가지
 * A. (x+P, y+P)
 * B. (x+P, y-P)
 * C. (x-P, y+P)
 * D. (x-P, y-P)
 * 
 * 있으면 점프하고, 없으면 가만히 있음.
 * 점프하고 나면 원래 있던 식물은 가라앉음.
 * 좌표가 100,000개기 때문에, 정렬된 상태로 구성해야 할듯. 가까운 지점으로 바로 갈 수 있게.
 * 
 * 맵에, 합과 차로 트리셋으로 구현함.
 */

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

public class Main {
    // B, C는 slash, X+Y가 동일, A, D는 bSlash, X-Y가 동일
    static HashMap<Integer, TreeSet<Pos>> slash = new HashMap<>();
    static HashMap<Integer, TreeSet<Pos>> bSlash = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int N = readInt(), K = readInt();
        System.in.read(); // 윈도우는 CRLF
        int[] jump = new int[K];
        for (int i = 0; i < K; i++)
            jump[i] = System.in.read();

        Pos[] positions = new Pos[N];
        for (int i = 0; i < N; i++)
            positions[i] = new Pos(readInt(), readInt());
        Pos cur = positions[0];
        Arrays.sort(positions);
        for (int i = 0; i < N; i++)
            putMap(positions[i]);

        for (int i = 0; i < K; i++) {
            TreeSet<Pos> tempA = slash.get(cur.x + cur.y);
            TreeSet<Pos> tempB = bSlash.get(cur.x - cur.y);

            // 점프할 다음 위치 찾기
            Pos next = null;
            if (jump[i] == 'A' && (next = tempB.higher(cur)) == null)
                continue;
            else if (jump[i] == 'B' && (next = tempA.higher(cur)) == null)
                continue;
            else if (jump[i] == 'C' && (next = tempA.lower(cur)) == null)
                continue;
            else if (jump[i] == 'D' && (next = tempB.lower(cur)) == null)
                continue;

            tempA.remove(cur);
            tempB.remove(cur);
            cur = next;
        }

        System.out.println(cur.x + " " + cur.y);

    }

    static void putMap(Pos p) {
        int X = p.x, Y = p.y;
        if (slash.containsKey(X + Y))
            slash.get(X + Y).add(p);
        else {
            TreeSet<Pos> temp = new TreeSet<>();
            temp.add(p);
            slash.put(X + Y, temp);
        }

        if (bSlash.containsKey(X - Y))
            bSlash.get(X - Y).add(p);
        else {
            TreeSet<Pos> temp = new TreeSet<>();
            temp.add(p);
            bSlash.put(X - Y, temp);
        }

    }

    static class Pos implements Comparable<Pos> {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Main.Pos o) {
            return x != o.x ? x - o.x : y - o.y;
        }

    }

    static int readInt() throws IOException {
        int c;
        while ((c = System.in.read()) <= 32)
            ;
        int n = c & 15;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}