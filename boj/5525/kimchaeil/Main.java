//문제: BOJ 5525번 IOIOI
//메모리: 22320 KB
//시간: 152 ms

/*
 * KMP를 이용하여 패턴과 일치하는 부분문자열 개수 찾기
 */

import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine())<<1; //패턴 길이는 N*2+1이다. 마지막 인덱스는 N*2
		int m = Integer.parseInt(br.readLine());
		char[] s = br.readLine().toCharArray();
        
		int result = 0;
		int idx = 0;
		for(int i=0;i<m;) {
			if(s[i]==(((idx&1)==0)?'I':'O')) { //현재 문자가 패턴의 문자와 일치한다면(패턴의 짝수번째는 I, 홀수번째는 O(0부터 세었을 때))
				if(idx==n) { //패턴 길이만큼 일치했다면
					result++; //카운트
					idx-=2; //OI가 반복되므로 idx를 2감소
					continue;
				}
				idx++; //아직 패턴길이만큼 확인하지 않았다면 다음 글자 비교를 위해 idx와 i 증가
				i++;
				continue;
			}
			//현재 문자가 패턴의 문자와 불일치한다면
			if(idx!=0) //패턴의 첫글자와 비교한게 아니라면 idx를 0으로
				idx=0;
			else //패턴의 첫글자와 비교했다면 다음 글자로 넘어가기, idx는 이미 0이다.
				i++;
		}
		System.out.println(result);
	}
}
