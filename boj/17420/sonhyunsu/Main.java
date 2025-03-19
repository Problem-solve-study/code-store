//제출번호: 91589235
//메모리:   25220 KB
//실행시간: 464 ms
import java.io.*;
import java.util.*;

//정렬 후 그리디로 답을 찾으면 풀 수 있음
//같은 날짜에 대해서는 남은 기한이 달라도 풀 수 있으므로 이에 대한 예외처리가 필요함
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        
        int[] remains = new int[n];
        for (int i = 0; i < n; i++) {
            remains[i] = nextInt();
        }

        int[][] useOrder = new int[n][];
        for (int i = 0; i < n; i++) {
            //사용해야 하는 날짜와 남은 기한을 묶어서 저장
            useOrder[i] = new int[]{nextInt(), remains[i]};
        }

        //사용해야 하는 날짜를 오름차순으로 정렬
        Arrays.sort(useOrder, (o1, o2) -> Integer.compare(o1[0], o2[0]));

        long res = 0;
        long prevNeedDay = 0; //직전에 반드시 있어야 하는 남은 기한의 수
        long curDay = 0;      //현재 날짜 
        long curNeedDay = 0;  //현재에 반드시 있어야 하는 남은 기한의 수
        for (int[] item : useOrder) {
            int day = item[0];
            int remain = item[1];

            //만약 기프티콘을 사용해야 하는 날짜가 바뀐다면
            if (curDay < day) {
                //직전까지 반드시 필요한 남은 기한과 기프티콘을 사용해야 하는 날짜 중 최댓값을 저장
                //왜냐하면 남은 기한보다 더 많은 남은 기한을 만들어야 이전의 기프티콘을 쓸 수 있기 때문임
                prevNeedDay = Math.max(curNeedDay, day);
                curDay = day;
            }

            //기한 연장할 횟수
            long click = 0;

            //만약 지금 남은 기한보다 직전에 반드시 필요한 기한이 더 큰 경우
            if (prevNeedDay > remain) {
                //기한 연장할 횟수를 계산하고 남은 기한에 반영함
                long diff = prevNeedDay - remain;
                click = diff / 30 + (diff % 30 == 0 ? 0 : 1);
                remain += 30 * click;
            }

            res += click;
            //현재 날짜(curDay)에 대해서 기한 연장된 날짜들 중 최댓값을 저장함
            curNeedDay = Math.max(curNeedDay, remain);
        }

        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}