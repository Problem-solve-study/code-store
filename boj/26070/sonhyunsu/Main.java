//제출번호: 93804595
//메모리:   11520 KB
//실행시간: 68 ms
import java.io.*;

//현재 식권으로 사줄 수 있는 곰곰이들에게 모두 사주고,
//식권을 다른 식권으로 교환한 다음 다시 곰곰이들에게 사주는 과정을
//3번 반복하면 됨
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int[] gom = new int[]{nextInt(), nextInt(), nextInt()};
	    int[] ticket = new int[]{nextInt(), nextInt(), nextInt()};
	    
	    long res = 0;
	    for (int i = 0; i < 3; i++) {
	        for (int j = 0; j < 3; j++) {

				//사줄 수 있는 곰곰이들의 수를 구함
	            int eatGom = Math.min(gom[j], ticket[j]);

				//곰곰이들과 티켓을 사준 만큼 빼고, 결과에 사준 만큼 더함
	            gom[j] -= eatGom;
	            ticket[j] -= eatGom;
	            res += eatGom;
	        }
	        
			//현재 티켓을 가지고 다음 티켓을 교환함
	        int[] newTicket = new int[3];
	        for (int j = 0; j < 3; j++) {
				//3장이면 다음 티켓으로 변경, 못 바꾼 티켓은 그대로 가짐
	            newTicket[j] = ticket[(j+2)%3] / 3 + ticket[j] % 3;
	        }
	        ticket = newTicket;
	    }
	    
	    System.out.print(res);
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}