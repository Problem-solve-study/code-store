// 35476KB 2628ms

import java.io.*;
import java.util.StringTokenizer;

// 최소 질투심에 대한 이진탐색으로 lower bound 구하기
class Main {
    static int n;
    static int[] beads;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        beads = new int[m];
        for (int i = 0; i < m; i++) {
            beads[i] = Integer.parseInt(br.readLine());
        }

        long max = search(1, 1_000_000_000);

        System.out.println(max);
    }
    
    // lower bound 탐색
    static long search(long left, long right) {
        if (left >= right) {
            return left;
        }

        long mid = (left + right) / 2;

        if (checkBound(mid)) {
            return search(left, mid);
        }
        return search(mid + 1, right);
    }

    // 구슬을 최대 maxBead로 나눌 때 학생이 부족하지 않은지 확인
    static boolean checkBound(long maxBead) {
        int count = 0;
        for (int i = 0; i < beads.length; i++) {
            int thisBeads = beads[i];

            while (thisBeads > maxBead) {
                thisBeads -= maxBead;
                count++;
            }
            count++;
        }

        if (count <= n) {
            return true;
        }
        return false;
    }
}
