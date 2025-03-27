import java.io.*;

/*
 * 36828KB, 208ms
 * 
 * 문제 읽고 서로소인 두 자연수가 다른 색을 칠해져야 하므로 바로 에라토스테네스의 체 문제라는걸 바로 떠올렸다.
 * 소수 판정은 보통 BigInteger 메소드를 써서 바로 구하는 편인데 이 문제는 에라토스테네스의 체를 써야하는 거 같아 오랜만에 구현해봤다. 
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
	
    public static void main(String[] args) throws Exception {
    	int n = nextInt();
    	//색을 저장할 배열
    	int[] arr = new int[n + 1];
    	//소수 여부를 저장할 배열. true면 합성수. false면 소수이다.
    	boolean[] b = new boolean[n + 1];
    	int color = 2;
    	arr[1] = 1;
    	for (int i = 2; i <= n; i++) {
    		//만일 현재 수가 소수라면
    		if (!b[i]) {
    			//새로운 색으로 칠해주고
    			arr[i] = color;
    			//현재 수의 배수를 순회하며 합성수임을 기록하고 현재 수와 동일한 색으로 칠한다.
    			for (int j = 2; true; j++) {
        			if (i * j <= n) {
        				arr[i * j] = color;
        				b[i * j] = true;
        			} else {
        				break;
        			}
        		}
    			color++;
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	for (int i = 1; i <= n; i++) {
    		sb.append(arr[i]).append(' ');
    	}
    	System.out.println(--color + "\n" + sb);
    }
    
    static int nextInt() throws Exception {
    	st.nextToken();
    	return (int) st.nval;
    }
}