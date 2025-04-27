//제출번호: 93642926
//메모리:   13812 KB
//실행시간: 92 ms
import java.io.*;
import java.util.*;

//로봇의 문자열을 가지고 직접 시뮬레이션하면 풀 수 있음
//각 라운드마다 가위, 바위, 보를 낸 로봇들의 집합을 구하고,
//가위바위보 결과에 따라 집합을 업데이트 했음
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    int T = Integer.parseInt(br.readLine());
	    char[][] commands = new char[10][];
        
        //로봇의 바위, 가위, 보를 저장하는 집합과 전체 로봇을 저장할 집합을 선언
	    Set<Integer> r = new TreeSet<>(), s = new TreeSet<>(), p = new TreeSet<>(), robots = new TreeSet<>();
	    StringBuilder sb = new StringBuilder();
	    for (int t = 0; t < T; t++) {
	        int n = Integer.parseInt(br.readLine());
	        
	        robots.clear();
	        for (int i = 0; i < n; i++) {
	            commands[i] = br.readLine().toCharArray();
	            robots.add(i); //살아남은 로봇을 저장
	        }
	        
	        int k = commands[0].length;
	        for (int i = 0; i < k; i++) {
	            r.clear();
	            s.clear();
	            p.clear();
	            
	            for (int robot : robots) {
                    //각 명령어에 따라 로봇을 알맞은 집합에 넣음
	                switch (commands[robot][i]) {
	                    case 'R': r.add(robot); break;
	                    case 'S': s.add(robot); break;
	                    case 'P': p.add(robot); break;
	                }
	            }
	            
                //조건에 따라 살아남은 집합을 robots에 업데이트
	            if (r.isEmpty() && !s.isEmpty()) {
                    robots.retainAll(s);
	            } else if (s.isEmpty() && !p.isEmpty()) {
                    robots.retainAll(p);
	            } else if (p.isEmpty() && !r.isEmpty()) {
                    robots.retainAll(r);
	            }
	        }
	        
            //만약 2명 이상이 남았으면 무승부
	        if (robots.size() != 1) {
	            sb.append(0);
	        } else {
                //그 외는 로봇의 번호를 출력
	            for (int robot : robots) {
	                sb.append(robot + 1);
	            }
	        }
	        
	        sb.append('\n');
	    }
	    
	    System.out.print(sb);
	}
}