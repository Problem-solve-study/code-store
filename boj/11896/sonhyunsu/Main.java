//제출번호:	93062567
//메모리:	11496 KB
//실행시간:	68 ms
import java.io.*;

/*한 번 다음과 같이 생각해보자
1. 직선을 하나 긋는다.
2. 첫 점을 직선의 왼쪽에 둔다
3. 직선이 변을 자르도록 만들기 위해서 다음 점은 직선의 오른쪽에 둔다
4. 직선이 변을 자르도록 만들기 위해서 다음 점은 직선의 왼쪽에 둔다
5. 다각형의 모든 점을 다 찍을 때까지 3~4 과정을 반복한다.
6-1. 이 때 첫 점과 마지막 점이 모두 왼쪽에 있으면 첫 점과 마지막 점을 잇는 변은 직선이 자를 수 없다
6-2. 첫 점이 왼쪽에 있고 마지막 점이 오른쪽에 있으면 직선이 변을 자를 수 있도록 이을 수 있다.

6-1은 점의 개수가 홀수일 때 나올 수 있는 결과물이고
6-2는 점의 개수가 짝수일 때 나올 수 있는 결과물이다

따라서 다각형의 점의 개수가 짝수일 때만 가능하다는 결론이 나옴
그래서 구간을 줬을 때 짝수의 합만 잘 구해주면 됨
2각형은 없으니까 주의할 것
*/
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    long a = nextInt() - 1 >> 1; //1 ~ a-1 구간의 짝수의 개수를 구할 때 쓸 것
	    long b = nextInt() >> 1; //1 ~ b 구간의 짝수의 개수를 구할 때 쓸 것
	    //2로 나눈 이유는 홀수는 의미없기 때문에 버리고, 연속된 짝수의 구간을 만들기 위해서 사용함

		//(1 ~ b의 합) - (1 ~ a의 합) 으로 총합을 구함
	    long res = (b * (b + 1) / 2) - (a * (a + 1) / 2);

		//2각형을 삭제하는 조건, 입력된 a, b가 모두 1이면 2각형을 세지 않았으므로 1을 빼면 안 됨
	    if (a < 1 && b > 0) {
	        res -= 1;
	    }
	    
		//1 + 2 + 3 + ... ==> 2 + 4 + 6 + ... 으로 만들기 위해서 2배 함
	    System.out.print(res << 1);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}