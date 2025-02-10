//메모리: 12328 KB
//시간: 92 ms

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int challenge = Integer.parseInt(br.readLine());
		
		Map<Integer,Integer> map = new LinkedHashMap<>(); //시간 순서대로 저장하기 위해 LinkedHashMap (key는 id, value는 추천 cnt)
		StringTokenizer st = new StringTokenizer(br.readLine());
		int recommend;
		for (int i = 0; i < challenge; i++) {
			recommend = Integer.parseInt(st.nextToken()); //현재 추천받은 학생 id
			if(map.containsKey(recommend)) { //현재 추천받은 학생이 이미 map에 있다면 cnt증가
				map.put(recommend, map.get(recommend)+1);
			}
			else {
				if (map.size() == n) { //map의 size가 n과 같다면(빈 사진틀이 없다면)
					int minId=0, minValue=1000;
					for(Entry entry : map.entrySet()) { //cnt가 가장 작은 학생 중 사진이 가장 오래된 학생 찾기
						if((Integer)entry.getValue()<minValue) {
							minId = (Integer)entry.getKey();
							minValue = (Integer)entry.getValue();
						}
					}
					map.remove(minId); //찾은 학생 삭제
				}
				map.put(recommend, 1); //현재 추천받은 학생 추가
			}
		}
		List<Integer> result = new ArrayList<>(map.keySet()); //map의 key들(학생들의 id)로 ArrayList 만들기
		result.sort(null); //result 정렬
		for(int id:result) {
			System.out.printf("%d ",id);
		}
	}
}