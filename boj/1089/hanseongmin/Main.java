import java.io.*;
import java.util.*;

/*
11676KB, 72ms

해당 자릿수에 어떤 숫자가 올 수 있냐는 그냥 구현이라 문제 해결의 당락을 가를만한 요소는 아닌거같고
결국 이 문제의 키 포인트는 평균을 어떻게 빨리 구하냐인것 같았음
모든 경우의 수를 일일히 카운팅하면 TLE에 걸릴 것 같았고 각 숫자가 몇 번 나올 수 있는지를 이용하여
나올 수 있는 수의 합을 빠르게 구하여 풀었다.

자세한 내용은 주석으로 설명
 */

public class Main {
    static ArrayList<Integer>[] lists;
    static int N;
    static char[][] map;
    //숫자별 모양을 미리 만들어둠
    static int[][][] num = {
            {{1, 1, 1}, {1, 0, 1}, {1, 0, 1}, {1, 0, 1}, {1, 1, 1}}, //0
            {{0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}}, //1
            {{1, 1, 1}, {0, 0, 1}, {1, 1, 1}, {1, 0, 0}, {1, 1, 1}}, //2
            {{1, 1, 1}, {0, 0, 1}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}}, //3
            {{1, 0, 1}, {1, 0, 1}, {1, 1, 1}, {0, 0, 1}, {0, 0, 1}}, //4
            {{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}}, //5
            {{1, 1, 1}, {1, 0, 0}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, //6
            {{1, 1, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}, {0, 0, 1}}, //7
            {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {1, 0, 1}, {1, 1, 1}}, //8
            {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}, {0, 0, 1}, {1, 1, 1}}, //9
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[5][4 * N - 1];
        for (int i = 0; i < 5; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j);
            }
        }

        //각 자릿수에 어떤 수가 올 수 있는지를 저장
        lists = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            lists[i] = new ArrayList<>();
            findNums(i);
        }

        //평균을 구하기 위한 분모를 먼저 계산
        int n2 = 1;
        for (ArrayList<Integer> list : lists) {
            n2 *= list.size();
        }

        //만일 분모가 0이라면 자릿수 중 불가능한 경우가 있다는 것이므로 -1을 출력
        if (n2 == 0) {
            System.out.print(-1);
        } else {
            //가능한 경우라면 분자를 계산해봄
            double n1 = 0;
            //매 자릿수마다
            for (int i = 0; i < N; i++) {
                //현재 자릿수에서 가능한 수를 가져와서
                ArrayList<Integer> list = lists[i];
                //현재 자릿수가 몇 번 등장하는지를 카운팅함
                long cnt = 1;
                for (int j = 0; j < N; j++) {
                    if (j == i) continue;
                    cnt *= lists[j].size();
                }

                //이후 카운팅한 수 * 실제 수 * 자릿수에 따른 10^n을 곱하여
                //실제로 더해져야 할 수를 지속적으로 더함
                for (int n : list) {
                    n1 += cnt * n * Math.pow(10, N - i - 1);
                }
            }

            //최종적인 답은 n1 / n2가 됨.
            //상대오차는 10^-5까지 허용하므로 그냥 double로 계산한 결과를 그대로 출력하면 된다.
            System.out.print(n1 / n2);
        }
    }

    static void findNums(int n) {
        int s = 4 * n;
        int e = s + 3;

        //중요한 점은 꺼져있는 전구는 켜져있는 것으로 취급해도 된다는 것
        //따라서 불가능한 경우를 생각해보면 꺼져있어야 할 위치에 전구가 켜져있는 경우밖에 없다.
        //0 ~ 9까지 돌아보며 위의 경우에 걸리면 넘어가고 그게 아니면 가능한 것으로 취급
        outer:
        for (int i = 0; i <= 9; i++) {
            for (int r = 0; r < 5; r++) {
                for (int c = s; c < e; c++) { //꺼져있어야 할 위치에 켜져있다면 현재 수는 불가능한 것
                    if (map[r][c] == '#' && num[i][r][c - s] == 0) {
                        continue outer;
                    }
                }
            }
            lists[n].add(i);
        }
    }
}
