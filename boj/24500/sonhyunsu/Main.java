//제출번호: 94140564
//메모리:   11760 KB
//실행시간: 68 ms
import java.io.*;

//N개의 카드를 가지고 만들 수 있는 최댓값은 N이 가진 비트들을 모두 1로 만든 값임
//예를 들어 N의 비트가 3자리일 때 만들 수 있는 최댓값은 111(2) = 7
//만약에 주어진 N의 비트가 모두 1이면 N 한 장으로 최댓값을 만들 수 있고
//1이 아닌 비트들이 있다면 걔들만 1로 바꿔주는 숫자를 한 장 고르면 됨
public class Main {
	public static void main(String[] args) throws IOException {
	    long n = Long.parseLong(new BufferedReader(new InputStreamReader(System.in)).readLine());
	    
        //target을 2^k - 1 꼴로 만듦 - target이 N개의 카드들로 만들 수 있는 최댓값
	    long target = 1;
	    while (target <= n) {
	        target <<= 1;
	    }
	    target--;
	    
	    if (target == n) {
            //만약에 target이 n과 같다면 n(=target) 카드를 한 장 내면 됨
	        System.out.printf("1%n%d", target);
	    } else {
            //다르다면 0인 비트들을 1로 바꿔줄 수 있는 카드와 n, 총 두 장 내면 됨
            //n이 가장 큰 비트를 가지고 있기 때문에 항상 n이 더 큼
	        System.out.printf("2%n%d%n%d", target ^ n, n);
	    }
	}
}