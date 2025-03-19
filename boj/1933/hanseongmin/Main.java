import java.io.*;
import java.util.*;

/*
 * 47732KB, 1244ms
 * 
 * 스위핑 어렵네요..
 * 아무리 생각해도 깔끔한 논리가 생각나지 않아서 깔끔한 답 하나 찾아서 참고하여 구현
 * 주석으로 생각하지 못했던 부분을 기록
 */

public class Main {
    static StreamTokenizer st = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

    public static void main(String[] args) throws Exception {
        int N = nextInt();
        ArrayList<int[]> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            int s = nextInt();
            int h = nextInt();
            int e = nextInt();
            //배운점 1. 구간의 시작과 끝을 따로 입력받는 것까지는 생각했음
            //근데 구간의 끝은 정렬 기준이 뒤바뀌어야 하기 때문에 높이에 -를 붙여줌
            //본디 들어오면 0, 나가면 1로 해두고 비교할 대상이 둘 다 1인 경우에만 정렬 기준을 바꾸고 있었는데
            //이렇게 하는게 훨씬 간단한듯
            list.add(new int[] {s, h});
            list.add(new int[] {e, -h});
        }
        
        //시작점 기준 정렬, 이후로는 높이 기준 역정렬. 여기까지도 생각했음
        //동시에 같은 지점에서 건물이 들어온다면 더 높은 건물을 우선해야함
        list.sort(Comparator.comparingInt((int[] arr) -> arr[0]).thenComparingInt(arr -> -arr[1]));
        StringBuilder sb = new StringBuilder();
        int maxX = -1;
        int maxH = -1;
        
        //높이, 개수를 저장
        //원래 구간별로 아이디를 따로 주고 구간을 set에 관리하면서 구간을 관리했는데 이게 훨씬 더 깔끔한듯
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        //배운점 2. 그냥 0을 먼저 베이스로 깔아줘서 0을 출력해야할 때 0을 출력하도록 함
        //구간을 빼고나서 자료구조가 empty라면 현재 위치에서부터 0을 출력하도록 했었는데
        //이게 훨씬 일관성있고 좋은듯
        map.put(0, 0);
        for (int i = 0; i < list.size(); i++) {
        	int[] cur = list.get(i);
        	if (cur[1] > 0) { //구간의 시작이라면 map에 개수 1 추가
        		map.put(cur[1], map.getOrDefault(cur[1], 0) + 1);
        	} else { //구간의 끝이라면 map에 개수 1 감소. 개수가 0이 된다면 제거
        		if (map.get(-cur[1]) == 1) {
        			map.remove(-cur[1]);
        		} else {
        			map.put(-cur[1], map.get(-cur[1]) - 1);
        		}
        	}
        	
        	//현재 위치에서 가장 높이가 가장 큰 녀석을 뽑음
        	int curMaxH = map.firstKey();
        	//뽑았는데 높이가 달라졌다? 출력해야함
        	//이때 x좌표가 이전에 사용된 좌표라면 중복된 경우이므로 출력하지 않음
            //그 전 로직은 x 좌표를 고려하는 부분이 미흡해서 틀린 것 같음.
        	if (maxX != cur[0] && maxH != curMaxH) {
        		sb.append(cur[0]).append(' ').append(curMaxH).append(' ');
        		maxX = cur[0];
        		maxH = curMaxH;
        	}
        }

        System.out.print(sb);
    }

    static int nextInt() throws Exception {
        st.nextToken();
        return (int) st.nval;
    }
}