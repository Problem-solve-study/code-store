/**
 * 50496KB	304ms
 * 
 * [사고 흐름]
 * N이 10^5에 1초? N 혹은 NlogN 풀이가 필요함.
 * 특정 차이값(d)으로 등차 수열로 만드는 것이 가능한지 판단하는데는 N이 소요된다는 점에 주목함.
 * d를 찾는 범위를 줄이는 것이 이 문제의 조건으로 생각하고 풀이를 시작하였음.
 * 0번값과 1번값 차이의 +-2 범위를 벗어나는 d값은 불가능하다고 생각함.
 * 시간복잡도가 5N이기에 풀이를 시도함.
 * 
 * 최초시도 - 연속한 두 수의 최소차와 최대차를 범위로 시도 => 당연히 시간초과
 * 2차시도 - 0번값과 1번값 차이의 +-2차이를 범위로 시도 => 통과
 * 
 * 
 * [알고리즘 설명]
 * 0번값과 1번값 차이의 +-2차이를 범위로 d값을 구한다.
 * d값을 대상으로 연속한 값들의 차이가 d와 동일한지 확인한다.
 * d와 동일하다면 다음 값으로 넘어가고, 
 * d와 동일하지 않고 abs(d-(last-cur))이 1이하인 경우, cur에 연산을 적용시키키고 last 에는 cur대신에 last + d를 할당한다.
 * 위 과정을 끝까지 반복하여 연산 적용횟수를 구한다.
 * d범위를 모두 탐색한 후 최소 연산 적용횟수를 출력한다.
 */

import java.util.*;
import java.io.*;

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int res = -1;
        if (N == 1) {
            res = 0;
        }
        else {
            int[] a = new int[N];
            a[0] = nextInt();
            for (int i = 1; i < N; i++) a[i] = nextInt();
            int min = a[1]-a[0]-2;
            int max = a[1]-a[0]+2;

            int minCnt = Integer.MAX_VALUE;
            for (int d=min; d<=max; d++) {
                for (int x=-1; x<=1; x++) {
                    int last = a[0]+x;
                    int cnt = x==0? 0:1;
                    for (int i = 1; i < N; i++) {
                        if (a[i] != last+d) {
                            if (Math.abs(a[i]-(last+d)) > 1) {
                                cnt = Integer.MAX_VALUE;
                                break;
                            }
                            cnt++;
                        }
                        last = last+d;
                    }
                    minCnt = Math.min(minCnt, cnt);
                }
            }
            if (minCnt != Integer.MAX_VALUE) res = minCnt;
        }
        System.out.println(res);
    }

    public static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}