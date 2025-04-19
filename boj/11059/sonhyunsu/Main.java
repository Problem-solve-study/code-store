
//제출번호: 93336725
//메모리:   11476 KB
//실행시간: 72 ms
import java.io.*;

// 자릿수의 합을 구할 때 구간합 사용, 구간합과 구간의 길이가 같은 구간이 연속으로 2개 있는 지 확인
// 그리디하게 접근하기 위해서 찾는 구간의 길이는 가능한 가장 큰 길이부터 찾아봄 
public class Main {

    public static void main(String[] args) throws IOException {
        int size = 0;
        int[] sum = new int[1001];
        int c;

        while ((c = System.in.read()) > 47) {
            sum[++size] = c & 15;
            sum[size] += sum[size - 1]; // 구간합 구하기
        }

        int res;

        // size가 홀수면 1 작은 수가 res의 시작값이 되고, 짝구면 size 자체가 res의 시작값이 됨
        find: for (res = size >> 1 << 1; res >= 0; res -= 2) {

            // a는 구간의 처음 - 1, mid는 구간의 중간, b는 구간의 끝을 가리킴
            for (int a = 0, b = res, mid = b >> 1; b <= size; a++, b++, mid++) {

                // (a, mid] 구간과 (mid, b] 구간의 합이 같으면 [a+1, b]가 크리 문자열이 됨
                if (sum[mid] - sum[a] == sum[b] - sum[mid]) {

                    // 더 이상 찾아볼 필요가 없으므로 종료
                    break find;
                }
            }
        }

        System.out.print(res);
    }
}