
//제출번호: 91248296
//메모리:   11640 KB
//실행시간: 72 ms
import java.io.*;

//N이 20밖에 안 되기 때문에 모든 경우의 수를 탐색해도 됨
//세준이가 인사할 사람을 부분집합으로 고른 후
//그게 가능한 경우라면 최댓값을 갱신하는 방법으로 구현
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    static int n;
    static int[] loss, happy;
    static int happyMax;

    public static void main(String[] args) throws IOException{
        n = nextInt();
        loss = new int[n];
        happy = new int[n];
        
        for (int i = 0; i < n; i++) {
            loss[i] = nextInt();
        }
        for (int i = 0; i < n; i++) {
            happy[i] = nextInt();
        }

        subset(0, 100, 0);

        System.out.print(happyMax);
    }

    static void subset(int idx, int remainHealth, int acquireHappy) {
        //체력이 이미 0이라면 더 이상 탐색할 필요 X
        if (remainHealth <= 0) {
            return;
        }

        //인사할 사람을 모두 골랐다면 최댓값 갱신
        if (idx == n) {
            happyMax = Math.max(happyMax, acquireHappy);
            return;
        }

        //i번쨰 사람에게 인사할지, 안 할지 골라봄
        subset(idx + 1, remainHealth - loss[idx], acquireHappy + happy[idx]);
        subset(idx + 1, remainHealth, acquireHappy);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}