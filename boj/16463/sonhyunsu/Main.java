//제출번호: 93369523
//메모리:   11648 KB
//실행시간: 104 ms
import java.io.*;

//그냥 n년 까지 모든 금요일에 대해서 13일인 금요일의 개수를 세었음
public class Main {
    
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));
    
	public static void main(String[] args) throws IOException {
	    int goalYear = nextInt();
	    int res = 0;
	    int[] date = {2019, 1, 4}; //2019년 1월 4일이 금요일이어서 시작값을 둠
	    
        //N년을 지나가기 전까지 반복
	    while (date[0] <= goalYear) {
            //다음 금요일을 계산함
	        date[2] += 7;
	        
            //현재 년월의 일수를 구함
	        int monthDays = getMonthDays(date[0], date[1]);

            //만약 일수가 이번 달을 넘겼다면
	        if (date[2] > monthDays) {
	            date[1] += 1; // 다음 달로 이동
	            date[2] -= monthDays; //일 업데이트
	            
                //만약 12월을 넘었다면
	            if (date[1] == 13) {
	                date[1] = 1; //1월로 이동
	                date[0]++; //연도 업데이트
	            }
	        }
	        
            //만약 13일의 금요일이라면 정답 개수 추가
	        if(date[2] == 13) {
	            res++;
	        }
	    }
	    
	    System.out.print(res);
	}
	
	static int getMonthDays(int year, int month) {
        //윤년이면서 2월이면 29일 반환
	    if ((year % 400 == 0 || year % 4 == 0 && year % 100 != 0) && month == 2) {
	        return 29;
	    }
	    
        //나머지는 윤년이 아닐 때의 일수 반환
	    switch (month) {
	        case 2: return 28;
	        case 4: case 6: case 9: case 11: return 30;
	        default: return 31;
	    }
	}
	
	static int nextInt() throws IOException {
	    st.nextToken();
	    return (int) st.nval;
	}
}