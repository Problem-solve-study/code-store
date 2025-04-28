import java.io.*;

/*
11672KB, 88ms

제거 대상의 위치를 상대적으로 봐야한다는 점을 채일이한테 듣고 풀었음
K % len == 0일 때 제거 대상이 0번째 아니라 마지막 원소가 대상이 되는데 이를 체크 못했다가 2틀함

만일 5 2 3이라고 하자
1 2 3 4 5에서 맨 처음 2를 지우게 되면
1 3 4 5가 된다. 다음에 맨 처음 시작될 원소는 3이므로 3을 기준으로 재정렬하면
3 4 5 1이 되는데, 이를 다시 1 2 3 4로 바꾸자. 이는 원래 위치에서 K만큼을 빼는 것으로 구할 수 있다.
원래 3이 1이 되었으므로 우리는 이제 1 2 3 4에서 1을 지우는 경우를 고려하면 된다.

다시 한 번 반복이 진행되면 1 3 4가 된다. 3을 기준으로 재정렬하면 3 4 1이며 이를 1 2 3으로 바꾸면
이제 1 2 3에서 3을 지우는 경우를 고려하면 된다.
.
.
.

위 과정을 통해 구해지는 제거 대상의 상대 위치가 K와 같아질 때를 반환하면 된다.
전체 원소가 지워지는 경우는 O(5,000,000)이고 상대 위치를 구하는 것은 O(1)에 가능하므로 시간 내에 풀이가 가능하다.
실제로는 전체 원소의 개수가 변하므로 적절한 모듈러 연산이 필요하다. 여러모로 실수할 건덕지가 매우매우 많은 문제인듯
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        int K = nextInt();
        int M = nextInt();

        //몇 번째 반복인지를 저장
        int i = 0;
        //현재 살아있는 사람의 수
        int len = N;
        //제거 대상의 상대 위치
        int tPos = M;
        //다음 제거 대상이 상대 위치가 같아질 때까지 반복
        //len이 1이 되면 반드시 제거되므로 이를 조건에 같이 넣어주었음
        for (; tPos != (K % len == 0 ? len : K % len) && len != 1; i++) {
            //새로운 상대 위치를 구한다
            tPos -= (K % len);
            //만일 0보다 작은 경우리면 len를 더해주는 것으로 상대 위치를 구할 수 있음
            if (tPos <= 0) {
                tPos += len;
            }
            len--;
        }
        System.out.print(i + 1);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}