import java.io.*;

/*
16072KB, 164ms

예전에 알고리즘 과제로 나왔던 회전 초밥이랑 아이디어가 비슷함
합을 딱 한 번만 계산한 후 슬라이딩 윈도우로 해결
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt(); int K = nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = nextInt();
        }

        //초기 합을 계산
        int value = 0;
        for (int i = 0; i < K; i++) {
            value += arr[i];
        }

        //이후로는 슬라이딩 윈도우로 값을 변화시키며 답 갱신 시도
        //인덱스는 모듈러로 관리
        int max = value;
        int tail = 1;
        int head = K;
        for(; tail < N; tail++, head++) {
            value -= arr[tail - 1];
            value += arr[head % N];
            max = Math.max(value, max);
        }
        System.out.print(max);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}
