//제출번호: 92672565
//메모리:   29708 KB
//실행시간: 176 ms
import java.io.*;

//길이가 N인 문자열이 펠린드롬이면서, N/2인 접두사, 접미사도 펠린드롬이어야 한다는 조건을 보고
//재귀로 풀어야겠다고 생각함
//기저조건으로 길이가 1일 때는 항상 펠린드롬이라고 했으니
//조건에 맞게 재귀를 잘 작성하면 풀 수 있음  
public class Main {
    
    static char[] str;
    
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    str = br.readLine().toCharArray();
	    
	    System.out.print(isAkaraka(0, str.length) ? "AKARAKA" : "IPSELENTI");
	}
	
	static boolean isAkaraka(int sPos, int len) {
        //길이가 1이면 항상 true
	    if (len == 1) {
	        return true;
	    }
	    
	    int divLen = len / 2;
	    
	    int ePos = sPos + len - 1; 
	    int mPos = sPos + divLen;
        //양 끝부터 하나씩 비교해보면서 하나라도 다르다면 펠린드롬이 아님 
	    for (int pos = sPos; pos <= mPos; pos++) {
	        if (str[pos] != str[ePos - pos]) {
	            return false;
	        }
	    }
	    
        //N/2 인 접두사, 접미사도 펠린드롬인지 확인해봄
	    return isAkaraka(sPos, divLen) & isAkaraka(len - divLen, divLen);
	}
}