import java.io.*;
import java.util.*;

/*
11624KB, 72ms

다른 단어들에 대해 접두사가 될 가능성이 큰 단어를 선택하지 않는 것이 이득이므로
(예를들어 hello, hi, h가 있을 때 h는 최대한 선택하지 않는 쪽이 이득이므로)
문자열을 입력받고 역정렬하면 다른 원소들의 접두사 될 가능성이 낮은 원소들이 앞쪽으로 오게 된다.

해당 원소들을 차례대로 다음 단어의 접두사가 되는지 체크하고 접두사가 된다면 다음 단어는 스킵
접두사가 되지 않는다면 집합에 포함했다 취급하고 비교 기준 문자열을 변경한다.
문자열을 정렬했으므르 접두사 체크 여부는 기준 문자열과 인접한 문자열만 봐도 된다.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];

        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
        }

        //단어 배열을 일단 역정렬
        Arrays.sort(arr, Comparator.reverseOrder());
        //첫 번째 원소는 반드시 선택
        int res = 1;
        String cur = arr[0];
        for (int i = 1; i < N; i++) {
            //현재 원소가 다음 단어와 접두사 관계에 있다면 다음 단어는 생략
            if (cur.startsWith(arr[i]) || arr[i].startsWith(cur)) continue;
            //그게 아니라면 다음 단어를 집합에 포함하고 기준 문자열을 변경
            cur = arr[i];
            res++;
        }

        System.out.println(res);
    }
}
