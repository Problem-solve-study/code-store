import java.io.*;

/*
22120KB, 156ms

가희가 봐야할 수열과 단비가 봐야할 수열을 만들고
전체 빌딩의 수가 N이 되도록 1 1 1 ...의 수열을 만든 후
이를 가희 수열, 단비 수열에 적절히 끼워넣기.

사전 순으로 출력해야하므로 가희가 봐야할 건물이 1이 아니라면
무조건 (1 수열) (가희 수열) (단비 수열)이 되어야 한다.

가희가 봐야할 건물이 1이라면 (1 수열)을 (가희 수열)보다 앞에
둘 수 없으므로 (가희 수열) (1 수열) (단비 수열)로 배치해야함
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int a = nextInt();
        int b = nextInt();

        int aCnt = 0;
        int bCnt = 0;
        int max = Math.max(a, b);
        StringBuilder aSb = new StringBuilder();
        for (int i = 1; i <= a; i++) {
            aCnt++;
            if (i == a) {
                aSb.append(Math.max(i, max));
            } else {
                aSb.append(i);
            }
            aSb.append(' ');
        }

        StringBuilder bSb = new StringBuilder();
        for (int i = b - 1; i >= 1; i--) {
            bCnt++;
            bSb.append(i).append(' ');
        }

        if (aCnt + bCnt > N) {
            System.out.print(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            int oneCount = N - (aCnt + bCnt);
            for (int i = 0; i < oneCount; i++) {
                sb.append('1').append(' ');
            }

            if (a == 1) {
                System.out.print("" + aSb + sb + bSb);
            } else {
                System.out.print("" + sb + aSb + bSb);
            }
        }
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}