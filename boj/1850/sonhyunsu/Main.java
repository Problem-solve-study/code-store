//제출번호:	93228707
//메모리:	59728 KB
//실행시간:	208 ms
import java.io.*;
import java.util.StringTokenizer;

//모든 자릿수가 1이기 때문에 1로 이루어진 숫자로 나누어 떨어지려면 나누어지는 자릿수가 겹치면 안 됨
//다시 말해서 111에서 11은 (백의 자리, 십의 자리), (십의 자리, 일의 자리)에 존재하지만
//십의 자리가 겹치기 때문에 나누어 떨어질 수 없음
//이걸 다시 보면 a의 1의 개수가 어떤 x의 1의 개수와 배수 관계이면 a는 x로 나누어 떨어짐  
//따라서 a, b의 GCD를 구하고, GCD만큼 1을 출력하면 a, b를 모두 나눌 수 있는 최대공약수를 구할 수 있음 
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Long.parseLong(st.nextToken());
        long b = Long.parseLong(st.nextToken());

		//유클리드 호제법을 이용해서 GCD를 구함
        long tmp;
        while (b != 0) {
            tmp = a%b;
            a = b;
            b = tmp;
        }

		//GCD만큼 1을 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < a; i++) {
            sb.append('1');
        }

        System.out.print(sb);
    }
}