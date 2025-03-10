import java.io.*;
import java.util.*;

/*
19980KB, 196ms

처음에 그리디로 오해하기 딱 좋다. 조심
그리디가 아니라는 걸 확인했다면 DP라는 것은 상대적으로 수월하게 판별할 수 있다.
DP 테이블을 생각해내는 것부터가 어려운데 여러 방법이 있을 수 있겠지만
특정 점부터 또 다른 점으로 선을 잇어야하므로 구간 DP라는 것은 동일할 듯하다.

[최솟값 구하기]
DP[i][j][k]: i ~ j 구간 내의 정보를 저장한다. k가 0이면 높이를, k가 1이면 구간 내의 최소 길이를 저장한다.
i ~ j 구간이 홀수거나 짝수더라도 흰색 점과 검은 색 점의 짝이 맞지 않다면 해당 구간은 불가능한 구간이므로 생략한다.

가능한 구간이라면 [i + 1 ~ r) 범위 내의 모든 subR에 대하여 dp[i][subR], dp[subR + 1][r]을 확인한다.
만일 나눈 두 서브 구간이 모두 가능한 구간이라면 dp[i][j]의 값을 두 서브 구간 dp의 값의 합으로 갱신한다.
높이의 경우 둘 중 더 높은 높이를 저장한다.

만일 i와 j의 짝이 맞는 경우(즉, 하나는 검은점 다른 하나는 흰점일 경우)라면 i와 j를 이어주는 경우를 추가로 고려해야한다.
이때는 dp[i + 1][j - 1] (i, j의 바로 안쪽 구간)의 경우를 채택함과 동시에 i와 j를 이어줘야하므로 i + 1 ~ j - 1 구간의
높이를 가져온 후 높이를 하나 높여 길이를 계산한다.

위를 재귀적으로 구하면 전체 구간에서의 최솟값을 계산할 수 있다.

[역추적]
최솟값 구하기까지만 존재했으면 P5 정도됐을 것 같다. 역추적이 조금 어려웠다.
DP 값을 갱신 중일 때는 최선의 선택이 확정된 상태가 아니므로 바로 역추적은 불가능하고 DP 테이블 갱신이 끝난 후
추가 재귀로 역추적을 할 필요가 있다.

v[i]: i번째 점이 선택되었음을 나타내는 dp 배열 하나를 추가로 선언한다.
각 점은 반드시 한 번만 선택되므로 v[l] || v[r]라면 더 이상 탐색을 수행하지 않도록 한다.
이 시점에서 DP 테이블에는 최선의 값만 들어가 있을테니 원래 로직을 거의 동일하게 수행하되,
만일 구한 값이 DP에 들어가 있는 값과 동일하다면 최선의 선택이라는 소리이므로 TreeSet에 경로를 저장한다.
이를 재귀적으로 반복한다.

위 과정을 수행하면 재귀적으로 테이블이 갱신될 때만 set에 들어가게 되어 경로 역추적이 가능해진다.
 */

public class Main {
    static int N;
    static String points;
    static boolean[] v;
    static int[][][] dp;
    static TreeSet<int[]> set = new TreeSet<>((arr1, arr2) -> {
        int comp1 = Integer.compare(arr1[0], arr2[0]);
        if (comp1 != 0) return comp1;
        return Integer.compare(arr1[1], arr2[1]);
    });

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = br.readLine();

        dp = new int[N][N][2];
        v = new boolean[N];
        StringBuilder sb = new StringBuilder();
        //먼저 구간 DP로 최솟값을 구한다.
        sb.append(getAns(0, N - 1)).append('\n');
        //이후 경로 역추적
        pathExtract(0, N - 1);
        for (int[] cur : set) {
            sb.append(cur[0] + 1).append(' ').append(cur[1] + 1).append('\n');
        }
        System.out.println(sb);
    }

    static int getAns(int l, int r) {
        //범위를 벗어나면 바로 빠져나가기
        if (l < 0 || r > N) return 0;
        //DP 값이 존재한다면 해당 값을 활용
        if (dp[l][r][1] != 0) return dp[l][r][1];
        //해당 구간에서 검은점과 흰색점이 짝이 맞지 않으면 바로 빠져나가기
        if (!check(l, r)) return 0;
        //마찬가지로 짝수라면 바로 빠져나간다. 지금보니 이 조건문은 필요가 없다. (위 조건문으로 어차피 빠져나간다)
        if ((r - l + 1) % 2 != 0) return dp[l][r][1] = 0;
        //기저 조건, 높이를 1로 설정하고 점의 차이가 1일 때 거리를 계산한다.
        if (r - l == 1) {
            if (points.charAt(l) != points.charAt(r)) {
                dp[l][r][0] = 1;
                dp[l][r][1] = dp[l][r][0] * 2 + r - l;
                return dp[l][r][1];
            }

            return dp[l][r][1] = 0;
        }

        //이제 가능한 모든 서브구간의 조합을 확인한다.
        int min = 0;
        int height = 0;
        for (int subR = l + 1; subR < r; subR++) {
            //왼쪽 서브구간을 구하고
            int leftSeg = getAns(l, subR);
            //만일 왼쪽 서브구간이 불가능한 구간이라면 넘어간다.
            if (leftSeg == 0) continue;
            //오른쪽 서브구간을 구하고
            int rightSeg = getAns(subR + 1, r);
            //만일 오른쪽 서브 구간이 불가능한 구간이라면 넘어간다.
            if (rightSeg == 0) continue;

            //이 조건까지 왔다면 해당 구간은 가능한 구간이므로 갱신 가능하다면 갱신한다.
            if (min == 0 || min > leftSeg + rightSeg) {
                //l, r은 연결하지 않는 경우이므로 이 경우 값은 서브 구간의 합
                min = leftSeg + rightSeg;
                //높이는 둘의 max가 된다.
                height = Math.max(dp[l][subR][0], dp[subR + 1][r][0]);
            }
        }

        //만일 l과 r의 짝이 맞을 경우라면 l과 r을 이어주는 경우도 추가로 고려한다.
        if(points.charAt(l) != points.charAt(r)) {
            //바로 직전 내부 구간의 값을 가져오고
            int subSeg = getAns(l + 1, r - 1);
            //직전 내부 구간의 높이에서 1을 높이고 길을 만들어본다
            int newValue = subSeg + (dp[l + 1][r - 1][0] + 1) * 2 + r - l;
            //갱신이 가능하다면 갱신한다.
            if (min == 0 || min > newValue) {
                min = newValue;
                height = dp[l + 1][r - 1][0] + 1;
            }
        }

        //이제 l, r 구간의 최솟값을 구했다. 높이와 값을 할당한다.
        dp[l][r][0] = height;
        return dp[l][r][1] = min;
    }

    //DP를 갱신할 때 set에 넣는다는 것 외에는 다를 것이 없다.
    //이 시점은 이미 DP에 최선의 값이 들어가 있으므로 현재 선택한 값이 dp에 들어가 있는 값과 동일한지를 판단한다.
    //동일하다면 최선의 선택이라는 소리이므로 재귀적으로 경로를 추가해준다.
    static void pathExtract(int l, int r) {
        if (v[l] || v[r]) return;

        if (r - l == 1) {
            set.add(new int[] {l, r});
            v[l] = v[r] = true;
            return;
        }

        if(points.charAt(l) != points.charAt(r)) {
            int subSeg = getAns(l + 1, r - 1);
            int newValue = subSeg + (dp[l + 1][r - 1][0] + 1) * 2 + r - l;
            if (newValue == dp[l][r][1]) {
                set.add(new int[] {l, r});
                pathExtract(l + 1, r - 1);
                v[l] = v[r] = true;
                v[l + 1] = v[r - 1] = true;
                return;
            }
        }

        for (int subR = l + 1; subR < r; subR++) {
            int leftSeg = getAns(l, subR);
            if (leftSeg == 0) continue;
            int rightSeg = getAns(subR + 1, r);
            if (rightSeg == 0) continue;

            if (leftSeg + rightSeg == dp[l][r][1]) {
                pathExtract(l, subR);
                pathExtract(subR + 1, r);
                v[l] = v[subR] = true;
                v[subR + 1] = v[r] = true;
            }
        }
    }

    static boolean check(int l, int r) {
        //검은색, 흰색의 짝이 맞는 경우 true
        int b = 0;
        int w = 0;
        for (int i = l; i <= r; i++) {
            if (points.charAt(i) == '1') {
                b++;
            } else {
                w++;
            }
        }

        return b == w;
    }
}