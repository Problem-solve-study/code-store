import java.io.*;
import java.time.LocalDate;

/*
22704KB, 244ms

PS에서는 한 번도 안써보긴 했는데 문제보자마자 LocalDate에 요일 가져오는걸 써서 바로 판별할 수 있을 것 같았음.
슥슥 짜서 예제 돌려봤더니 잘 나오는거같아서 그대로 제출해서 AC
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int res = 0;
        for (int year = 2019; year <= N; year++) {
            for (int month = 1; month <= 12; month++) {
                LocalDate date = LocalDate.of(year, month, 13);
                if (date.getDayOfWeek().getValue() == 5) {
                    res++;
                }
            }
        }
        System.out.print(res);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
