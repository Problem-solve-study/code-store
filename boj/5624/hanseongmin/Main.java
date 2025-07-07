import java.io.*;
import java.util.*;

/*
186612KB, 480ms

굳이 세 개의 수를 저장할 필요 없이 2개의 수만 저장해두고 되는지를 판단하면 됨
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        //이 set에는 두 수의 합을 저장
        HashSet<Integer> set = new HashSet<>();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < i; j++) {
                //set 내부 원소 + arr[j] == arr[i]일 경우에 arr[i]가 좋은 수가 된다.
                //따라서 set 내부 원소 == arr[i] - arr[j]일 경우에 가능한 것이므로
                //set의 contains를 사용하여 해당 수가 존재하는지 판별
                if (set.contains(arr[i] - arr[j])) {
                    cnt++;
                    break;
                }
            }

            //arr[i]까지 다 보았으므로 arr[i]와 이전 값들의 합을 set에 저장
            for (int j = 0; j <= i; j++) {
                set.add(arr[i] + arr[j]);
            }
        }

        System.out.print(cnt);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
