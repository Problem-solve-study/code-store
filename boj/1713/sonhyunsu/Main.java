//제출번호: 89785126
//메모리:   16024 KB
//실행시간: 160 ms

import java.io.*;
import java.util.*;


//이번 문제는 단순한 시뮬레이션이었는데
//n과 r이 매우 작기 때문에 어떻게 구현하든 동작만 하면 상관없었습니다.
//여기서는 필요할 때마다 정렬하고, 데이터를 갱신하는 방법을 택했습니다.
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int n = Integer.parseInt(br.readLine());
	    int r = Integer.parseInt(br.readLine());
	    
	    StringTokenizer st = new StringTokenizer(br.readLine());
	    List<Data> frame = new ArrayList<>();

        //사진틀만큼 객체를 만든다.
	    for (int i = 0; i < n; i++) {
	        frame.add(new Data());
	    }
	    
	    for (int i = 0; i < r; i++) {
	        int recommendStudent = Integer.parseInt(st.nextToken());
	        
            //사진틀 안에 추천 학생이 있는 지 검사한다.
	        if (frame.stream().anyMatch(d -> d.num == recommendStudent)) {
                //안에 있으면 count 증가
	            frame.stream().filter(d -> d.num == recommendStudent).findAny().get().count++; 
	        } else {
                //없으면 count가 가장 작은 순으로, count가 같으면 게시된 지 가장 오래된 사진을 맨 앞으로 가져온다.
	            frame.sort((d1, d2) -> d1.count != d2.count ? Integer.compare(d1.count, d2.count) : Integer.compare(d1.order, d2.order));

                //가져온 사진에 새로운 사진을 업데이트한다.
	            frame.get(0).num = recommendStudent;
	            frame.get(0).order = i;
	            frame.get(0).count = 1;
	        }
	    }
	    
        //학생을 기준으로 정렬한다.
	    frame.sort((d1, d2) -> Integer.compare(d1.num, d2.num));
	    frame.stream()
	        .filter(d -> d.num != 0) //0번은 존재하지 않는 학생이므로 제외한다.
	        .forEach(d -> System.out.printf("%d ", d.num)); //나머지는 출력한다.
    }
	
	public static class Data {
	    int num;
	    int order;
	    int count;
	}
}