//제출번호: 90528943
//메모리:   11528 KB
//실행시간: 64 ms
import java.io.*;

//n이 너무 커서 무작정 탐색으로 할 수는 없는 것이라 생각함
//모든 자리수로 나누어 떨어지는 수를 찾아야 하니까 lcm을 이용하기로 결정
//n을 1 곱하고, 10 곱하고, 100 곱해보면서 탐색함
//바로 나누어 떨어지면 그 수가 가장 작은 수이고
//나누어 떨어지지 않으면 나누어 떨어지기 위해 필요한 수를 계산한 후에
//곱한 수 범위 이내로 결정할 수 있으면 가장 작은 수를 만들 수 있음
public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer st = new StreamTokenizer(br);

    public static void main(String[] args) throws IOException {
        long n = nextLong();

        //lcm을 구함
        long lcm = 1;
        long cpyN = n;
        while (cpyN > 0) {
            long num = cpyN % 10;
            if (num != 0) {
                long g = gcd(num, lcm);
                lcm = lcm / g * num;  
            }
            cpyN /= 10;
        }

        long res = 0;
        //10단위로 올라가면서 찾아봄
        for (long i = 1; ; i *= 10) {
            long target = n * i;

            //만약 바로 나누어 떨어지면 찾은 것
            if (target % lcm == 0) {
                res = target;
                break;
            } else {
                //나누어 떨어지기 위해 필요한 수를 계산하고
                long needValue = lcm - target % lcm;
                //[1 ~ i) 범위 이내에 있는 수라면 (필요한 수를 더했을 때 자릿수를 넘지 않는다면)
                if (i - needValue > 0) {
                    //필요한 숫자를 더함으로써 가장 작은 수를 만들 수 있음
                    res = target + needValue;
                    break;
                }
            }
        }

        System.out.print(res);
    }
    
    static long gcd(long a, long b) {
        long t;
        while (b != 0) {
            t = a % b;
            a = b;
            b = t;
        }

        return a;
    }
    static long nextLong() throws IOException {
        st.nextToken();
        return (long) st.nval;
    }
}