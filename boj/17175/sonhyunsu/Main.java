//제출번호: 89689379
//메모리:   17696 KB
//실행시간: 180 ms
import java.io.*;
import java.util.*;

public class Main {

    static int[] call = new int[51];

    public static void main(String[] args) throws IOException {
        int n = new Scanner(System.in).nextInt();

        call[0] = call[1] = 1; //fi(0), fi(1)의 호출 횟수를 저장한다.
        int divider = (int) 1e9 + 7; //1,000,000,007을 int로 저장한다. 
        for (int i = 2; i <= n; i++) {
            //fi(i)의 호출 횟수는 하위 fi의 호출횟수 + 자신의 호출횟수다.
            call[i] = (call[i - 1] + call[i - 2] + 1) % divider;
        }

        System.out.print(call[n]);
    }
    
}