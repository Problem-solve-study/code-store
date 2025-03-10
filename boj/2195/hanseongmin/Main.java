import java.io.*;

/*
 * 11920KB, 76ms
 * 
 * 그냥 문제 읽자마자 그리디하게 붙일 수 있는 가장 긴 문자열 붙이면 되는거 아닌가? 싶었다
 * 그대로 구현해서 내니까 AC
 */

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	//로직을 다 짜고 보니까 S에서 P를 만드는게 아니라 P에서 S를 만드는걸로 짜서
    	//로직 바꾸기 귀찮아서 그냥 P, S 위치를 변경
    	String P = br.readLine();
    	String S = br.readLine();

    	int cnt = 0;
    	//붙일 문자열의 인덱스
    	for (int i = 0; i < S.length();) {
    		//붙일 문자열의 길이
    		for (int j = i + 1; j <= S.length() + 1; j++) {
    			//문자열의 끝까지 봤는데도 contains가 모두 true라면 i ~ 끝까지를 붙여줌
    			if (j == S.length() + 1) {
    				cnt++;
    				i += j - 1 - i;
    				break;
    			}
    			
    			boolean contains = P.contains(S.substring(i, j));
    			//현재 붙일 수 없다면 이전값을 붙이는게 가장 최선
    			if (!contains) {
    				cnt++;
    				i += j - 1 - i;
    				break;
    			}
    		}
    	}
    	System.out.println(cnt);
    }
}