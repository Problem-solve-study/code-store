import java.io.*;
import java.util.*;

/*
 * 17812KB, 1536ms
 * 
 * 각 문자별로 숫자가 다 달라야하니까 최대 10개의 문자를 대상으로
 * 순열을 돌리면 풀릴거라 생각했다.
 * 
 * toCharArray를 반복 호출했다가 MLE 1번
 * 값 계산할 때 중간 변수를 int로 선언해서 WA 2번
 */

public class Main {
    static String[] words;
    static ArrayList<Character> chars = new ArrayList<>();
    static int[] charToValue = new int[26];
    static boolean[] selected = new boolean[10];
    static boolean isYes = false;
    
	public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	words = br.readLine().split(" ");
    	initArr();
    	dfs(0);
    	System.out.print(isYes ? "YES" : "NO");
    }
    
	//주어진 문자열에서 문자들을 추출
    static void initArr() {
    	for (int i = 0; i < 26; i++) {
    		charToValue[i] = -2;
    	}
    	
    	for (String word : words) {
    		for (char c : word.toCharArray()) {
    			if (charToValue[c - 'A'] == -2) {
    				chars.add(c);
    				charToValue[c - 'A'] = -1;
    			}
    		}
    	}
    }
    
    static void dfs(int idx) {
    	//모두 배정됐다면 값 계산
    	if (idx == chars.size()) {
    		long word0Value = getValue(words[0]);
    		long word1Value = getValue(words[1]);
    		long word2Value = getValue(words[2]);
    		if (word0Value + word1Value == word2Value) {
    			isYes = true;
    		}
    		
    		return;
    	}
    	
    	//뽑힌 문자들을 대상으로 순열을 이용하여 숫자들을 배정
    	for (int i = 0; i < 10; i++) {
			if (selected[i]) continue;
			selected[i] = true;
			charToValue[chars.get(idx) - 'A'] = i;
			dfs(idx + 1);
			selected[i] = false;
			charToValue[chars.get(idx) - 'A'] = -1;
		}
    }
    
    //값 계산
    static long getValue(String word) {
    	long value = 0;
    	long base = (long)Math.pow(10, word.length() - 1);
    	for (int i = 0; i < word.length(); i++) {
    		char c = word.charAt(i);
    		value += (base * charToValue[c - 'A']);
    		base /= 10;
    		if (base == 0) base = 1;
    	}
    	return value;
    }
}