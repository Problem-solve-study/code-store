import java.io.*;
import java.util.*;

/*
 * 11708KB, 72ms
 * 
 * 주어진 문자열을 펠린드롬으로 바꾸기
 * 팰린드롬으로 만들 수 있으려면 입력으로 주어진 모든 문자들의 개수가 짝수거나
 * 홀수가 단 1개만 있어야함. 그게 아니라면 불가능
 * 어 근데 여러개 만들어질 수 있을텐데 이거 왜 스페셜저지가 아니지? 아 사전순으로 앞서는걸 출력해야하구나
 * 
 * 펠린드롬을 만들 수 있다면 사전순으로 앞서는걸 어떻게 만들지
 * 주어진 글자를 트리맵에 문자, 개수로 담는다면 순회할 때 사전순으로 앞선 문자열을 만들 수 있음.
 * 트리맵에 담고 순회하여 팰린드롬의 절반을 배치한 다음
 * 홀수인 글자가 하나 있으면 그걸 중간에 끼워넣고 리버스한걸 추가하면 만들 수 있을듯
 */

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	TreeMap<Character, Integer> map = new TreeMap<>();
    	String str = br.readLine();
    	for (int i = 0; i < str.length(); i++) {
    		//각 문자와 개수를 트리맵에 카운팅
    		map.put(str.charAt(i), map.getOrDefault(str.charAt(i), 0) + 1);
    	}

    	//홀수인 녀석이 있는지, 있다면 몇개인지 카운팅
    	char oddChar = 'a';
    	int oddCnt = 0;
    	for (Map.Entry<Character, Integer> e : map.entrySet()) {
    		if (e.getValue() % 2 != 0) {
    			oddCnt++;
    			oddChar = e.getKey();
    		}
    	}
    	
    	//홀수인 문자가 1개 초과라면 팰린드롬 문자열 생성 불가
    	if (oddCnt > 1) {
    		System.out.print("I'm Sorry Hansoo");
    	} else {
    		StringBuilder sb = new StringBuilder();
    		//사전순으로 앞서는 문자열을 만들어야하므로 트리맵에서 순회
    		//트리맵은 자동으로 정렬해주므로 항상 사전순으로 앞선 문자열 생성을 보장 가능
        	for (Map.Entry<Character, Integer> e : map.entrySet()) {
        		char key = e.getKey();
        		int cnt = e.getValue();
        		//팰린드롬의 절반만 만드는거라 cnt / 2만큼만 추가
        		//만일 홀수 문자가 1개만 존재하는 경우 이때 추가되면 안되는데 cnt / 2만큼만 반복하므로 
        		//홀수 문자가 이때 추가되게 하지 않는 효과도 있음
        		for (int i = 0; i < cnt / 2; i++) {
        			sb.append(key);
        		}
        	}
        	
        	//리버스 용 임시 sb 하나 더 할당
        	StringBuilder sb2 = new StringBuilder(sb);
        	//홀수 문자가 있었다면 팰린드롬 절반 다음에 홀수 문자 삽입
        	if (oddCnt == 1) {
        		sb.append(oddChar);
        	}
        	//팰린드롬의 정의에 따라 팰린드롬의 절반에 reverse한 문자열을 삽입
        	sb.append(sb2.reverse());
        	System.out.print(sb);
    	}
    }
}