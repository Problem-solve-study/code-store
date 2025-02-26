/**
 * 	11364KB	68ms
 *  
 * 승리 패배 판단 조건은 타 코드와 동일함.
 * 보드의 크기가 3X3사이즈이므로 비트마스킹으로 접근하는 것이
 * 실수할 확률이 낮다고 생각하여, 비트마스킹으로 풀었음.
 */

import java.io.*;
import java.util.*;

public class Main {
	static int[] win = {
		0b111000000,
		0b100100100,
		0b100010001,
		0b010010010,
		0b001001001,
		0b001010100,
		0b000111000,
		0b000000111,
	};
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while (!(str = br.readLine()).equals("end")) {
        	int p1 = 0;
        	int p2 = 0;
        	int p1cnt = 0;
        	int p2cnt = 0;
        	int p1win = 0;
        	int p2win = 0;
        	boolean blankExists = false;
        	
        	for (char c: str.toCharArray()) {
        		p1 = (p1<<1) | (c=='X'?1:0);
        		p2 = (p2<<1) | (c=='O'?1:0);
        		if (c=='X') p1cnt++;
        		else if (c=='O') p2cnt++;
        		else blankExists = true;
        	}
        	
        	for (int w: win) {
        		if ((p1&w) == w) p1win++;
        		else if ((p2&w) == w) p2win++;
        	}

        	boolean isValid = true;
        	if (p1cnt-p2cnt < 0 || 1 < p1cnt-p2cnt) isValid = false;
        	else if (p1win>0 && p2win>0) isValid = false;
        	else if (p1win>0 && p1cnt-p2cnt != 1) isValid = false;
        	else if (p2win>0 && p1cnt-p2cnt != 0) isValid = false;
        	else if (p1win==0 && p2win==0 && blankExists) isValid = false;
        	
        	System.out.println(isValid? "valid":"invalid");
        }
    }
}