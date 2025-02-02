//제출번호: 89453228
//메모리:   100380 KB
//실행시간: 936 ms
import java.util.*;
import java.io.*;

public class Main {
    static Map<String, Integer> m = new TreeMap<>();  
	public static void main(String[] args) throws IOException {
	    BufferedReader re = new BufferedReader(new InputStreamReader(System.in));
	    BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));
	    
	    String name;
	    int total = 0;
	    while ((name = re.readLine()) != null) {
	        m.put(name, m.getOrDefault(name, 0) + 1);
	        total++;
	    }
	    
	    for (String key : m.keySet()) {
	        wr.write(String.format("%s %.4f%n", key, 100.0 * m.get(key) / total));
	    }
	    
	    wr.flush();
	}
}