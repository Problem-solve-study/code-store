import java.io.*;
import java.util.*;

/*
 * 11776KB, 72ms
 * 
 * 문자열을 적당히 파싱해서 여우가 아닌 다른 동물들의 소리를 Set에 저장하고
 * 녹음된 소리에서 여우의 울음소리만 택하여 출력하기
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		while (T --> 0) {
			String q = br.readLine();
			//다른 동물들의 소리를 저장해둘 Set
			HashSet<String> soundSet = new HashSet<>();
			String sound = null;
			while (true) {
				sound = br.readLine();
				if (sound.equals("what does the fox say?")) {
					break;
				}
				
				String[] split = sound.split(" ");
				//울음소리는 split한 후 2번째 원소에 위치함
				soundSet.add(split[2]);
			}
			
			//이제 녹음된 소리를 스플릿
			String[] qSplit = q.split(" ");
			//울음소리를 하나씩 살펴보며
			for (int i = 0; i < qSplit.length; i++) {
				//다른 동물의 울음소리가 아니라면
				if (!soundSet.contains(qSplit[i])) {
					//여우의 울음소리라는 뜻이므로 택함
					sb.append(qSplit[i]).append(' ');
				}
			}
			sb.append('\n');
		}
		System.out.print(sb);
	}
}