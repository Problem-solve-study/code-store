import java.io.*;
import java.util.*;

/*
 * 12212KB, 80ms
 * 
 * 이거 뭔가 수학적으로 규칙찾는 문제인가 하고 계속 들여다봤는데 
 * 견적 안나오는거 같고 최대 수도 50개밖에 안되니 브루트포스를 채택
 * 
 * 1. 수열을 입력받고 수열 내 최대 숫자가 얼마인지 추출
 * 2. 큰 수부터 수열을 탐색하여 해당 수를 탐색. 
 * 문제의 출력을 보았을 때 수열의 복구가 불가능한 경우는 없는 것 같으니 반드시 1개 이상 존재할 것이 보장 
 * 3. 해당 수가 탐색됐다면 해당 수를 선택했다는 점과 함께 공백이 들어가야할 위치를 기록
 * 4. 최종적으로 1까지 도달했다면 탐색을 종료하고 수와 함께 공백을 출력
 * 
 * 일의 자리는 가능한 경우의 수가 많으므로 큰 수부터 1로 역방향으로 탐색해야지 탐색 경로를 줄일 수 있음
 */

public class Main {
	static String s;
	static int maxNum;
	static boolean[] v; //공백이 들어가야할 위치를 기록
	static boolean[] v2; //현재 수가 채택됐는지를 기록
	static boolean flag = false;
	
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	s = br.readLine();
    	v = new boolean[s.length() + 1];
    	v2 = new boolean[s.length()]; 
        //현재 수열 내에서 최댓값 탐색
    	getMaxNum();
    	
        //큰 수부터 탐색
    	dfs(maxNum);
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < s.length(); i++) {
    		sb.append(s.charAt(i));
    		if (v[i + 1]) sb.append(' ');
    	}
    	System.out.print(sb);
    }
    
    static void getMaxNum() {
    	int len = 0;
    	int num = 0;
    	for (int i = 1; true; i++) {
    		if (len == s.length()) break;
    		if (i < 10) {
    			len++;
    		} else {
    			len += 2;
    		}
    		num++;
    	}
    	maxNum = num;
    }
    
    static void dfs(int num) {
        //만일 1까지 모두 보았으면 체크
    	if (num < 1) {
    		flag = true;
    	}
    	
    	//일의 자리만 존재하면 true
    	boolean one = num / 10 == 0;
    	if (one) {
    		for (int i = 0; i < s.length(); i++) {
    			if ((s.charAt(i) - '0') == num && !v2[i]) {
    				v[i + 1] = true;
    				v2[i] = true;
    				dfs(num - 1);
    				if (flag) return;
    				v2[i] = false;
    				v[i + 1] = false;
    			}
    		}
    	} else {
    		for (int i = 0; i < s.length() - 1; i++) {
    			int num1 = s.charAt(i) - '0'; //십의 자리
    			int num2 = s.charAt(i + 1) - '0'; //일의 자리
    			if (((10 * num1 + num2) == num) && !v2[i] && !v2[i + 1]) {
    				v[i + 2] = true;
    				v2[i] = true;
    				v2[i + 1] = true;
    				dfs(num - 1);
    				if (flag) return;
    				v2[i] = false;
    				v2[i + 1] = false;
    				v[i + 2] = false;
    			}
    		}
    	}
    }
}