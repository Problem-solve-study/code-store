/**
 * 11472KB	64ms
 * 다들 신기하게 푸셨네요...
 */

import java.io.*;
import java.util.*;

public class Main {
	static char[] S;
	static boolean hasL;
	static boolean[] isVowel;
	static int cnt = 0;
	static int[] toIdx;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		S = br.readLine().toCharArray();
		isVowel = new boolean[S.length];
		for (char c: S) {
			if (c=='L') hasL = true;
			if (c=='_') cnt++;
		}
		toIdx = new int[cnt];
		cnt = 0;
		for (int i=0; i<S.length; ++i) {
			if (S[i]=='_') toIdx[cnt++] = i;
			if (S[i]=='A' || S[i]=='E' || S[i]=='I' || S[i]=='O' || S[i]=='U') isVowel[i] = true;
		}
		System.out.print(bt(0, 0, 0));
	}
	
	static long bt(int i, int vc, int cc) {
		if (i == cnt) {
			if (!hasL && cc==0) return 0;
			int cnt = 1;
			for (int j=1; j<S.length; ++j) {
				if (isVowel[j]==isVowel[j-1]) {if (++cnt==3) return 0;}
				else cnt = 1;
			}
			return pow(5, vc) * (hasL? pow(21, cc):(pow(21, cc)-pow(20, cc)));
		}
		long sum = 0;
		sum += bt(i+1, vc, cc+1);
		isVowel[toIdx[i]] = true;
		sum += bt(i+1, vc+1, cc);
		isVowel[toIdx[i]] = false;
		return sum;
	}
	
	static long pow(long v, int i) {
		long a = 1;
		for (; i>0; i>>=1) {
			if ((i&1)==1) a *= v;
			v *= v;
		}
		return a;
	}
}