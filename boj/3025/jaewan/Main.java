// 66688 KB, 628 ms
/*
 * R * C 보드에서 게임
 * 비어있거나 벽으로 막혀있음.
 * 가장 윗 행의 그 열에 돌을 놓고 돌은 중력에 의해 떨어짐.
 * 
 * 1. 돌의 아랫칸이 벽으로 막혀있거나 가장 아랫줄이면 멈춤
 * 2. 아랫칸이 비어있으면 아랫칸으로 이동
 * 3. 아랫칸에 돌이 있으면 미끄러짐.
 * 3-1. 왼쪽 칸과 왼쪽-아래 칸이 비어있으면, 왼쪽으로 미끄러짐.
 * 3-2. 왼쪽으로 미끄러지지 않았고, 오른쪽 칸과 오른쪽-아래 칸이 비어있으면, 오른쪽으로 미끄러짐.
 * 3-3. 두 경우가 아니면 멈춤.
 * 
 * 돌의 이동이 멈춘 이후에 다른 돌을 던짐.
 * 초기 상태와 돌을 놓은 열이 번호가 주어질 때 모든 돌을 던진 이후의 상태를 구하는 프로그램.
 * 
 * 보드의 크기 R <= 30,000, C <= 30
 * 
 * 돌을 던진 횟수 N <= 100,000
 * 
 * 시뮬레이션을 전부 돌리면? 100,000 x 최대 미끄러짐(30,000) = 3,000,000,000 그냥 시뮬레이션이 아님.
 * 세로가 30,000 이므로 내려가는 걸 줄여야 함.
 * 빈 칸은 스킵하고, 돌과 벽이 있는 곳만.
 * 
 * 옆 칸으로 미끄러졌을 떄, 해당 열의 행 위치보다 아래에 있는 것 중 최댓값. 
 * TreeSet를 열 개수만큼 만들고, 아래부터 장애물(벽, 돌) 쌓아야겠다.
 * 
 * TreeSet의 ceil로 탐색, log R
 * 전체 시간복잡도는 O(N x log R) = 100,000 x 15 ? 정도 될듯
 * 왜 시간초과..?
 * 
 * TreeSet으로 찾는 것 자체는 log R 이 맞지만, 만약 해당 열에 계속 쌓는다면?
 * 미끄러지는 횟수만큼 연산을 반복하게 되네.
 * 그래서 시간초과가 난 것 같다.
 * 
 * 현수랑 대환이 코드 보니까 경로를 스택으로 저장했네.
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.TreeSet;

public class Main {
    static final int WALL = -1;
    static final int EMPTY = 0;
    static final int ROCK = 1;

    static int R, C;
    static int[][] map;

    static ArrayList<TreeSet<Obstacle>> obstacles = new ArrayList<>();
    static ArrayList<ArrayDeque<int[]>> stacks = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        R = Integer.parseInt(input[0]);
        C = Integer.parseInt(input[1]);
        map = new int[R + 1][C];

        for (int i = 0; i < C; i++) {
            obstacles.add(new TreeSet<>());
            obstacles.get(i).add(new Obstacle(R, WALL)); // 바닥 벽 추가
            stacks.add(new ArrayDeque<>());
            map[R][i] = WALL;
        }

        for (int i = 0; i < R; i++) {
            String inStr = br.readLine();
            for (int j = 0; j < C; j++) {
                char t = inStr.charAt(j);
                switch (t) {
                    case 'X':
                        obstacles.get(j).add(new Obstacle(i, WALL));
                        map[i][j] = WALL;
                        break;
                }
            }
        }

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int c = Integer.parseInt(br.readLine());
            throwRock(c);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                switch (map[i][j]) {
                    case WALL:
                        sb.append('X');
                        break;
                    case ROCK:
                        sb.append('O');
                        break;
                    default:
                        sb.append('.');
                        break;
                }
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    // c열에 돌을 던지자
    static void throwRock(int col) {
        int x = col - 1;
        int y = 0;
        ArrayDeque<int[]> stack = stacks.get(x);

        // 유효한 경로 확인
        while (!stack.isEmpty()) {
            int[] pos = stack.peek();
            if (map[pos[0]][pos[1]] != 0) {
                stack.poll();
            } else {
                break;
            }
        }

        if (!stack.isEmpty()) {
            int[] pos = stack.peek();
            y = pos[0];
            x = pos[1];
        }

        while (true) {
            // 현재 열에서 장애물 찾기
            Obstacle ceil = obstacles.get(x).ceiling(new Obstacle(y, 0));
            y = ceil.y - 1;

            // 벽이면 멈춤
            if (ceil.type == WALL)
                break;

            // 미끄러짐 확인
            boolean slide = false;
            // 왼쪽 확인
            if (canSlide(x - 1, y)) {
                x--;
                y++;
                stack.push(new int[] { y, x });
                slide = true;
            }
            // 오른쪽 확인
            else if (canSlide(x + 1, y)) {
                x++;
                y++;
                stack.push(new int[] { y, x });
                slide = true;
            }

            if (!slide)
                break;
        }

        // 돌 배치
        obstacles.get(x).add(new Obstacle(y, ROCK));
        map[y][x] = ROCK;
    }

    // 해당 열에서 y와 y+1 위치가 비어있는지 확인
    static boolean canSlide(int targetX, int y) {
        if (targetX < 0 || targetX >= C)
            return false;
        return map[y][targetX] == EMPTY && map[y + 1][targetX] == EMPTY;
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

    static class Obstacle implements Comparable<Obstacle> {
        int y, type;

        public Obstacle(int y, int type) {
            this.y = y;
            this.type = type;
        }

        @Override
        public int compareTo(Main.Obstacle o) {
            return y - o.y;
        }

    }
}