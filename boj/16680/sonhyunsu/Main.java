import java.io.*;
import java.util.*;

//이거는 진짜 믿음으로 제출했음
//주어진 수의 모든 배수를 찾아보는 건 시간초과 날 확률이 높음
//2^1 ~ 2^k의 배수만 확인하면 언젠가 안수빈수가 한 번은 나오지 않을까 생각했고
//c++로 1부터 1억까지 모두 검사하는 프로그램을 만들어서 안수빈수가 적어도 1개씩 나오는 지 확인했음
//k = 31일 때는 55617563이 안수빈수가 생성되지 않았음
//k = 32일 때 모든 숫자가 통과되었고 그대로 제출
//이게 왜 되는지 설명하라하면.. 컴퓨터가 증명했으니 생략할게요..
//블로그에 더 신박한 풀이가 있으니까 꼭 찾아보세요
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	    
	    int T = Integer.parseInt(br.readLine());
	    for (int t = 0; t < T; t++) {
	        int n = Integer.parseInt(br.readLine());
	        
            //자기자신이 안수빈수라면 그대로 출력
	        if (!isSubinNum(n)) {
	            bw.write(String.format("%d%n", n));
	            continue;
	        }
	        
	        long tester = n;
	        for (int i = 0; i < 32; i++) {
	            tester *= 2; //2^k을 곱한다음 안수빈수인지 검사
	            if (!isSubinNum(tester)) {
	                bw.write(String.format("%d%n", tester));
	                break;
	            }
	        }
	    }
	    
	    bw.flush();
	}
	
	static boolean isSubinNum(long num) {
	    int sum = 0;
	    while(num > 0) {
	        sum += num % 10;
	        num /= 10;
	    }
	    
	    return sum % 2 == 0;
	}
}

/* c++ 검사기
#include <cstdio>

bool test(long long val) {
    int s = 0;
    while (val) {
        s += val%10;
        val/=10;
    }

    return s%2;
}

int main() {
    for (int i = 1; i <= 1e8; i++) {
        if (i%1000000 == 0) {
            printf("%d pass\t", i);
            if (i%5000000 == 0) {
                printf("\n");
            }
        }

        long long val = i;
        if (test(val)) {
            continue;
        }

        bool check = false;
        for (int j = 0; j < 31; j++) {
            val *= 2;
            if (test(val)) {
                check = true;
                break;
            }
        }

        if (!check) {
            printf("%d\n", i);
        }
    }
    printf("Fin!");
    return 0;
}
*/