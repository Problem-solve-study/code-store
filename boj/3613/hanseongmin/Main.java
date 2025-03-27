import java.io.*;

/*
 * 11520KB, 64ms
 * 
 * Error인 문자열들을 적절히 피해가며 문자열 변환하기
 * 문자열 변환이 메인이 아니라 Error를 출력하는 것이 메인인듯
 */

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	char[] name = br.readLine().toCharArray();

    	boolean isC = false;
    	boolean isJava = false;
    	boolean isError = false;
    	StringBuilder convertName = new StringBuilder();
    	boolean nextUpper = false;
    	//첫 글자, 끝 글자는 반드시 영문자여야하고 첫 글자는 반드시 소문자여야 함
    	if (!Character.isAlphabetic(name[0]) || !Character.isAlphabetic(name[name.length - 1]) || Character.isUpperCase(name[0])) {
    		isError = true;
    	}
    	
    	for (int i = 0; i < name.length && !isError; i++) {
    		if (name[i] == '_') {
    			//_가 연속으로 나오면 에러
    			if (nextUpper) {
    				isError = true;
    			}
    			isC = true; //_가 나왔으면 현재 문자는 C++ 변수명
    			nextUpper = true;
    		} else if (Character.isLowerCase(name[i])) {
    			if (nextUpper) {
    				convertName.append(Character.toUpperCase(name[i]));
    				nextUpper = false;
    			} else {
    				convertName.append(name[i]);
    			}
    		} else {
    			isJava = true; //대문자가 나왔으면 현재 문자는 java 변수명
    			convertName.append('_').append(Character.toLowerCase(name[i]));
    		}
    	}
    	
    	//C++ 변수명이면서 동시에 자바 변수명인 경우도 에러
    	if (isError || (isC && isJava)) {
    		System.out.print("Error!");
    	} else { 
    		System.out.print(convertName);
    	}
    }
}