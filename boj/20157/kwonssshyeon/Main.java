import java.util.*;
import java.io.*;


public class Main {
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        HashMap<Double, Integer> slopes = new HashMap<>();
        HashMap<Double, Integer> rslopes = new HashMap<>();

        for (int i=0; i<n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            double slope = y / x;
            if (x > 0) {
                slopes.put(slope, slopes.getOrDefault(slope, 0) + 1);
            }
            else {
                rslopes.put(slope, rslopes.getOrDefault(slope, 0) + 1);
            }
        }

        int answer = 0;
        for (int count : slopes.values()) {
            answer = Math.max(answer, count);
        }
        for (int count : rslopes.values()) {
            answer = Math.max(answer, count);
        }
        System.out.print(answer);        
	}
}