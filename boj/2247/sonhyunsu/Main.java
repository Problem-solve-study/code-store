//제출번호: 94228025
//메모리:   11580 KB
//실행시간: 448	ms
import java.io.*;

//그냥 2 ~ n / 2까지 둘면서 1 ~ n 중 나눠지는 수의 개수를 더함
//자기 자신은 실질적 약수가 아니기 때문에 1을 빼야 함
//1억까지 돌 수 있어서 시초 각오하고 냈는데 이상하게 통과함

//절반까지만 보는 이유는 2가 최소 실질적 약수, (n / 2) 가 최대 실질적 약수이기 때문
public class Main {

    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
    public static void main(String[] args) throws IOException {
        int n = nextInt(), divN = n >> 1;
        long res = 0;
        final int DIV = 1000000;

        for (int i = 2; i <= divN; i++) {
            //[1, n] 중 i로 나눠 떨어지는 수의 개수를 구한 뒤 1을 빼고 i를 곱함
            //(나눠 떨어지는 수의 개수 - 1)이 곧 i를 실질적 약수로 가지는 수의 개수임
            res += (n / i - 1) * i;
            res %= DIV; //문제 요구사항에 맞게 100만으로 나눔
        }
        
        System.out.print(res);
    }

    static int nextInt() throws IOException {
        st.nextToken();
        return (int) st.nval;
    }
}