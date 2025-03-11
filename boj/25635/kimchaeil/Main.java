import java.io.*;

/*
 * 최댓값과 최댓값을 제외한 나머지들의 합(이하 나머지합)을 비교해야한다.
 * (1) 만약, 최댓값이 나머지합보다 작거나 같다면 최댓값을 가지는 놀이기구를 다른 놀이기구 사이에 넣으면 모두 최대한으로 탈 수 있다.
 * (2) 만약, 최댓값이 나머지합보다 1 크다면 최댓값을 가지는 놀이기구 사이에 다른 놀이기구들을 넣으면 모두 최대한으로 탈 수 있다.
 * (3) 만약, 최댓값이 나머지합보다 2이상 크다면 (나머지합*2+1)만큼만 탈 수 있다.
 * 
 * (2)의 경우는 (3)의 경우와 합쳐서 생각할 수 있다. (2)의 경우 최댓값은 나머지합+1이고 최대한으로 탄다면 나머지합+나머지합+1이므로 나머지합*2+1과 같다.
 * 즉, 최댓값이 나머지합과 작은지 큰지만 확인하여 처리해주면 된다.
 * 입력받으면서 최댓값과 총합을 구해준다.
 * 총합의 절반보다 최댓값이 크면 이는 나머지합보다 최댓값이 크다는 것이다.
 * 총합의 절반과 최댓값을 비교하여 최댓값이 크다면 (총합-최댓값)*2+1을 출력하고 아니라면 총합을 출력하면된다.
 */

public class Main {
	static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

	public static void main(String[] args) throws IOException {
		long n = nextLong();
		long result = 0;
		long max = 0;
		for (int i = 0; i < n; i++) {
			long input = nextLong();
			result += input;
			max = max < input ? input : max;
		}
		System.out.println((result>>1) >= max?result:((result-max)<<1)+1);
	}

	static long nextLong() throws IOException {
		st.nextToken();
		return (long) st.nval;
	}
}
