import java.io.*;

/*
20432KB, 408ms

문제 읽자마자 매개변수 탐색이라고 생각함
최대 생명력에 따라 마지막 방까지 갈 수 있는 경우를 시뮬레이션을 통해 O(N)에 가능하므로
maxHP를 매개변수로 두고 돌리면 될거라고 생각했다.
기여보니 그리디로도 된다는 것 같음.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int[][] rooms;

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        long initAtk = nextInt();
        rooms = new int[N][3];
        for (int i = 0; i < N; i++) {
            rooms[i][0] = nextInt();
            rooms[i][1] = nextInt();
            rooms[i][2] = nextInt();
        }

        long s = 0;
        long e = Long.MAX_VALUE;
        long mid = 0;
        while (s <= e) {
            //max를 Long.MAX로 잡았으므로 오버플로우 방지를 위해 mid를 s + (e - s) / 2로 계산
            mid = s + (e - s) / 2;
            if (simulation(mid, initAtk)) {
                e = mid - 1;
            } else {
                s = mid + 1;
            }
        }
        System.out.print(s);
    }

    static boolean simulation(long maxHp, long atk) {
        long curHp = maxHp;

        for (int[] room : rooms) {
            int t = room[0];
            int a = room[1];
            int h = room[2];

            if (t == 1) { //몬스터가 있는 경우
                //이 부분을 단순 반복으로 돌리면 100% 시초날거라고 생각해서 계산하여 바로 구했음
                //플레이어가 몬스터를 잡을 턴 수
                int winTurn = (int) Math.ceil((double) h / atk);
                //몬스터가 플레이어를 잡을 턴 수
                int loseTurn = (int) Math.ceil((double) curHp / a);
                //만일 몬스터가 플레이어를 더 빨리 잡는 경우에는 불가능한 경우임
                //플레이어가 선공하므로 loseTurn과 winTurn이 같다면 가능한 경우다.
                if (loseTurn < winTurn) {
                    return false;
                }

                //플레이어가 이기는 경우라면 몬스터는 플레어어가 공격한  횟수 - 1만큼 공격함
                curHp -= ((long) a * (winTurn - 1));
            } else { //포션이 있는 경우
                curHp = Math.min(maxHp, curHp + h);
                atk += a;
            }
        }

        return true;
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
