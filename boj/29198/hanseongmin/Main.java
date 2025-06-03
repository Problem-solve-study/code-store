import java.io.*;
import java.util.*;

/*
19052KB, 232ms

어차피 문자들을 재정렬할 것이므로 처음부터 정렬시킨 후 합쳐도 무방
문자들을 입력받고 정렬시킨 뒤 K만큼 택한 후 택한 문자들을 다시 정렬하여 출력
이때 문자를 입력받는 동시에 해당 문자를 바로 정렬시켜야지 틀리지 않음

3 2 1
bc
bb
za

라는 입력이 들어온다고 했을 때 정답으로 az가 출력되어야 함.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            //입력 받는 동시에 정렬하여 넣음
            char[] str = br.readLine().toCharArray();
            Arrays.sort(str);
            list.add(new String(str));
        }
        //입력을 다 받았다면 전체를 정렬
        Collections.sort(list);

        //이후 K개를 고르고
        ArrayList<Character> ans = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < list.get(i).length(); j++) {
                ans.add(list.get(i).charAt(j));
            }
        }
        //문자를 재정렬할 수 있으므로 다시 정렬
        Collections.sort(ans);

        StringBuilder sb = new StringBuilder();
        for (char c : ans) {
            sb.append(c);
        }
        System.out.print(sb);
    }
}
