//제출번호: 94444395
//메모리:   12064 KB
//실행시간: 72 ms
import java.io.*;
import java.util.*;

//삼각형의 외접원의 둘레를 구하는 문제인 거는 알았는데 공식을 몰라서 찾아봤음
//삼각형 변의 길이를 a, b, c라고 하고, 삼각형의 넓이를 S라고 할 떄, 반지름 R은 abc/4S 로 구할 수 있음
//삼각형의 넓이는 헤론의 공식을 이용해서 구함 A = (a+b+c)/2, S = A(A-a)(A-b)(A-c)
public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    String line;
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    while ((line = br.readLine()) != null) {
	        st = new StringTokenizer(line);
	        double x1 = Double.parseDouble(st.nextToken()), y1 = Double.parseDouble(st.nextToken());
	        double x2 = Double.parseDouble(st.nextToken()), y2 = Double.parseDouble(st.nextToken());
	        double x3 = Double.parseDouble(st.nextToken()), y3 = Double.parseDouble(st.nextToken());
	        
	        double a = Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
	        double b = Math.sqrt((x2 - x3) * (x2 - x3) + (y2 - y3) * (y2 - y3));
	        double c = Math.sqrt((x3 - x1) * (x3 - x1) + (y3 - y1) * (y3 - y1));
	        
	        double mid = (a + b + c) / 2;
	        sb.append(String.format("%.2f%n", 2 * Math.PI * a*b*c/ (4 * Math.sqrt(mid * (mid - a) * (mid - b) * (mid - c)))));
	    }
	    System.out.print(sb);
	}
}

/* 다른 방법으로 반지름 구한 것도 있음
//변 a와 마주보는 각을 A라고 할 때 R = a/2sinA
b dot c = |b||c|cosA <=> cosA = |b||c| / (b dot c)
sinA = sqrt(1 - cos^2(A))

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    
	    String line;
	    StringTokenizer st;
	    StringBuilder sb = new StringBuilder();
	    while ((line = br.readLine()) != null) {
	        st = new StringTokenizer(line);
	        double x1 = Double.parseDouble(st.nextToken()), y1 = Double.parseDouble(st.nextToken());
	        double x2 = Double.parseDouble(st.nextToken()), y2 = Double.parseDouble(st.nextToken());
	        double x3 = Double.parseDouble(st.nextToken()), y3 = Double.parseDouble(st.nextToken());
	        
            double dx12 = x1 - x2, dx23 = x2 - x3, dx31 = x3 - x1;
            double dy12 = y1 - y2, dy23 = y2 - y3, dy31 = y3 - y1;
            
            double cos = (dx12*dx23 + dy12*dy23) / (Math.sqrt(dx12*dx12 + dy12*dy12) * Math.sqrt(dx23*dx23 + dy23*dy23));
            double sin = Math.sqrt(1 - cos * cos);
            
	        sb.append(String.format("%.2f%n", Math.PI / sin * Math.sqrt(dx31*dx31 + dy31*dy31)));
	    }
	    System.out.print(sb);
	}
}
 */