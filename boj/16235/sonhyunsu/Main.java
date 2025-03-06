
//제출번호: 90842262
//메모리:   19616 KB
//실행시간: 516 ms
import java.io.*;
import java.util.*;

//구현, 시뮬레이션 문제, 문제 조건에 맞게 차례대로 구현하면 된다.
//이 때 같은 위치에 있는 나무를 잘 관리하는 게 핵심 (덱 사용)
//덱의 원소를 항상 내림차순으로 만들어야 한다.
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static List<List<Deque<Integer>>> map = new ArrayList<>();
    static int[] dx = { 1, 0, -1, -1, -1, 0, 1, 1 }, dy = { 1, 1, 1, 0, -1, -1, -1, 0 };

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();
        int[][] a = new int[n][n];
        int[][] remains = new int[n][n];

        for (int i = 0; i < n; i++) {
            map.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                map.get(i).add(new ArrayDeque<>());
                remains[i][j] = 5; // 초기 비료는 5
                a[i][j] = nextInt();
            }
        }

        for (int i = 0; i < m; i++) {
            int x = nextInt();
            int y = nextInt();
            int age = nextInt();
            map.get(x - 1).get(y - 1).addLast(age); // 초기 나무를 심음
        }

        for (int year = 0; year < k; year++) {
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    Deque<Integer> trees = map.get(x).get(y);
                    int size = trees.size(); // 원래 위치에 있던 나무의 개수를 세어둠

                    // 봄
                    int adder = 0;
                    while (size-- > 0) {
                        // 덱은 내림차순으로 정렬되어 있기 때문에
                        // 가장 끝부터 뽑아서 비료를 먹여본다.
                        int age = trees.pollLast();

                        // 비료를 줄 수 있으면 준 다음 덱의 처음에 넣는다.
                        if (remains[x][y] >= age) {
                            remains[x][y] -= age;
                            trees.addFirst(age + 1);
                        } else {
                            // 비료를 줄 수 없으면 나무는 죽는다.
                            adder += age / 2;
                        }

                        // 끝에서 빼고 처음에 넣었기 때문에 내림차순 정렬이 유지됨
                    }

                    // 여름
                    remains[x][y] += adder;
                }
            }

            // 가을
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    Deque<Integer> trees = map.get(x).get(y);
                    int size = trees.size();

                    while (size-- > 0) {
                        int age = trees.pollFirst();

                        // 나무를 퍼트릴 수 있으면 퍼트린다.
                        if (age % 5 == 0) {
                            for (int i = 0; i < 8; i++) {
                                int nx = x + dx[i];
                                int ny = y + dy[i];

                                if (0 <= nx && nx < n && 0 <= ny && ny < n) {
                                    // 덱의 끝에 넣에서 내림차순 정렬을 유지한다.
                                    // 원래 있던 나무들은 봄에서 나이가 1 증가했기 때문에
                                    // 내림차순이라고 말할 수 있다.
                                    map.get(nx).get(ny).addLast(1);
                                }
                            }
                        }

                        trees.addLast(age);
                    }
                }
            }

            // 겨울
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < n; y++) {
                    remains[x][y] += a[x][y];
                }
            }
        }

        // 살아 있는 나무의 개수를 센다.
        int res = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                res += map.get(x).get(y).size();
            }
        }

        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}