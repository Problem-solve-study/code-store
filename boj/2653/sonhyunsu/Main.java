//제출번호: 90319422
//메모리:   12620 KB
//실행시간: 68 ms
import java.io.*;
import java.util.*;

//2차원으로 상태 정보를 줬기 때문에 이걸 써먹어야 겠다고 생각함
//같은 집단에 있는 사람들은 상태 정보가 동일하다는 것을 발견함.
//(집단 내 사람은 좋아해야 하고[0], 집단 외 사람은 싫어해야 함[1])
//그래서 집단이 결정되지 않은 사람을 기준으로 좋아하는 사람이면서 자신과 상태 정보가 같은 사람이 있는 지 확인
//자신과 상태 정보가 다르면 안정된 집단을 만들 수 없음
//그리고 나 외에 모두 싫어하면 구성원이 1명이므로 집단을 만들 수 없음
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in), 32768);
    static StreamTokenizer st = new StreamTokenizer(br);

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        int n = nextInt();
        int d[][] = new int[n + 1][n + 1];
        boolean isStable = true;
        int groups[] = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                d[i][j] = nextInt();
            }
        }

        int group = 0;
        for (int i = 1; i <= n; i++) {
            //만약에 아직 집단이 결정되지 않았다면
            if (groups[i] == 0) {
                int member = 1;
                groups[i] = i; //나를 집단의 가장 작은 수로 생각함

                boolean isMatched = true;
                //나보다 큰 숫자를 기준으로 (작은 수는 이미 탐색했을 것이므로)
                for (int j = i + 1; j <= n; j++) {
                    //내가 좋아하면서 아직 집단이 결정되지 않은 숫자가 있다면
                    if (d[i][j] == 0 && groups[j] == 0) {

                        //나와 상태 정보가 같은 지 확인함
                        for (int k = 1; k <= n; k++) {
                            isMatched &= d[i][k] == d[j][k];
                        }

                        //만약 상태 정보가 같다면 같은 집단으로 기록함
                        if (isMatched) {
                            member++;
                            groups[j] = i;
                        }
                    }
                }

                //만약 한 번이라도 상태 정보가 다른 구성원이 있거나, 나 혼자만 집단에 속한다면
                if (!isMatched || member == 1) {
                    //안정되지 않은 집단으로 결정됨
                    isStable = false;
                    break;
                }

                //안정된 소집단이 생겼다면 소집단의 개수를 늘림
                group++;
            }
        }

        if (isStable) {
            sb.append(group).append('\n');

            //같은 소집단을 찾아봄
            for (int i = 1; i <= n; i++) {
                //만약 i가 집단의 가장 작은 사람이라면
                if (groups[i] == i) {
                    //i와 같은 집단의 사람을 추가함
                    for (int j = 1; j <= n; j++) {
                        if (groups[j] == i) {
                            sb.append(j).append(' ');
                        }
                    }
                    sb.append('\n');
                }
            }
        } else {
            //안정된 집단이 아니라면 0 출력
            sb.append(0);
        }

        System.out.print(sb);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}