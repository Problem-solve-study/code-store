import java.io.*;
import java.util.*;

/*
40756KB, 696ms

[서브태스크 1 풀이]
기프티콘은 B가 낮은 순서대로 사용될 것은 문제의 조건에 의해 너무나도 자명하다.
따라서 B를 기준으로 하여 오름차순 정렬한다.
현재보고 있는 기프티콘의 A값을 B보다 크거나 같도록 만들어야 사용할 수 있으므로
적절히 값을 조절한 후 해당 기프티콘을 사용했다고 취급한다.
이때 기프티콘은 기한이 가장 적게 남아야 사용 가능하므로 자신의 뒤에 존재하는 원소들은 모두 자신의 A값보다 커야한다.
따라서 자신의 뒤에 있는 모든 원소들의 값을 현재 A값 이상으로 조절해준다.
이를 모든 원소들에 대해 수행해주면 답을 구할 수 있으며 시간복잡도는 O(N^2)이다.

[만점 풀이]
현재 원소를 볼 때마다 뒤의 원소를 일일히 갱신할 필요가 없다.
prevA 값과 현재 원소의 A 값을 비교하는 것만으로 현재 A를 어떻게 조절할지 결정할 수 있다.
이때 B의 값이 같은 경우가 문제가 되는데, B의 값이 같은 경우를 잘 생각해보면 prevA는 직전 원소의 A가 아닌
동일한 B 값을 가지는 구간의 최대 A 값이 되어야 한다는 것을 알 수 있다.
직전 B 구간의 최대 A 값이 되어야지 "기프티콘은 기한이 가장 적게 남아야 사용 가능" 이라는 조건을 만족시킬 수 있기 때문이다.
따라서 이러한 prevA를 적절히 관리해주며 카운팅해주면 O(N)에 풀 수 있다.
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    public static void main(String[] args) throws Exception {
        int N = nextInt();
        long[][] arr = new long[N][2];
        for (int i = 0; i < N; i++) {
            arr[i][0] = nextInt();
        }
        for (int i = 0; i < N; i++) {
            arr[i][1] = nextInt();
        }

        //직전 B 구간에서의 최대 prevA 값을 저장
        //만일 map[10] = 50이라면 B가 10일 때 prevA 값은 50이라는 뜻
        TreeMap<Long, Long> map = new TreeMap<>();
        //사용 계획이 빠른 기프티콘이 빠르게 사용되어야 할 것이니 B를 기준으로 정렬
        Arrays.sort(arr, Comparator.comparingLong((long[] a) -> a[1]).thenComparingLong(a -> a[0]));
        long res = 0;
        long prevA;     //현재 원소가 취급해야할 prevA값
        long max = 0;   //B 구간 내의 최대 A 값을 관리
        for (int i = 0; i < N; i++) {
            long A = arr[i][0];
            long B = arr[i][1];
            //만일 B 구간이 바뀌었다면 현재 B 구간에서 봐야할 prevA 값을 저장하고 max는 초기화
            if (!map.containsKey(B)) {
                map.put(B, max);
                max = 0;
            }
            //현재 원소가 봐야할 prevA를 가져옴
            prevA = map.get(B);

            //현재 원소의 A가 prev의 A보다 작다면 prev의 A를 현재 원소보다 먼저 사용했으므로
            //자신은 반드시 prev의 A보다 더 큰 값이 되어야 한다. 이를 계산
            if (prevA > A) {
                long d = (long) Math.ceil((double) (prevA - A) / 30);
                A += 30 * d;
                res += d;
            }

            //그 이후 현재 원소의 사용 계획보다 A가 작다면 현재 기프티콘을 사용할 수 있는 날짜까지 갱신한다.
            if (A < B) {
                long d = (long) Math.ceil((double) (B - A) / 30);
                A += 30 * d;
                res += d;
            }

            //현재 B 구간의 최댓값 갱신을 시도
            max = Math.max(max, A);
        }
        System.out.println(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}