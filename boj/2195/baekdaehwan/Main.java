/**
 * 	11704KB	84ms
 * 
 * [사고 흐름]
 * 이거 그리디인 것 같다. 코드 길이가 짧을 것 같으니 그냥 제출해보자.
 * 
 * [알고리즘 설명]
 * P문자열을 순차적으로 읽으며, S문자열에서 가장 긴 부분 문자열을 찾고, 그 길이만큼 P문자열을 검사하는 포인터에 더해준다.
 * 포인터의 이동 횟수가 답
 */

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	char[] S = br.readLine().toCharArray();
    	char[] P = br.readLine().toCharArray();
    	
    	int sl = S.length;
    	int pl = P.length;
    	int pp = 0;
    	int res = 0;
    	while (pp<pl) {
    		int maxPgp = pp;
    		for (int sp=0; sp<sl; ++sp) {
    			int sgp = sp;
    			int pgp = pp;
    			while (sgp<sl&&pgp<pl && S[sgp]==P[pgp]) {
    				sgp++;
    				pgp++;
    			}
    			maxPgp = Math.max(maxPgp, pgp);
    		}
    		pp = maxPgp;
    		res++;
    	}
    	System.out.println(res);
    }
}