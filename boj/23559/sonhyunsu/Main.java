//제출번호: 95070594
//메모리:   22780 KB
//실행시간: 300 ms
import java.io.*;
import java.util.*;

//처음에는 냅색인줄 알았는데 N과 X가 커서 불가능했음
//그래서 그리디적인 접근을 한 번 해봤음

//1. 천원 학식이 5천원보다 가치가 높으면 무조건 천원을 먹는게 이득
//2. 5천원 학식이 천원 학식보다 가치가 높은 것들은 따로 모아뒀다가
//5천원 가치 - 천원 가치 기준으로 정렬하고
//살 수 있는 만큼 5천원 학식을 먹는게 이득
public class Main {
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    int x = nextInt() / 1000;
	    
	    List<int[]> betterA = new ArrayList<>();
        int res = 0;
        while (n-- > 0) {
            int a = nextInt(), b = nextInt();
            
            if (a <= b) {
                x--;
                res += b;
            } else {
                betterA.add(new int[]{a, b});
            }
        }
        
        betterA.sort((i1, i2) -> -Integer.compare(i1[0] - i1[1], i2[0] - i2[1]));
        
        x -= betterA.size();
        int cnt = x / 4;
        for (int[] item : betterA) {
            res += item[cnt-- > 0 ? 0 : 1];
        }
        
        System.out.print(res);
	}
    
    static int nextInt() throws IOException {
        int n = System.in.read() & 15, c;
        while ((c = System.in.read()) > 47)
            n = (n << 3) + (n << 1) + (c & 15);
        return n;
    }
}