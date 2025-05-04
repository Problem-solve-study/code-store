//제출번호: 93898308
//메모리:   11808 KB
//실행시간: 68 ms
import java.io.*;

//n이 크고 시간 제한이 작아서, 그렇게 많이 고통받았던 거듭 제곱으로 피보나치 구하기 문제인 것을 알았음
//[[x y] [1 0]]을 n-1 제곱하면 [x y] 위치에 각각 an의 a1계수, a0계수가 있고, 둘을 잘 곱해서 출력하면 됨
//행렬은 a2부터 있으므로, a0, a1은 예외처리
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int x = nextInt();
	    int y = nextInt();
	    int a0 = nextInt();
	    int a1 = nextInt();
	    int n = nextInt();
	    
	    StringBuilder sb = new StringBuilder();
	    if (n < 2) { //a0, a1 예외처리
	        sb.append(String.format("%02d", n == 0 ? a0 : a1));
	    } else {
	        n--;
	        int[] res = {1, 0, 0, 1}, tmp = {x, y, 1, 0};

			//[[x, y], [1, 0]]의 n제곱을 구함 
    	    for (int i = 1; i <= n; i <<= 1) { //i는 1, 2, 4, ... 순으로 증가
				//n이 2^k(=i) bit를 가지고 있다면
    	        if ((n & i) != 0) {
					//res * tmp를 res에 저장
    	            res = mul(res, tmp);
    	        }
    	        
				//tmp^2을 tmp에 저장
    	        tmp = mul(tmp, tmp);
    	    }
    	    
			//계수를 a1, a0와 잘 곱하고 마지막 두 자리를 출력
    	    sb.append(String.format("%02d", (res[0]*a1 + res[1]*a0) % 100));
	    }
	    
	    System.out.print(sb);
	}
	
	//2x2 행렬 곱셈 - 1차원으로 변경 후 인덱스에 맞게 곱했음
	static int[] mul(int[] a, int[] b) {
	    return new int[] {
	      (a[0]*b[0] + a[1]*b[2]) % 100,
	      (a[0]*b[1] + a[1]*b[3]) % 100,
	      (a[2]*b[0] + a[3]*b[2]) % 100,
	      (a[2]*b[1] + a[3]*b[3]) % 100
	    };
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}