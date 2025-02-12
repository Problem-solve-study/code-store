//제출번호: 89969172
//메모리:   16364 KB
//실행시간: 120 ms
import java.io.*;
import java.util.*;

//투포인터룰 활용한 탐색.
//초밥의 가짓수를 세기 위해 초밥당 먹은 수를 세었음
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] belt = new int[n];
        for (int i = 0; i < n; i++) {
            belt[i] = Integer.parseInt(br.readLine());
        }

        int[] eatCount = new int[d + 1];
        int res = 0;
        int start = 0;
        int end = 0;
        int sushiCount = 0;

        //연속으로 0~k-1개의 접시를 고르는 경우를 만들어준다.
        for (int i = 0; i < k; i++) {
            int sushi = belt[end++];
            if(eatCount[sushi] == 0) { //한 번도 먹지 않은 초밥이라면
                sushiCount++; //초밥의 가짓수를 늘려준다.
            }
            eatCount[sushi]++;
        }

        //최초 결과는 위에서 계산한 초밥의 가짓수 + 쿠폰이다.
        res = sushiCount + (eatCount[c] == 0 ? 1 : 0);

        //end를 k -> k+1 -> ... -> n -> 0 -> 1 -> ... -> k-2까지 반복하게 한다.
        for (int i = 1; i < n; i++) {
            int sSushi = belt[start];
            int eSushi = belt[end];
            start = (start + 1) % n;
            end = (end + 1) % n;

            eatCount[sSushi]--;
            if (eatCount[sSushi] == 0) { //먹지 못하게 된 초밥이 있으면
                sushiCount--; //초밥의 가짓수를 줄인다.
            }

            if(eatCount[eSushi] == 0) { //한 번도 먹지 않은 초밥이라면
                sushiCount++; //초밥의 가짓수를 늘린다.
            }
            eatCount[eSushi]++;

            //각각의 경우마다 초밥의 최종 가짓수를 구해서 res에 업데이트한다.
            res = Math.max(res, sushiCount + (eatCount[c] == 0 ? 1 : 0));
        }

        System.out.print(res);
    }
}