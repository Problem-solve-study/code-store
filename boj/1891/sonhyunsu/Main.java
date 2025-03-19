//제출번호: 91622605
//메모리:   11540 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//주어진 사분면 정보를 x, y 좌표로 변환하고
//타일을 이동한 후의 x, y 좌표를 다시 사분면 정보로 변환하면
//쉽게 풀 수 있을 것이라고 생각함
//x, y를 쓰다보니 어느 게 행이고 열인지 헷갈려서 1틀
//여기서는 왼쪽 상단을 0, 0으로 잡고, 가로가 x (좌우), 세로가 y임 (상하)
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());

        int d = Integer.parseInt(st.nextToken());
        String sPos = st.nextToken();

        //입력된 사분면 조각에 대해서 조각 좌표 범위의 최댓값을 계산 
        long size = 1L << d;

        long x = 0;
        long y = 0;

        //범위의 중앙값 계산
        long dist = size >> 1;
        for (char c : sPos.toCharArray()) {
            int num = c - '0';

            //만약 3, 4사분면이라면 아래쪽에 있으므로 y에 중앙값을 더함
            if (num > 2) {
                y += dist;
            }

            //만약 1, 4사분면이라면 오른쪽에 있으므로 x에 중앙값을 더함
            if (num == 1 || num == 4) {
                x += dist;
            }

            //재귀적으로 수행
            dist >>= 1;
        }

        st = new StringTokenizer(br.readLine());
        //양수면 오른쪽, 음수면 왼쪽으로 이동하기 때문에 덧셈 연산
        x += Long.parseLong(st.nextToken());
        //양수면 위쪽, 음수면 아래쪽으로 이동하기 때문에 뺄셈 연산
        y -= Long.parseLong(st.nextToken());

        StringBuilder sb = new StringBuilder();

        //만약 이동한 좌표가 조각 좌표 범위를 넘어선 좌표면 이동이 불가능한 경우임
        if (0 <= x && x < size && 0 <= y && y < size) {
            dist = size;

            //중앙값을 계속 반씩 줄여봄
            while ((dist >>= 1) > 0) {
                boolean isLeft = x < dist; //중앙값을 기준으로 왼쪽에 있는지
                boolean isUp = y < dist; //중앙값을 기준으로 위쪽에 있는지

                //왼쪽 여부, 위쪽 여부에 따라 몇사분면에 있는지 판단 가능
                //오른쪽이나 아래쪽에 있는 경우, x와 y에 dist를 빼줘야
                //다음에 사분면을 찾을 때 수식을 일반화할 수 있음
                if (isLeft) {
                    if (isUp) {
                        sb.append(2);
                    } else {
                        sb.append(3);
                        y -= dist;
                    }
                } else {
                    if (isUp) {
                        sb.append(1);
                    } else {
                        sb.append(4);
                        y -= dist;
                    }
                    x -= dist;
                }
            }
        } else {
            sb.append(-1);
        }

        System.out.print(sb);
    }

}