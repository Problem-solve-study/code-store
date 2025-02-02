package workspace;

// 95340 KB, 904 ms

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) throws IOException {
		Map<String, Integer> treeMap = new TreeMap<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String inStr;
		while ((inStr = br.readLine()) != null && !inStr.isEmpty())
			treeMap.put(inStr, treeMap.getOrDefault(inStr, 0) + 1);

		// 전체 값 합계 계산
		int total = treeMap.values().stream().mapToInt(Integer::intValue).sum();

		// 결과 출력 (키 사전순 정렬 유지)
		for (Map.Entry<String, Integer> entry : treeMap.entrySet()) {
			double percentage = (entry.getValue() / (double) total) * 100;
			bw.write(String.format("%s %.4f\n", entry.getKey(), percentage));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
