// 12576KB 112ms

import java.io.*;
import java.util.*;

// 사각형이 겹치면 한 번에 그릴 수 있음
// -> 분리 집합
class Main {
    static final int X1 = 0;
    static final int X2 = 1;
    static final int Y1 = 2;
    static final int Y2 = 3;

    static int n;
    static int[][] squares;
    static int[] ids;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 사각형 초기화 & 입력
        n = Integer.parseInt(br.readLine());
        squares = new int[n][4];
        boolean isStartZero = false;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            squares[i][X1] = Integer.parseInt(st.nextToken());
            squares[i][Y1] = Integer.parseInt(st.nextToken());
            squares[i][X2] = Integer.parseInt(st.nextToken());
            squares[i][Y2] = Integer.parseInt(st.nextToken());

            // (0, 0)을 지나는 사각형이 있는지 확인
            if (!isStartZero && isOverlayZero(squares[i])) {
                isStartZero = true;
            }
        }

        // 분리 집합 초기화
        ids = new int[n];
        for (int i = 0; i < n; i++) {
            ids[i] = i;
        }

        // 겹치는 사각형 분류
        for (int i = 0; i < n; i++) {
            classify(i);
        }

        // 사각형 집합의 개수 카운팅
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(find(i));
        }

        System.out.println(isStartZero ? set.size() - 1 : set.size());
    }

    static void classify(int idx) {
        // idx번째 사각형이 (idx+1, n)번째 사각형과 겹치는지 확인
        for (int i = idx + 1; i < n; i++) {
            if (!isOverlay(squares[idx], squares[i])) {
                continue;
            }

            union(idx, i);
        }
    }
    
    static boolean isOverlayZero(int[] square) {
        if ((square[X1] == 0 || square[X2] == 0) && (square[Y1] <= 0 && square[Y2] >= 0)) {
            return true;
        }

        if ((square[Y1] == 0 || square[Y2] == 0) && (square[X1] <= 0 && square[X2] >= 0)) {
            return true;
        }

        return false;
    }
    
    static boolean isOverlay(int[] squareA, int[] squareB) {
        if (squareA[X1] > squareB[X2] || squareA[Y1] > squareB[Y2] || squareB[X1] > squareA[X2]
                || squareB[Y1] > squareA[Y2]) {
            return false;
        }

        // A가 B를 포함하는지
        if (squareA[X1] < squareB[X1] && squareA[X2] > squareB[X2] && squareA[Y1] < squareB[Y1]
                && squareA[Y2] > squareB[Y2]) {
            return false;
        }

        // B가 A를 포함하는지
        if (squareB[X1] < squareA[X1] && squareB[X2] > squareA[X2] && squareB[Y1] < squareA[Y1]
                && squareB[Y2] > squareA[Y2]) {
            return false;
        }

        return true;
    }
    
    static void union(int i, int j) {
        int rootI = find(i);
        int rootJ = find(j);

        ids[rootI] = rootJ;
    }

    static int find(int i) {
        if (ids[i] == i) {
            return i;
        }

        return ids[i] = find(ids[i]);
    }
}
