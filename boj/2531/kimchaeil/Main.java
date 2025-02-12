//메모리: 16360 KB
//시간: 116 ms

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int[] sushiTable = new int[n]; //초밥 테이블 정보 배열
		for(int i=0;i<n;i++) { //초밥 테이블 정보 입력
			sushiTable[i]=Integer.parseInt(br.readLine());
		}
		
		int[] eatCount = new int[d+1]; //각 초밥을 먹은 개수
		eatCount[c]=1; //쿠폰으로 받는 초밥은 1개 이미 먹은 것으로 처리
		int cnt=1; //먹은 초밥 종류, 쿠폰으로 받는 초밥을 미리 포함하여 1부터 카운트
		for(int i=0;i<k;i++) { //0~(k-1)번째 초밥 카운트
			if((eatCount[sushiTable[i]]++)==0) { //i번째 초밥을 먹은 적이 없다면 cnt 증가
				cnt++;
			}
		}
		int max=cnt;
		for(int i=0;i<n-1;i++) { //시작지점을 1칸씩 이동
			if((--eatCount[sushiTable[i]])==0) cnt--; //이전에 가장 먼저 먹은 초밥 빼기
			if((eatCount[sushiTable[(k+i)%n]]++)==0) cnt++; //지금 가장 마지막에 먹을 초밥 추가하기
			max=cnt>max?cnt:max; //max 초기화
		}
		bw.write(max+"");
		bw.flush();
		bw.close();
	}

}
