//제출번호: 96035923
//메모리:   17052 KB
//실행시간: 156 ms
import java.io.*;

// a가 2개 이상을 볼 수 있으면 a, b에 필요한 건물을 제외하고
// 남아 있는 1을 a가 필요한 건물 앞에 쭉 설치하면 됨
// a가 한 개만 볼 수 있다면 맨 앞에는 b에서 필요한 건물을 세워야 함
// 그러면 남아 있는 1은 건물을 하나 설치한 다음 쭉 설치하고
// 다음 공간에 b를 만족하는 건물을 세우면 됨
public class Main {
	public static void main(String[] args) throws IOException {
	    int n = nextInt(), a = nextInt(), b = nextInt();
	    
	    int remain = n - a - b + 1;
	    if (remain >= 0) {
	        StringBuilder sb = new StringBuilder();
	        
	        if (a != 1) {
    	        while (remain-- > 0) {
    	            sb.append(1).append(' ');
    	        }
	        }
	        
	        int max = Math.max(a, b);
	        for (int i = 1; i < a; i++) {
	            sb.append(i).append(' ');
	        }
	        
	        sb.append(max).append(' ');
	        if (a == 1) {
    	        while (remain-- > 0) {
    	            sb.append(1).append(' ');
    	        }
	        }
	        
	        for (int i = b-1; i > 0; i--) {
	            sb.append(i).append(' ');
	        }
	        
	        System.out.print(sb);
	    } else {
	        System.out.print(-1);
	    }
	}
	
	static int nextInt() throws IOException {
	    int n = System.in.read() & 15, c;
	    while ((c = System.in.read()) > 47)
	        n = (n << 3) + (n << 1) + (c & 15);
        return n;
	}
}