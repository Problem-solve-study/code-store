//14556KB, 88ms
import java.io.*;
import java.util.*;

public class Main {
	static int T; // 입력 테스트 케이스 개수
	static StringBuilder sb = new StringBuilder(); // 여우소리를 담을 StringBuilder
	static Map<String, String> map; // 각 동물들의 울음소리를 저장 할 map
	static String res;
	static String endStr = "what does the fox say?"; // 입력 종료 조건
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < T; tc++){
			res = br.readLine();
			String animalSound;
			
			map = new HashMap<>();
			while(true){
				animalSound = br.readLine();
				if (animalSound.equals(endStr)){
					break;
				}
				String soundArr[] = animalSound.split(" ");
				map.put(soundArr[0], soundArr[2]); // key: 동물, value: 울음소리
			}
			
			for (String key : map.keySet()){
				String removeStr = map.get(key); // 제거할 울음 소리
				StringBuilder resSb = new StringBuilder(); //여우를 제외한 나머지 동물들의 울음소리가 제거된 결과를 담기위한 StringBuilder
				String temp[] = res.split(" ");

				// 여우울음 소리만 저장
				for (int i = 0; i < temp.length; i++){
					if (!temp[i].equals(removeStr)){
						resSb.append(temp[i] + " ");
					}
				}
				res = resSb.toString();
			}
			sb.append(res).append('\n');
		}
		System.out.println(sb.toString());
	}
}