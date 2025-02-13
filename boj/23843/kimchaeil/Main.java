//문제: 23843번 콘센트
//메모리: 13076 KB
//시간: 108 ms

import java.util.*;
import java.io.*;

/*
 * 충전에 필요한 시간을 내림차순으로 정렬하고
 * 많은 시간이 필요한 전자기기부터 대기시간이 가장 짧은 콘센트에 추가하면 된다고 생각했다.
 */


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		int[] times = new int[n];
		
		Queue<Integer> pq = new PriorityQueue<>(); //콘센트들의 대기시간(작은 값부터 나옴)
		for(int i=0;i<m;i++) { //m개 추가
			pq.add(0);
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<n;i++) {
			times[i]=Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(times); //오름차순 정렬
		for(int i=n-1;i>=0;i--) { //뒤에서부터 접근(내림차순)
			pq.add(pq.poll()+times[i]);	//가장 짧은 대기시간에 충전시간을 더해서 우선순위큐에 다시 삽입
		}

		int result=0;
		while(!pq.isEmpty()){ //가장 긴 대기시간 꺼내기
			result=pq.poll(); 
		}
		bw.write(result+"");
		
		bw.flush();
		bw.close();
	}

}