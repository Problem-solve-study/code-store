//제출번호: 93437861
//메모리:   12800 KB
//실행시간: 140 ms
import java.io.*;

//문제에서 주어지는 분산, 표준편차 수식을 이용해서 잘 구하면 되는 문제
//분산을 상수 시간 만에 구하려고 수식을 변형하니까 정밀도 때문에 2틀 당함
//그냥 O(N)으로 분산을 구하고 제출하니까 맞음
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws IOException {
        int n = nextInt();
        int k = nextInt();

        int[] sum = new int[n + 1];
        int[] arr = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            int num = arr[i] = nextInt();
            sum[i] = num + sum[i - 1];
        }

        double min = Long.MAX_VALUE;
        for (int i = k; i <= n; i++) {
            for (int s = 0, e = i; e <= n; s++, e++) {
				//분산의 최솟값이 곧 표준편차의 최솟값이니까,
				//분산의 최솟값을 min에 저장함
                min = Math.min(min, getV(arr, sum, s, e));
            }
        }

		//마지막에 표준편차를 구함
        System.out.print(Math.sqrt(min));
    }

    static double getV(int[] arr, int[] sum, int s, int e) {
		//평균을 구함
        double m = 1.0 * (sum[e] - sum[s]) / (e - s);
		double res = 0;

        for (int i = s + 1; i <= e; i++) {
			//현재 구간에 대해서 분산을 구하기 위해 제곱의 합을 res에 저장
            double val = arr[i] - m;
            res += val * val;
        }

		//마지막에 구간의 길이로 나눠서 분산을 구함
        return res / (e - s);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}