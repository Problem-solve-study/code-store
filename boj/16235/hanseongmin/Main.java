import java.io.*;
import java.util.*;

/*
302432KB, 1156ms

구현 + 시뮬레이션 문제. 보통 구현, 시뮬 문제는 최적화 없이 통과하는 경우가 많아서
최적화 생각없이 구현했다가 한 번 TLE 맞음.

ArrayList를 우선순위 큐로 바꿔서 해결
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N, M, K;
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[][] map;
    static int[][] A;
    static PriorityQueue<Tree> trees = new PriorityQueue<>();

    static class Tree implements Comparable<Tree> {
        int r;
        int c;
        int age;

        Tree(int r, int c, int a) {
            this.r = r;
            this.c = c;
            age = a;
        }

        //나이 어린 나무부터 양분을 먹기 위한 정렬 기준 설정
        @Override
        public int compareTo(Tree o) {
            return Integer.compare(age, o.age);
        }
    }

    public static void main(String[] args) throws Exception {
        //맵의 크기
        N = nextInt();
        //나무 정보 개수
        M = nextInt();
        //구해야할 년수
        K = nextInt();
        map = new int[N + 1][N + 1];
        A = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                map[i][j] = 5;
            }
        }

        //A배열 입력(로봇이 돌아다니면서 추가하는 양분의 양)
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                A[i][j] = nextInt();
            }
        }

        //심은 나무 입력
        for (int i = 0; i < M; i++) {
            int x = nextInt();
            int y = nextInt();
            int z = nextInt();
            trees.add(new Tree(x, y, z));
        }

        while (K --> 0) {
            //봄, 자신의 나이만큼 양분을 먹고 나이가 1 증가
            //양분이 부족한 경우 즉시 사망
            ArrayList<Tree> aliveTrees = new ArrayList<>();
            ArrayList<Tree> deadTrees = new ArrayList<>();
            while (!trees.isEmpty()) {
                Tree cur = trees.remove();
                if (map[cur.r][cur.c] >= cur.age) {
                    map[cur.r][cur.c] -= cur.age;
                    cur.age++;
                    aliveTrees.add(cur);
                } else {
                    deadTrees.add(cur);
                }
            }
            trees.addAll(aliveTrees);

            //여름. 죽은 나무가 양분으로 변하게 됨
            for (Tree t : deadTrees) {
                map[t.r][t.c] += (t.age / 2);
            }

            //가을. 나무가 번식
            for (Tree t : aliveTrees) {
                //나무의 나이가 5의 배수라면 번식
                if (t.age % 5 == 0) {
                    for (int i = 0; i < dr.length; i++) {
                        int nr = t.r + dr[i];
                        int nc = t.c + dc[i];
                        if (boundaryCheck(nr, nc)) {
                            trees.add(new Tree(nr, nc, 1));
                        }
                    }
                }
            }

            //겨울. 로봇이 돌아다니며 땅에 양분을 추가
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] += A[i][j];
                }
            }
        }

        System.out.println(trees.size());
    }

    static boolean boundaryCheck(int r, int c) {
        return (1 <= r && r <= N) && (1 <= c && c <= N);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}