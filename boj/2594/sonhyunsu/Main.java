//제출번호:	93025421
//메모리:	11484 KB
//실행시간:	64 ms
import java.io.*;

//처음에는 정렬해서 구할까 했는데 24시간 밖에 안 돼서 휴식 플래그를 세우고 그냥 돌리기로 함
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int n = nextInt();
	    boolean[] canRest = new boolean[1441]; //24시간을 분단위로 저장할 배열 선언
	    int playStart = toMinute(1000);
	    int playEnd = toMinute(2200);
	    
		//일과 시간을 모두 휴식 가능으로 선언
	    for (int i = playStart; i < playEnd; i++) {
	        canRest[i] = true;
	    }
	    
	    for (int i = 0; i < n; i++) {
			//휴식 불가능한 구간을 입력받은 뒤
	        int start = toMinute(nextInt()) - 10;
	        int end = toMinute(nextInt()) + 10;
	        
			//휴식 불가능하다고 업데이트한다
	        for (int time = start; time < end; time++) {
	            canRest[time] = false;
	        }
			
			//true, false로 기록했기 때문에
			//start가 10시 전으로 계산되어도 문제 없음
	    }
	    
	    int res = 0;
	    int restTime = 0;
		//모든 일과 시간에 대해서 
	    for (int i = playStart; i <= playEnd; i++) {
			//쉴 수 있으면 +1
	        if (canRest[i]) {
	            restTime++;
	        } else {
				//쉴 수 없으면 res에 업데이트 후 0으로 변경
	            res = Math.max(res, restTime);
	            restTime = 0;
	        }
	    }
 	    
	    System.out.print(res);
	}
	
	//(시)(분)을 분으로 바꾸는 함수
	static int toMinute(int hm) {
	    return hm / 100 * 60 + hm % 100;
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}