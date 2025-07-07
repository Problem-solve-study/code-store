//제출번호: 96032081
//메모리:   12580 KB
//실행시간: 128 ms
import java.io.*;

// O(N^2), 왼쪽에 있는 모든 돌로부터 현재 돌로 이동할 수 있는지 확인
// 마지막 징검다리에 도착할 수 있는지 확인 후 출력하면 됨
public class Main {
	public static void main(String[] args) throws IOException {
	    int n = nextInt(), k = nextInt();
	    
	    int[] stones = new int[n];
	    boolean[] canGo = new boolean[n];
	    canGo[0] = true;
	    for (int i = 0; i < n; i++) {
	        stones[i] = nextInt();
	        for (int j = 0; j < i; j++) {
	            if (canGo[j] && (i - j) * (1 + Math.abs(stones[i] - stones[j])) <= k) {
	                canGo[i] = true;
	                break;
	            }
	        }
	    }
	    
	    System.out.print(canGo[n-1] ? "YES" : "NO");
	}
	
	static int nextInt() throws IOException {
	    int n = System.in.read() & 15, c;
	    while ((c = System.in.read()) > 47)
	        n = (n << 3) + (n << 1) + (c & 15);
        return n;
	}
}