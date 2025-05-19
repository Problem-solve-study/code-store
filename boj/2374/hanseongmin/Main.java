import java.io.*;
import java.math.BigInteger;

/*
12988KB, 80ms

분할 정복으로 풀이.
적당히 나올 수 있는 수열들의 그래프를 머릿속으로 생각해보고
그 상태에서 아래 알고리즘을 적용시켰을 때의 반례가 딱히 안떠올라서 쭉 구현하고 냈는데 맞았음.

1. 현재 구간에서 Max값을 찾는다.
2. Max 기준 왼쪽 구간을 현재 Max값과 동일한 높이로 맞춘다.
3. Max 기준 오른쪽 구간을 현재 Max값과 동일한 높이로 맞춘다.
4. 2번과 3번에서 수행한 Add 연산을 횟수를 더한다.

이러한 결국 주어진 구간에서 작은 구간들이 점차 메워지고 결국 Max와 같은 높이가 되는 식으로 작동하게 될 것임

풀고보니 태그에 분할정복은 없고 스택만 있길래 당황함.
답이 long을 넘어가는거 같아서 BigInteger 사용
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        N = nextInt();
        int max = 0;
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
            max = Math.max(max, arr[i]);
        }

        //[0 ~ N - 1] 구간에서의 답을 출력
        System.out.print(findValue(0, N - 1, max));
    }

    static BigInteger findValue(int s, int e, int prevMax) {
        //s, e의 관계가 어긋나면 그냥 0 반환(답에 영향을 주지 않기 위해서)
        if (e < s) return BigInteger.ZERO;
        //하나의 원소만 보고 있다면 그냥 이전 Max값과의 차이만큼만 add하면 됨
        //지금보니까 abs들은 적용시킬 필요가 없었을듯(이미 대소 관계가 확실하니)
        if (s == e) {
            return BigInteger.valueOf(Math.abs(prevMax - arr[s]));
        } else if (e - s == 1) {
            //두 개의 구간을 보고 있다면 일단 두 개 중 더 큰 값을 뽑고
            int max = Math.max(arr[e], arr[s]);
            //큰 값과 동일해지도록 add 해준뒤
            BigInteger bi = BigInteger.valueOf(Math.abs(arr[e] - arr[s]));
            //이전 max값과 동일해지도록 add 함
            return bi.add(BigInteger.valueOf(Math.abs(max - prevMax)));
        }

        //현재 구간 내에서 max를 찾고
        int max = 0;
        int maxIdx = 0;
        for (int i = s; i <= e; i++) {
            if (max < arr[i]) {
                max = arr[i];
                maxIdx = i;
            }
        }

        BigInteger bi = BigInteger.ZERO;
        //왼쪽 구간을 max와 동일하게 만들기 위한 add 횟수와
        bi = bi.add(findValue(s, maxIdx - 1, max));
        //오른쪽 구간을 max와 동일하게 만들기 위한 add 횟수를 더한다.
        bi = bi.add(findValue(maxIdx + 1, e, max));
        //그리고 현재 구간 전체를 이전 max 값과 동일하게 만들기 위해 add해주면 현재 구간에서의 답이 됨
        return bi.add(BigInteger.valueOf(Math.abs(prevMax - max)));
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
