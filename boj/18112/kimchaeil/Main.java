//메모리: 11616 KB
//시간: 72 ms

import java.io.*;
import java.util.*;
/*
	목표가 '최소' 동작 횟수이므로 BFS로 선택
	첫번째 연산은 보수 연산이므로 목표 비트에 xor 연산
	두번째 연산은 +1, 세번째 연산은 -1로 해결
	첫번째 연산에서는 MSB를 알아야 함
	MSB를 구하는 연산을 구현할 수도 있지만
	Java의 Integer.highestOneBit 사용
	Integer.highestOneBit는 최상위비트값을 반환
	만약, Integer.highestOnebit(7)은 7이 '111'이기 때문에 '100'인 4를 반환
*/
public class Main {
	static final int LIMIT = 1<<11; //최대값
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int start = Integer.parseInt(br.readLine(),2); //시작 이진수를 int로 변환
		int end = Integer.parseInt(br.readLine(),2); //목표 이진수를 int로 변환
		
		boolean visited[] = new boolean[LIMIT]; //이미 방문한 수인지 확인하기 위한 배열
		Queue<int[]> queue = new ArrayDeque<int[]>(); //길이가 2인 배열을 넣을 큐 
		//큐에 넣을 데이터를 arr라 했을 때 arr[0]은 값, arr[1]은 연산 횟수
		
		visited[start]=true;
		queue.add(new int[] {start,0});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			if(now[0]==end) { //목표 도달
				System.out.println(now[1]);
				break;
			}
			if(now[0]+1<LIMIT&&!visited[now[0]+1]) { //1 더한 값이 LIMIT보다 작고 방문하지 않았다면
				visited[now[0]+1]=true;
				queue.add(new int[] {now[0]+1,now[1]+1});
			}
			if(now[0]-1>=0&&!visited[now[0]-1]) { //1 뺀 값이 0 이상이고 방문하지 않았다면
				visited[now[0]-1]=true;
				queue.add(new int[] {now[0]-1,now[1]+1});
			}
			int msb = Integer.highestOneBit(now[0]); //현재 값의 MSB(most significant bit) 가지고 오기
			for(int i=0;(1<<i)<msb;i++) { //1<<i가 MSB값보다 작을 때까지 반복, 문제 조건상 MSB는 보수 연산이 불가능하기 때문
				int temp =now[0]^1<<i; //현재값에서 i번째 비트 보수(1과 xor) 연산
				if(temp<LIMIT&&!visited[temp]) { //보수 연산한 결과가 LIMIT보다 작고 방문하지 않았다면
					visited[temp]=true;
					queue.add(new int[] {temp,now[1]+1});
				}
			}
		}
	}
}
