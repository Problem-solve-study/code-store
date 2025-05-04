//제출번호: 93898308
//메모리:   11808 KB
//실행시간: 68 ms
import java.io.*;

//n이 크고 시간 제한이 작아서, 그렇게 많이 고통받았던 거듭 제곱으로 피보나치 구하기 문제인 것을 알았음
//[[x y] [1 0]]을 n 제곱하면 [1 0] 위치에 각각 an의 a1계수, a0계수가 있고, 둘을 잘 곱해서 출력하면 됨
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int x = nextInt();
	    int y = nextInt();
	    int a0 = nextInt();
	    int a1 = nextInt();
	    int n = nextInt();
	    
		//res는 0제곱을 위해서 단위행렬로 저장
        int[] res = {1, 0, 0, 1}, tmp = {x, y, 1, 0};
		//n을 2^k 꼴의 합으로 분해하고, n에 2^k(=i)가 포함되어 있다면 res에 tmp를 더함
        for (int i = 1; i <= n; i <<= 1) {
            if ((n & i) != 0) {
                res = mul(res, tmp);
            }

			//tmp^2을 tmp에 저장
            tmp = mul(tmp, tmp);
        }

		//a1, a0에 계수를 잘 곱하고 마지막 두 자리를 출력
        System.out.printf("%02d", (res[2]*a1 + res[3]*a0) % 100);
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