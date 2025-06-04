import java.io.*;
import java.util.*;

/**
 * X가 최대 5000 인줄 알았는데 5000N 이었네요..
 * DP로 풀었다가 메모리 초과
 * 이게 DP가 아니라고 ?? 그럼 그리디 ?
 * 일단 전부 1000원 짜리로 초기값을 잡고, 남은 돈이 있으면 가장 차이가 큰 5000원짜리 학식을 선택
 * 
 * 1000원 짜리 학식이 더 맛있는 경우가 있다. -> 이거 고려안해서 또 틀...
 */
public class Main {
    static StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
    
    public static void main(String[] args) throws Exception {
        int n = nextInt();
        int x = nextInt();

        int answer = 0;
        int diff[] = new int[n];
        for (int i=0; i<n; i++) {
            int flavor5000 = nextInt();
            int flavor1000 = nextInt();
            answer += flavor1000;
            diff[i] = flavor5000 - flavor1000;
        }

        int money = 1000 * n;
        Arrays.sort(diff);
        int cursor = n-1;
        while (money + 4000 <= x) {
            if (diff[cursor] <= 0) break;
            answer += diff[cursor--];
            money += 4000;
        }
        System.out.print(answer);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}