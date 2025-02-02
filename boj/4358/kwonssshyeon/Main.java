// 메모리 : 85804 KB
// 시간 : 860 ms
import java.io.*;
import java.util.*;

public class Main {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		TreeMap<String, Integer> map = new TreeMap<>();
		int total = 0;
		while (true) {
			String name = br.readLine();
			if (name == null || name.length() == 0) 
				break;
			map.put(name, map.getOrDefault(name, 0) + 1);
			total++;
		}

		for (String key : map.keySet()) {
			float avg = (float)(100 * map.get(key)) / (float)total;
			bw.write(key+" "+String.format("%.4f",avg)+"\n");
		}

		bw.flush();
		bw.close();
		br.close();	
	}
}