//제출번호: 89185476
import java.util.*;

public class Main
{
    static int[] dp = new int[25]; //피보나치
    static int[] max = new int[20001]; //치킨 최댓값
	public static void main(String[] args) {
	    dp[0] = 1;
	    dp[1] = 1;
	    for (int i = 2; i < 25; i++) {
	        dp[i] = dp[i-1] + dp[i-2];
	    }
	    
	    Scanner scan = new Scanner(System.in);
	    
	    int n = scan.nextInt();
	    
        //치킨의 최소 개수는 최대한 2인 1닭을 배치하고
        //1명이 남으면 2인 1닭 세트 1개를 빼고 3인 2닭을 배치하는 것
        //결과적으로 닭 개수가 1개 증가
	    int min = n / 2 + n % 2;
	    
	    for (int i = 2; i <= n; i += 2) {
	        max[i] = max[i - 2] + 1;
	    }
	    
        //max는 dp를 이용해서 최대를 구함
	    for (int i = 3; i < 25; i++) {
	        if (dp[i] > n) {
	            break;
	        }
	        
            max[dp[i]] = Math.max(max[dp[i]], dp[i - 1]);
	        for (int j = dp[i] + 2; j <= n; j++) {
	            max[j] = Math.max(max[j], max[j - dp[i]] + dp[i-1]);
	        }
	    }
	    
	    System.out.println(min + " " + max[n]);
	    
	    scan.close();
	}
}

/* 다른 풀이
 * 제출번호: 89185188
 * import java.util.*;

public class Main
{
	public static void main(String[] args) {
	    Scanner scan = new Scanner(System.in);
	    
	    int n = scan.nextInt();
        
        //min은 위와 동일
	    int min = n / 2 + n % 2;

        //max는 3인 2닭을 최대한 배치하고
        //남은 인원 수는 3인 2닭을 줄이고 2인 1닭으로 배치
        //남은 인원 수가 1일 때는 3인 2닭이 (2인 1닭) x 2이기 때문에 닭 개수는 변함 없음
        //남은 인원 수가 2일 떄는 2인 1닭을 추가로 배치할 수 있기 떄문에 + 1 됨
	    int max = 2 * n / 3; 
	    System.out.println(min + " " + max);
	    
	    scan.close();
	}
}
 */